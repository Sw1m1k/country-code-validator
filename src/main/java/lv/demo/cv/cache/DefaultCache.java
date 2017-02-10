package lv.demo.cv.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import lv.demo.cv.exception.CasheException;
import lv.demo.cv.model.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by Anastasija on 09.02.2017.
 */
@Component
//@RefreshScope
public class DefaultCache {

    private static final Logger log = LoggerFactory.getLogger(DefaultCache.class);

    @Value("${cashe.checkInterval}")
    private int checkInterval;

    @Value("${cashe.cashSize}")
    private int cashSize;

    @Value("${cashe.reloadCashe}")
    private boolean reloadCashe;

    @Value("${cashe.filePath}")
    private String fileName;

    private String separator = "=";

    private final ScheduledTaskRegistrar registrar = new ScheduledTaskRegistrar();

    private Cache<String, Country> countryMap = null;

    RemovalListener<String, Country> removalListener = new RemovalListener<String, Country>() {
        public void onRemoval(RemovalNotification<String, Country> removal) {
            //log.info("Record with code"+ removal.getKey()+"was deleted");
        }
    };

    @PostConstruct
    public void init(){

        countryMap = CacheBuilder.newBuilder()
                .maximumSize(cashSize)
                .removalListener(removalListener)
                .build();

        populateCashe();

        Runnable eventReloadTask = () -> {
            if(reloadCashe){
                Map<String, Country> tempMap = new HashMap<String,Country>(countryMap.asMap());
                countryMap.asMap().clear();
                populateCashe();
                if(countryMap.asMap() != null && countryMap.asMap().size() == 0){
                    countryMap.asMap().putAll(tempMap);
                    tempMap.clear();
                    log.info("Failed to refresh data. Restoring old data.");
                }
            }
        };

        registrar.addFixedDelayTask(eventReloadTask,checkInterval);
        registrar.afterPropertiesSet();
    }

    private void populateCashe(){
        try (Stream<String> lines = Files.lines(Paths.get(fileName), Charset.forName("Cp1252"))) {
            lines.filter(line -> line.contains(separator)).forEach(
                    line -> countryMap.asMap().putIfAbsent(line.split(separator)[0], new Country(line.split(separator)[0],line.split(separator)[1])));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Country getCountry(String code) throws CasheException {
        if(countryMap.asMap() != null && countryMap.asMap().size() > 0){
            return countryMap.asMap().get(code);
        }else{
            throw new CasheException("Cashe is empty, please contact support team");
        }
    }
}

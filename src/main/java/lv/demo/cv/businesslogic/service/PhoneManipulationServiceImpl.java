package lv.demo.cv.businesslogic.service;

import lv.demo.cv.businesslogic.validator.ValidationService;
import lv.demo.cv.cache.DefaultCache;
import lv.demo.cv.exception.*;
import lv.demo.cv.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by Anastasija on 09.02.2017.
 */
@Service
public class PhoneManipulationServiceImpl implements PhoneManipulationService{

    @Autowired
    private ValidationService validationService;

    @Autowired
    private DefaultCache defaultCache;

    @Value("${data.maxCountryCodeLength}")
    private int maxCountryCodeLength;

    private static final String UNASSIGNED="unassigned";

    private static final String DISCONTINUED="discontinued";

    public Country getCountry(String phone) throws CountryNotFoundException, DiscontinuedCodeException, UnassignedCodeException, InvalidPhoneException, CasheException {
        Country resultCountry = null;

        boolean isValid = validationService.isPhoneValid(phone);

        if(!isValid){
            throw new InvalidPhoneException("Phone number should be numeric, starts with '+' and contain 7-15 chracters");
        }

        String phoneNumber = phone.replaceAll("[^0-9]", "");

        if(maxCountryCodeLength > 0){
            for(int i = maxCountryCodeLength; i > 0; i--) {
                Country country = defaultCache.getCountry(phoneNumber.substring(0,i));
                if(country != null){
                    if(!country.getName().equals(UNASSIGNED) && !country.getName().equals(DISCONTINUED)){
                        resultCountry = country;
                        break;
                    }else{
                        resultCountry = country;
                    }
                }
            }
        }

        if (resultCountry != null) {
            if(resultCountry.getName().equals(UNASSIGNED)){
                throw new UnassignedCodeException("Country Code is unassigned, please refer to the country code documentation");
            }else if(resultCountry.getName().equals(DISCONTINUED)){
                throw new DiscontinuedCodeException("Country Code is discontinued, please refer to the country code documentation");
            }
            return resultCountry;
        } else {
            throw new CountryNotFoundException("Phone Number contain invalid country code");
        }
    }
}

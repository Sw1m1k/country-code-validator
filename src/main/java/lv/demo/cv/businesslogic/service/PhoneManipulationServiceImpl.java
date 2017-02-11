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

    @Value("${manipulation.maxCountryCodeLength}")
    private int maxCountryCodeLength;

    private static final String UNASSIGNED="unassigned";

    private static final String DISCONTINUED="discontinued";

    private static final String RESERVED="reserved";

    public Country getCountry(String phone) throws CountryNotFoundException, DiscontinuedCodeException, UnassignedCodeException, InvalidPhoneException, CasheException, ReservedCodeException {
        Country resultCountry = null;

        boolean isValid = validationService.isPhoneValid(phone);

        if(!isValid){
            throw new InvalidPhoneException("Phone number should be numeric, starts with '+' and contain 7-15 chracters");
        }

        String phoneNumber = phone.replaceAll("[^0-9]", "");

        if(phoneNumber.length() < maxCountryCodeLength){
            maxCountryCodeLength = phoneNumber.length();
        }

        if(maxCountryCodeLength > 0){
            for(int i = maxCountryCodeLength; i > 0; i--) {
                Country country = defaultCache.getCountry(phoneNumber.substring(0,i));
                if(country != null){
                    if(!country.getName().toLowerCase().equals(UNASSIGNED) && !country.getName().toLowerCase().equals(DISCONTINUED) && !country.getName().toLowerCase().equals(RESERVED)){
                        resultCountry = country;
                        break;
                    }else{
                        resultCountry = country;
                    }
                }
            }
        }

        if (resultCountry != null) {
            if(resultCountry.getName().toLowerCase().equals(UNASSIGNED)){
                throw new UnassignedCodeException("Country Code is unassigned, please refer to the country code documentation");
            }else if(resultCountry.getName().toLowerCase().equals(DISCONTINUED)){
                throw new DiscontinuedCodeException("Country Code is discontinued, please refer to the country code documentation");
            }else if(resultCountry.getName().toLowerCase().equals(RESERVED)){
                throw new ReservedCodeException("Country Code is reserved,but not in use at the moment, please refer to the country code documentation");
            }
            return resultCountry;
        } else {
            throw new CountryNotFoundException("Phone Number contain invalid country code");
        }
    }
}

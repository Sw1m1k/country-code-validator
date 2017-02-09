package lv.demo.cv.businesslogic.service;

import lv.demo.cv.exception.CountryNotFoundException;
import lv.demo.cv.exception.DiscontinuedCodeException;
import lv.demo.cv.exception.InvalidPhoneException;
import lv.demo.cv.exception.UnassignedCodeException;
import lv.demo.cv.model.Country;
import org.springframework.stereotype.Service;

/**
 * Created by Anastasija on 09.02.2017.
 */
@Service
public class PhoneManipulationServiceImpl {

    public Country getCountry(String phone) throws CountryNotFoundException, DiscontinuedCodeException, UnassignedCodeException, InvalidPhoneException{
            return new Country("","");
        }
}

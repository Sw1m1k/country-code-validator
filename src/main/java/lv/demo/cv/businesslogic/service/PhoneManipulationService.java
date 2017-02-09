package lv.demo.cv.businesslogic.service;

import lv.demo.cv.exception.CountryNotFoundException;
import lv.demo.cv.exception.DiscontinuedCodeException;
import lv.demo.cv.exception.InvalidPhoneException;
import lv.demo.cv.exception.UnassignedCodeException;
import lv.demo.cv.model.Country;

/**
 * Created by Anastasija on 09.02.2017.
 */
public interface PhoneManipulationService {

    Country getCountry(String phone) throws CountryNotFoundException, DiscontinuedCodeException, UnassignedCodeException, InvalidPhoneException;
}

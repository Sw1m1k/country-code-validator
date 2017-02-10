package lv.demo.cv.businesslogic.service;

import lv.demo.cv.exception.*;
import lv.demo.cv.model.Country;

/**
 * Created by Anastasija on 09.02.2017.
 */
public interface PhoneManipulationService {

    Country getCountry(String phone) throws CountryNotFoundException, DiscontinuedCodeException, UnassignedCodeException, InvalidPhoneException, CasheException, ReservedCodeException;
}

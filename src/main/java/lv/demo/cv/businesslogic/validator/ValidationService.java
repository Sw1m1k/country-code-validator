package lv.demo.cv.businesslogic.validator;

import lv.demo.cv.exception.InvalidPhoneException;
import org.springframework.stereotype.Service;

/**
 * Created by Anastasija on 09.02.2017.
 */

public interface ValidationService {

    boolean isPhoneValid(String phone);
}

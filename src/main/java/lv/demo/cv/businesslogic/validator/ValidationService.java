package lv.demo.cv.businesslogic.validator;

import lv.demo.cv.exception.InvalidPhoneException;
import org.springframework.stereotype.Service;

/**
 * Created by Anastasija on 09.02.2017.
 */
@Service
public interface ValidationService {

    void validatePhone(String phone) throws InvalidPhoneException;
}

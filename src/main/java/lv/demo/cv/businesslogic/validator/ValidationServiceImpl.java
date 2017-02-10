package lv.demo.cv.businesslogic.validator;

import lv.demo.cv.exception.InvalidPhoneException;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Anastasija on 09.02.2017.
 */
@Service
public class ValidationServiceImpl implements ValidationService{

    private String regex = "^\\+(?:[-()0-9] ?){6,14}[0-9]$";

    public boolean isPhoneValid(String phone){

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
}

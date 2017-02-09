package lv.demo.cv.exception;

/**
 * Created by Anastasija on 09.02.2017.
 */
public class InvalidPhoneException extends Exception {
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public InvalidPhoneException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public InvalidPhoneException() {
        super();
    }
}


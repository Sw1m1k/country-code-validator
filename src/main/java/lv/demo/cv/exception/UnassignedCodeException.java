package lv.demo.cv.exception;

/**
 * Created by Anastasija on 09.02.2017.
 */
public class UnassignedCodeException extends Exception {
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public UnassignedCodeException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public UnassignedCodeException() {
        super();
    }
}


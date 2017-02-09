package lv.demo.cv.exception;

/**
 * Created by Anastasija on 09.02.2017.
 */
public class DiscontinuedCodeException extends Exception {
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public DiscontinuedCodeException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public DiscontinuedCodeException() {
        super();
    }
}


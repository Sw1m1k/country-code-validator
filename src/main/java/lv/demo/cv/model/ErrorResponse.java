package lv.demo.cv.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Anastasija on 09.02.2017.
 */
@ApiModel("error")
public class ErrorResponse {

    @ApiModelProperty(example = "0")
    private int statusCode;

    @ApiModelProperty(example = "error mesage text")
    private String message;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package lv.demo.cv.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Anastasija on 09.02.2017.
 */
@ApiModel("phones")
public class Request {

    @ApiModelProperty(example = "7545543")
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}

package lv.demo.cv.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Anastasija on 09.02.2017.
 */
@ApiModel("countries")
public class Country {

    @ApiModelProperty(example = "7545543")
    private String phone;

    @ApiModelProperty(example = "Latvia")
    private String name;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

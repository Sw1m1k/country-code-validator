package lv.demo.cv.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Anastasija on 09.02.2017.
 */
@ApiModel("countries")
public class Country {

    @ApiModelProperty(example = "371")
    private String code;

    @ApiModelProperty(example = "Latvia")
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country(String code, String name) {
        this.code = code;
        this.name = name;
    }
}

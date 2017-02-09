package lv.demo.cv.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lv.demo.cv.exception.CountryNotFoundException;
import lv.demo.cv.exception.DiscontinuedCodeException;
import lv.demo.cv.exception.InvalidPhoneException;
import lv.demo.cv.exception.UnassignedCodeException;
import lv.demo.cv.model.Country;
import lv.demo.cv.model.ErrorResponse;
import lv.demo.cv.model.Request;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Anastasija on 09.02.2017.
 */
@RestController
@RequestMapping(value = "/api/country")
@Api(tags="Country", description = "Country API")
public class CountryAPI {

    @ApiOperation(value="Get country calling code", nickname = "getCode")
    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping(value = "")
    public Country getCountryByPhone(@RequestBody Request request){
        String phone = request.getPhone();
        Country country = new Country("","");
        country.setName("test");
        country.setCode(phone);
        return country;
    }

    @ExceptionHandler({CountryNotFoundException.class, DiscontinuedCodeException.class, UnassignedCodeException.class, InvalidPhoneException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse handleExceptions(Exception ex){
        ErrorResponse error = new ErrorResponse();
        error.setStatusCode(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        return error;
    }
}

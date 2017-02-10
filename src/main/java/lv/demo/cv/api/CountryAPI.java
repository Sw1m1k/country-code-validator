package lv.demo.cv.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lv.demo.cv.businesslogic.service.PhoneManipulationService;
import lv.demo.cv.exception.*;
import lv.demo.cv.model.Country;
import lv.demo.cv.model.ErrorResponse;
import lv.demo.cv.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Anastasija on 09.02.2017.
 */
@RestController
@RequestMapping(value = "/api/country")
@Api(tags="Country", description = "Country API")
public class CountryAPI {

    @Autowired
    private PhoneManipulationService phoneManipulationService;

    @ApiOperation(value="Get country calling code", nickname = "getCode")
    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping(value = "")
    public Country getCountryByPhone(@RequestBody Request request) throws DiscontinuedCodeException, UnassignedCodeException, CountryNotFoundException, InvalidPhoneException, CasheException {
        return phoneManipulationService.getCountry(request.getPhone());
    }

    @ExceptionHandler(CountryNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse handleCodeExceptions(Exception ex){
        ErrorResponse error = new ErrorResponse();
        error.setStatusCode(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        return error;
    }

    @ExceptionHandler({InvalidPhoneException.class, DiscontinuedCodeException.class, UnassignedCodeException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse handlePhoneExceptions(Exception ex){
        ErrorResponse error = new ErrorResponse();
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage(ex.getMessage());
        return error;
    }

    @ExceptionHandler(CasheException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleAppExceptions(Exception ex){
        ErrorResponse error = new ErrorResponse();
        error.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(ex.getMessage());
        return error;
    }
}

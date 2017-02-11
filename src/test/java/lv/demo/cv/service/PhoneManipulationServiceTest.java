package lv.demo.cv.service;

import lv.demo.cv.CountryCodeValidatorApplicationTests;
import lv.demo.cv.businesslogic.service.PhoneManipulationService;
import lv.demo.cv.exception.*;
import lv.demo.cv.model.Country;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Anastasija on 10.02.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PhoneManipulationServiceTest extends CountryCodeValidatorApplicationTests {

    @Autowired
    private PhoneManipulationService phoneManipulationService;

    @Value("${test.service.successPhone}")
    private String successPhone;

    @Value("${test.service.withWhiteSpacesPhone}")
    private String withWhiteSpacesPhone;

    @Value("${test.service.withBracketsPhone}")
    private String withBracketsPhone;

    @Value("${test.service.unassignedCodePhone}")
    private String unassignedCodePhone;

    @Value("${test.service.discontinuedCodePhone}")
    private String discontinuedCodePhone;

    @Value("${test.service.reservedPhone}")
    private String reservedPhone;

    @Value("${test.service.tooBigPhone}")
    private String tooBigPhone;

    @Value("${test.service.tooSmallPhone}")
    private String tooSmallPhone;

    @Value("${test.service.notFoundCountryCodePhone}")
    private String notFoundCountryCodePhone;

    @Test
    public void testSuccessGetCountryService() throws ReservedCodeException, CountryNotFoundException, UnassignedCodeException, DiscontinuedCodeException, InvalidPhoneException, CasheException {

        String phone = successPhone;

        Country country = phoneManipulationService.getCountry(phone);

        Assert.assertNotNull("failure - expected not null", country);
        Assert.assertNotNull("failure - expected name attribute not null",
                country.getName());
        Assert.assertNotNull("failure - expected code attribute not null",
                country.getCode());
        Assert.assertEquals("failure - expected name attribute match", "Sri Lanka",
                country.getName());
        Assert.assertEquals("failure - expected code attribute match", "94",
                country.getCode());
    }

    @Test
    public void testSuccessGetCountryServiceWithWhiteSpaces() throws ReservedCodeException, CountryNotFoundException, UnassignedCodeException, DiscontinuedCodeException, InvalidPhoneException, CasheException {

        String phone = withWhiteSpacesPhone;

        Country country = phoneManipulationService.getCountry(phone);

        Assert.assertNotNull("failure - expected not null", country);
        Assert.assertNotNull("failure - expected name attribute not null",
                country.getName());
        Assert.assertNotNull("failure - expected code attribute not null",
                country.getCode());
        Assert.assertEquals("failure - expected name attribute match", "Israel",
                country.getName());
        Assert.assertEquals("failure - expected code attribute match", "972",
                country.getCode());
    }

    @Test
    public void testSuccessGetCountryServiceWithBrackets() throws ReservedCodeException, CountryNotFoundException, UnassignedCodeException, DiscontinuedCodeException, InvalidPhoneException, CasheException {

        String phone = withBracketsPhone;

        Country country = phoneManipulationService.getCountry(phone);

        Assert.assertNotNull("failure - expected not null", country);
        Assert.assertNotNull("failure - expected name attribute not null",
                country.getName());
        Assert.assertNotNull("failure - expected code attribute not null",
                country.getCode());
        Assert.assertEquals("failure - expected name attribute match", "Isle of Man",
                country.getName());
        Assert.assertEquals("failure - expected code attribute match", "441624",
                country.getCode());
    }

    @Test(expected = UnassignedCodeException.class)
    public void testUnassignedCountryService() throws ReservedCodeException, CountryNotFoundException, UnassignedCodeException, DiscontinuedCodeException, InvalidPhoneException, CasheException {

        String phone = unassignedCodePhone;

        Country country = phoneManipulationService.getCountry(phone);

        Assert.assertNull("failure - expected exception", country);
    }

    @Test(expected = DiscontinuedCodeException.class)
    public void testDiscontinuedCountryService() throws ReservedCodeException, CountryNotFoundException, UnassignedCodeException, DiscontinuedCodeException, InvalidPhoneException, CasheException {

        String phone = discontinuedCodePhone;

        Country country = phoneManipulationService.getCountry(phone);

        Assert.assertNull("failure - expected exception", country);
    }

    @Test(expected = ReservedCodeException.class)
    public void testReservedCountryService() throws ReservedCodeException, CountryNotFoundException, UnassignedCodeException, DiscontinuedCodeException, InvalidPhoneException, CasheException {

        String phone = reservedPhone;

        Country country = phoneManipulationService.getCountry(phone);

        Assert.assertNull("failure - expected exception", country);
    }

    @Test(expected = InvalidPhoneException.class)
    public void tesInvalidTooBigPhoneCountryService() throws ReservedCodeException, CountryNotFoundException, UnassignedCodeException, DiscontinuedCodeException, InvalidPhoneException, CasheException {

        String phone = tooBigPhone;

        Country country = phoneManipulationService.getCountry(phone);

        Assert.assertNull("failure - expected exception", country);
    }

    @Test(expected = InvalidPhoneException.class)
    public void tesInvalidTooSmallPhoneCountryService() throws ReservedCodeException, CountryNotFoundException, UnassignedCodeException, DiscontinuedCodeException, InvalidPhoneException, CasheException {

        String phone = tooSmallPhone;

        Country country = phoneManipulationService.getCountry(phone);

        Assert.assertNull("failure - expected exception", country);
    }

    @Test(expected =  CountryNotFoundException.class)
    public void tesNotFoundPhoneCountryService() throws ReservedCodeException, CountryNotFoundException, UnassignedCodeException, DiscontinuedCodeException, InvalidPhoneException, CasheException {

        String phone = notFoundCountryCodePhone;

        Country country = phoneManipulationService.getCountry(phone);

        Assert.assertNull("failure - expected exception", country);
    }
}

package lv.demo.cv.api;

import lv.demo.cv.CountryCodeValidatorApplicationTests;
import lv.demo.cv.businesslogic.service.PhoneManipulationService;
import lv.demo.cv.model.Country;
import lv.demo.cv.model.ErrorResponse;
import lv.demo.cv.model.Request;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * Created by Anastasija on 10.02.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CountryApiTest extends CountryCodeValidatorApplicationTests {

    @Before
    public void setUp() {
        super.setUp();
    }

    @Value("${test.api.successPhone}")
    private String successPhone;

    @Value("${test.api.unassignedCodePhone}")
    private String unassignedCodePhone;

    @Value("${test.api.discontinuedCodePhone}")
    private String discontinuedCodePhone;

    @Value("${test.api.reservedPhone}")
    private String reservedPhone;

    @Value("${test.api.invalidPhoneWithOutPlusPhone}")
    private String invalidPhoneWithOutPlusPhone;

    @Value("${test.api.invalidPhoneWithLetter}")
    private String invalidPhoneWithLetter;

    @Value("${test.api.tooBigPhone}")
    private String tooBigPhone;

    @Value("${test.api.tooSmallPhone}")
    private String tooSmallPhone;

    @Value("${test.api.notFoundCountryCodePhone}")
    private String notFoundCountryCodePhone;

    String uri = "/api/country";

    @Test
    public void testSuccessCountryApi() throws Exception {

        Request request = new Request();
        request.setPhone(successPhone);
        String inputJson = super.mapToJson(request);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(inputJson))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);

        Country country = super.mapFromJson(content, Country.class);

        Assert.assertNotNull("failure - expected country not null",
                country);
        Assert.assertNotNull("failure - expected country.name not null",
                country.getName());
        Assert.assertEquals("failure - expected country.name match", "Canada,United State",
                country.getName());
        Assert.assertEquals("failure - expected country.code match", "1",
                country.getCode());
    }

    @Test
    public void testUnassignedCountryApi() throws Exception {

        Request request = new Request();
        request.setPhone(unassignedCodePhone);
        String inputJson = super.mapToJson(request);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(inputJson))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status 400", 400, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);

        ErrorResponse errorResponse = super.mapFromJson(content, ErrorResponse.class);

        Assert.assertNotNull("failure - expected errorResponse not null",
                errorResponse);
        Assert.assertNotNull("failure - expected errorResponse.message not null",
                errorResponse.getMessage());
        Assert.assertEquals("failure - expected errorResponse.message match", "Country Code is unassigned, please refer to the country code documentation",
                errorResponse.getMessage());
        Assert.assertEquals("failure - expected errorResponse.statusCode match", 400,
                errorResponse.getStatusCode());
    }

    @Test
    public void testDiscontinuedCountryApi() throws Exception {

        Request request = new Request();
        request.setPhone(discontinuedCodePhone);
        String inputJson = super.mapToJson(request);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(inputJson))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status 400", 400, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);

        ErrorResponse errorResponse = super.mapFromJson(content, ErrorResponse.class);

        Assert.assertNotNull("failure - expected errorResponse not null",
                errorResponse);
        Assert.assertNotNull("failure - expected errorResponse.message not null",
                errorResponse.getMessage());
        Assert.assertEquals("failure - expected errorResponse.message match", "Country Code is discontinued, please refer to the country code documentation",
                errorResponse.getMessage());
        Assert.assertEquals("failure - expected errorResponse.statusCode match", 400,
                errorResponse.getStatusCode());
    }

    @Test
    public void testReservedCountryApi() throws Exception {

        Request request = new Request();
        request.setPhone(reservedPhone);
        String inputJson = super.mapToJson(request);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(inputJson))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status 400", 400, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);

        ErrorResponse errorResponse = super.mapFromJson(content, ErrorResponse.class);

        Assert.assertNotNull("failure - expected errorResponse not null",
                errorResponse);
        Assert.assertNotNull("failure - expected errorResponse.message not null",
                errorResponse.getMessage());
        Assert.assertEquals("failure - expected errorResponse.message match", "Country Code is reserved,but not in use at the moment, please refer to the country code documentation",
                errorResponse.getMessage());
        Assert.assertEquals("failure - expected errorResponse.statusCode match", 400,
                errorResponse.getStatusCode());
    }

    @Test
    public void testInvalidPhoneWithOutPlusApi() throws Exception {

        Request request = new Request();
        request.setPhone(invalidPhoneWithOutPlusPhone);
        String inputJson = super.mapToJson(request);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(inputJson))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status 400", 400, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);

        ErrorResponse errorResponse = super.mapFromJson(content, ErrorResponse.class);

        Assert.assertNotNull("failure - expected errorResponse not null",
                errorResponse);
        Assert.assertNotNull("failure - expected errorResponse.message not null",
                errorResponse.getMessage());
        Assert.assertEquals("failure - expected errorResponse.message match", "Phone number should be numeric, starts with '+' and contain 7-15 chracters",
                errorResponse.getMessage());
        Assert.assertEquals("failure - expected errorResponse.statusCode match", 400,
                errorResponse.getStatusCode());
    }

    @Test
    public void testInvalidPhoneWithLetterApi() throws Exception {

        Request request = new Request();
        request.setPhone(invalidPhoneWithLetter);
        String inputJson = super.mapToJson(request);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(inputJson))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status 400", 400, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);

        ErrorResponse errorResponse = super.mapFromJson(content, ErrorResponse.class);

        Assert.assertNotNull("failure - expected errorResponse not null",
                errorResponse);
        Assert.assertNotNull("failure - expected errorResponse.message not null",
                errorResponse.getMessage());
        Assert.assertEquals("failure - expected errorResponse.message match", "Phone number should be numeric, starts with '+' and contain 7-15 chracters",
                errorResponse.getMessage());
        Assert.assertEquals("failure - expected errorResponse.statusCode match", 400,
                errorResponse.getStatusCode());
    }

    @Test
    public void testTooBigPhoneApi() throws Exception {

        Request request = new Request();
        request.setPhone(tooBigPhone);
        String inputJson = super.mapToJson(request);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(inputJson))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status 400", 400, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);

        ErrorResponse errorResponse = super.mapFromJson(content, ErrorResponse.class);

        Assert.assertNotNull("failure - expected errorResponse not null",
                errorResponse);
        Assert.assertNotNull("failure - expected errorResponse.message not null",
                errorResponse.getMessage());
        Assert.assertEquals("failure - expected errorResponse.message match", "Phone number should be numeric, starts with '+' and contain 7-15 chracters",
                errorResponse.getMessage());
        Assert.assertEquals("failure - expected errorResponse.statusCode match", 400,
                errorResponse.getStatusCode());
    }

    @Test
    public void testTooSmallPhoneApi() throws Exception {

        Request request = new Request();
        request.setPhone(tooSmallPhone);
        String inputJson = super.mapToJson(request);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(inputJson))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status 400", 400, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);

        ErrorResponse errorResponse = super.mapFromJson(content, ErrorResponse.class);

        Assert.assertNotNull("failure - expected errorResponse not null",
                errorResponse);
        Assert.assertNotNull("failure - expected errorResponse.message not null",
                errorResponse.getMessage());
        Assert.assertEquals("failure - expected errorResponse.message match", "Phone number should be numeric, starts with '+' and contain 7-15 chracters",
                errorResponse.getMessage());
        Assert.assertEquals("failure - expected errorResponse.statusCode match", 400,
                errorResponse.getStatusCode());
    }

    @Test
    public void testNotFoundCountryApi() throws Exception {

        Request request = new Request();
        request.setPhone(notFoundCountryCodePhone);
        String inputJson = super.mapToJson(request);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(inputJson))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status 404", 404, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);

        ErrorResponse errorResponse = super.mapFromJson(content, ErrorResponse.class);

        Assert.assertNotNull("failure - expected errorResponse not null",
                errorResponse);
        Assert.assertNotNull("failure - expected errorResponse.message not null",
                errorResponse.getMessage());
        Assert.assertEquals("failure - expected errorResponse.message match", "Phone Number contain invalid country code",
                errorResponse.getMessage());
        Assert.assertEquals("failure - expected errorResponse.statusCode match", 404,
                errorResponse.getStatusCode());
    }
}

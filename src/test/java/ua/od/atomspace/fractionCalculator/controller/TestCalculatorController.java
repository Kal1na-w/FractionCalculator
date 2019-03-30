package ua.od.atomspace.fractionCalculator.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ua.od.atomspace.fractionCalculator.TestAbstract;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TestCalculatorController extends TestAbstract {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }
    @Test
    public void testPostEquation() throws Exception {
        String uri = "/calc";
        Map<String,String> jsonSend = new HashMap<>();
        jsonSend.put("equation","2+5/5");
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(jsonSend))).andReturn();
        jsonSend.put("result","3/1");
        assertEquals(200, mvcResult.getResponse().getStatus());
        assertEquals(jsonSend,mapFromJson(mvcResult.getResponse().getContentAsString(),HashMap.class));
    }
}

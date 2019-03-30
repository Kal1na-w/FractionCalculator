package ua.od.atomspace.fractionCalculator.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ua.od.atomspace.fractionCalculator.AbstractTest;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;


public class TestStatusController extends AbstractTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void testGetStatus() throws Exception {
		String uri = "/healthcheck";
		HashMap<String,String> status = new HashMap<>();
		status.put("status","ok");
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		assertEquals(200, mvcResult.getResponse().getStatus());
		assertEquals(mapToJson(status),mvcResult.getResponse().getContentAsString());
	}


}

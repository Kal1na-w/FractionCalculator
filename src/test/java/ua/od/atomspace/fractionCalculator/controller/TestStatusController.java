package ua.od.atomspace.fractionCalculator.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ua.od.atomspace.fractionCalculator.TestAbstract;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;


public class TestStatusController extends TestAbstract {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void testJson() throws Exception {
		String uri = "/healthcheck";
		HashMap<String,String> status = new HashMap<>();
		status.put("status","ok");
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Type","application/json");
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).headers(httpHeaders)).andReturn();
		assertEquals(200, mvcResult.getResponse().getStatus());
		assertEquals(mapToJson(status),mvcResult.getResponse().getContentAsString());
	}
	@Test
	public void testTextPlain() throws Exception {
		String uri = "/healthcheck";
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Type","text/plain");
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).headers(httpHeaders)).andReturn();
		assertEquals(200, mvcResult.getResponse().getStatus());
		assertEquals("status\tok",mvcResult.getResponse().getContentAsString());
	}

}

package org.amura.interview.test.integration;

import org.amura.interview.application.Application;
import org.amura.interview.client.util.RestConstants;
import org.amura.interview.rep.MatrixProblemRep;
import org.amura.interview.test.util.TestHelperUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT,  classes=Application.class)
public class MatrixProblemControllerIntegrationTest {
	
	@LocalServerPort
    int randomServerPort;
	
	@Value("#{servletContext.contextPath}")
    private String servletContextPath;
	
	public String getApplicationUrl() {
		return "http://localhost:" + randomServerPort + "/" + servletContextPath;
	}
	
	private RestTemplate rt = new RestTemplate();
	
	@Before
	public void before() {
		rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	}
	
	@Test
	public void testSuccessfulScenario() throws Exception {
		// data
		int array[][] = {
				{0,1,1,0,1},
				{1,1,0,1,0},
				{0,1,1,1,0},
				{1,1,1,1,0},
				{1,1,1,1,1},
				{0,0,0,0,0}
		};
		
		MatrixProblemRep rep = new MatrixProblemRep();
		rep.setArray(array);
		
		// execution
		String body = TestHelperUtils.getJsonString(rep);
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Content-Type", "application/json");
		HttpEntity<String> entity = new HttpEntity<String>(body, headers);
		
		ResponseEntity<String> response = rt.postForEntity(
				getApplicationUrl() + RestConstants.REST_ENDPOINT_MATRIX_PROBLEM, 
				entity, 
				String.class);
		
		
		Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
		
		MatrixProblemRep responseRep = TestHelperUtils.getObjectFromJson(
				response.getBody().toString(), MatrixProblemRep.class);
		
		Assert.assertEquals(responseRep.getHeight(), 3);
		Assert.assertEquals(responseRep.getWidth(), 3);
		Assert.assertEquals(responseRep.getLongestMatrixXCordinate(), 2);
		Assert.assertEquals(responseRep.getLongestMatrixYCordinate(), 1);
	}

}

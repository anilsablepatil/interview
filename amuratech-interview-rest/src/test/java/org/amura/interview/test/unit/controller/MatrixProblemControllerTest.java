package org.amura.interview.test.unit.controller;

import org.amura.interview.client.converter.MatrixProblemConverter;
import org.amura.interview.client.dto.MatrixProblemDTO;
import org.amura.interview.client.exception.InvalidMatrixException;
import org.amura.interview.client.service.MatrixProblemService;
import org.amura.interview.client.util.RestConstants;
import org.amura.interview.controller.MatrixProblemController;
import org.amura.interview.rep.MatrixProblemRep;
import org.amura.interview.test.util.TestHelperUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class MatrixProblemControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	private MatrixProblemController controller;
	
	@Mock
	private MatrixProblemService matrixProblemService;

	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders
				.standaloneSetup(controller)
				.build();
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
		
		// mocking 
		int height=3, width=3, longestMatrixXCordinate=2, longestMatrixYCordinate=1;
		MatrixProblemDTO mockedResponse = MatrixProblemConverter.convertRepToDto(rep);
		mockedResponse.setHeight(height);
		mockedResponse.setWidth(width);
		mockedResponse.setLongestMatrixXCordinate(longestMatrixXCordinate);
		mockedResponse.setLongestMatrixYCordinate(longestMatrixYCordinate);
		
		Mockito.when(matrixProblemService.findBiggestInnerMatrix(Matchers.any(MatrixProblemDTO.class))).thenReturn(mockedResponse);
		
		// execution
		String body = TestHelperUtils.getJsonString(rep);
		RequestBuilder request = MockMvcRequestBuilders
				.post(RestConstants.REST_ENDPOINT_MATRIX_PROBLEM)
				.content(body)
				.contentType("application/json")
				.accept("application/json");
		
		MvcResult result = mockMvc.perform(request).andReturn();
		Assert.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		
		MatrixProblemRep response = TestHelperUtils.getObjectFromJson(
				result.getResponse().getContentAsString(), 
				MatrixProblemRep.class);
		
		Assert.assertEquals(response.getHeight(), height);
		Assert.assertEquals(response.getWidth(), width);
		Assert.assertEquals(response.getLongestMatrixXCordinate(), longestMatrixXCordinate);
		Assert.assertEquals(response.getLongestMatrixYCordinate(), longestMatrixYCordinate);
	}
	
	@Test
	public void test_provide_invalid_matrix_should_fail() throws Exception {
		// data
		int array[][] = {
				{0,1,1,0,1},
				{1,1,0,1,0},
				{0,1,1,1,0,1},
				{1,1,1,1,0},
				{1,1,1,1,1},
				{0,0,0,0,0}
		};
		
		MatrixProblemRep rep = new MatrixProblemRep();
		rep.setArray(array);
		
		//mocking
		Mockito.when(matrixProblemService.findBiggestInnerMatrix(Matchers.any(MatrixProblemDTO.class))).thenThrow(InvalidMatrixException.class);
		
		// execution
		String body = TestHelperUtils.getJsonString(rep);
		RequestBuilder request = MockMvcRequestBuilders
				.post(RestConstants.REST_ENDPOINT_MATRIX_PROBLEM)
				.content(body)
				.contentType("application/json")
				.accept("application/json");
		
		MvcResult result = mockMvc.perform(request).andReturn();
		Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
	}
	
}

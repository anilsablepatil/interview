package org.amura.interview.test.service.impl;

import org.amura.interview.client.dto.MatrixProblemDTO;
import org.amura.interview.client.exception.InvalidMatrixException;
import org.amura.interview.service.impl.MatrixProblemServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

public class MatrixProblemServiceImplTest {

	@InjectMocks
	MatrixProblemServiceImpl service = new MatrixProblemServiceImpl();
	
	@Before
	public void setup() {
		
	}
	
	@Test
	public void test_find_biggestmatrix_successful_scerario() throws InvalidMatrixException {
		// Preparing Test data
		int[][] array = {
				{1,0,0,0,0,0}, 
				{0,1,1,1,1,0}, 
				{0,1,1,1,1,0}, 
				{0,0,0,0,0,0}
		};
		
		MatrixProblemDTO inputDto = new MatrixProblemDTO();
		inputDto.setArray(array);
		
		
		// execution
		MatrixProblemDTO dto = service.findBiggestInnerMatrix(inputDto);
		
		// result validation
		Assert.assertEquals(dto.getWidth(), 4);
		Assert.assertEquals(dto.getHeight(), 2);
		Assert.assertEquals(dto.getLongestMatrixXCordinate(), 1);
		Assert.assertEquals(dto.getLongestMatrixYCordinate(), 1);	
	}
	
	
	@Test
	public void test_find_biggestmatrix_matrix_invalid_scerario() throws InvalidMatrixException {
		// Preparing Test data
		int[][] array = {
				{1,0,0,0,0,0}, 
				{0,1,1,1,1,0,1}, 
				{0,1,1,1,1,0,1,1}, 
				{0,0,0,0,0,0,1,1,1}
		};
		
		MatrixProblemDTO inputDto = new MatrixProblemDTO();
		inputDto.setArray(array);
		
		
		// execution
		try {
			MatrixProblemDTO dto = service.findBiggestInnerMatrix(inputDto);
			Assert.fail("Should never reach here when invalid matrix is provided");
		}
		catch(Exception ex) {
			Assert.assertTrue(ex instanceof InvalidMatrixException);
		}
	}
	
	@Test
	public void test_find_biggestmatrix_matrix_empty_scerario() throws InvalidMatrixException {
		// Preparing Test data
		int[][] array = null;
		
		MatrixProblemDTO inputDto = new MatrixProblemDTO();
		inputDto.setArray(array);
		
		
		// execution
		try {
			MatrixProblemDTO dto = service.findBiggestInnerMatrix(inputDto);
			Assert.fail("Should never reach here when invalid matrix is provided");
		}
		catch(Exception ex) {
			Assert.assertTrue(ex instanceof InvalidMatrixException);
		}
	}
	
	@Test
	public void test_find_biggestmatrix_matrix_with_boundry_condition_scerario() throws InvalidMatrixException {
		// Preparing Test data
		int[][] array = {
				{1,0,0,0,0,0}, 
				{0,0,0,0,0,0}, 
				{0,0,0,0,0,0},
				{0,0,0,0,0,0}
		};
		
		MatrixProblemDTO inputDto = new MatrixProblemDTO();
		inputDto.setArray(array);
		
		
		// execution
		MatrixProblemDTO dto = service.findBiggestInnerMatrix(inputDto);

		// result validation
		Assert.assertEquals(dto.getWidth(), 1);
		Assert.assertEquals(dto.getHeight(), 1);
		Assert.assertEquals(dto.getLongestMatrixXCordinate(), 0);
		Assert.assertEquals(dto.getLongestMatrixYCordinate(), 0);
	}
}

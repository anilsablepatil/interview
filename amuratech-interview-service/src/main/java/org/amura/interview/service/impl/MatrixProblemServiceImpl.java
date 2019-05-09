package org.amura.interview.service.impl;

import org.amura.interview.client.dto.MatrixProblemDTO;
import org.amura.interview.client.exception.InvalidMatrixException;
import org.amura.interview.client.service.MatrixProblemService;
import org.springframework.stereotype.Service;

@Service
public class MatrixProblemServiceImpl implements MatrixProblemService {

	@Override
	public MatrixProblemDTO findBiggestInnerMatrix(MatrixProblemDTO dto) throws InvalidMatrixException {
		
		// Validate the matrix
		boolean validateRV = validateMatrixForRowsAndColumn(dto.getArray());
		if(!validateRV) {
			throw new InvalidMatrixException();
		}
		
		MatrixProblemDTO rv = solveProblem(dto);
		return rv;
	}
	
	// Process the matrix and Extract the necessary details
	private MatrixProblemDTO solveProblem(MatrixProblemDTO dto) {

		// source array
		int[][] source = dto.getArray();
		
		// find out rows & column
		int rows, cols;
		rows = source.length;
		cols = source[1].length;

		// aux matrix		
		int aux[][] = new int[rows][cols];

		// initilize first row first column
		for(int i=0; i<cols; i++) {
			aux[0][i] = source[0][i];
		}
		for(int i=0; i<rows; i++) {
			aux[i][0] = source[i][0];
		}
		System.out.println("src");
		printmat(source);

		//	System.out.println("Aux");
		//printmat(aux);

		// if value is 0 then put zero in aux matrix
		// if value is 1 then check take minimum of left, top, left-top element with respect to current
		// and store 1 + minimum there
		for(int i=1; i<rows; i++) {
			for(int j=1; j<cols; j++) {

				if(source[i][j] == 1) {
					int left = aux[i-1][j];
					int top = aux [i][j-1];
					int left_top = aux [i-1][j-1];

					int min = Math.min(left_top, Math.min(left, top));

					int valueToPut = min + 1;
					aux[i][j] = valueToPut;
				}
				else {
					aux[i][j] = 0;
				}


			}
		}

		//System.out.println("Aux - after processing");
		//	printmat(aux);


		// find maximum value co-ordinate;
		rows = aux.length;
		cols = aux[1].length;
		int max = 0;
		int row=0, col=0;
		for(int i = 0; i< rows; i++ ) {
			for(int j =0 ; j< cols; j++) {
				if(aux[i][j] >= max) {
					max = aux[i][j];
					row=i; col=j;
				}
			}

		}

		System.out.println("Max= " + max + "; row, col = " + row + "," + col );


		// reverse aux matrix	
		rows = source.length;
		cols = source[1].length;
		int aux2[][] = new int[rows][cols];

		// initilize last row last column
		for(int i=0; i<rows; i++) {
			aux2[i][cols-1] = source[i][cols-1];
		}
		for(int i=0; i<cols; i++) {
			aux2[rows-1][i] = source[rows-1][i];
		}
		//	System.out.println("src");
		//	printmat(source);

		//	System.out.println("rev Aux");
		//printmat(aux2);


		// if value is 0 then put zero in aux matrix
		// if value is 1 then check take minimum of left, top, left-top element with respect to current
		// and store 1 + minimum there
		for(int i=rows-2; i>=0; i--) {
			for(int j=cols-2; j>=0; j--) {

				if(source[i][j] == 1) {
					int right = aux[i+1][j];
					int bottom = aux [i][j+1];
					int bottom_right = aux [i+1][j+1];

					int min = Math.min(bottom_right, Math.min(right, bottom));

					int valueToPut = min + 1;
					aux[i][j] = valueToPut;
				}
				else {
					aux[i][j] = 0;
				}


			}
		}

		//System.out.println("Aux - after processing");
		//printmat(aux);


		// find maximum value co-ordinate;
		rows = aux.length;
		cols = aux[1].length;
		int max2 = 0;
		int row2=0, col2=0;
		for(int i=rows-1; i>=0; i--) {
			for(int j=cols-1; j>=0; j--) {
				if(aux[i][j] >= max) {
					max2 = aux[i][j];
					row2=i; col2=j;
				}
			}

		}

		System.out.println("Min= " + max2 + "; start row, start col = " + row2 + "," + col2 );



		int height = (row - row2) + 1;
		int width = (col - col2) + 1;

		System.out.println("height = " + height + "; width = " + width);


		dto.setHeight(height);
		dto.setWidth(width);
		dto.setLongestMatrixXCordinate(row2);
		dto.setLongestMatrixYCordinate(col2);

		return dto;
	}

	private static void printmat(int[][] arr) {
		int rows, cols;
		rows = arr.length;
		cols = arr[1].length;
		
		for(int i = 0; i< rows; i++ ) {
			for(int j =0 ; j< cols; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println("");
		}
	}
	

	// Validate the given 2-D array is matrix and not just having random sized element
	private boolean validateMatrixForRowsAndColumn(int[][] arr) {
		boolean isValidMatrix = false;
		
		if(arr != null && arr.length > 0) {
			int len = arr[0].length;
			isValidMatrix = true;
			
			for(int i = 1 ; i< arr.length; i++) {
				if(arr[i].length != len) {
					isValidMatrix = false;
					break;
				}
			}
		}
		
		return isValidMatrix;
	}
	
}

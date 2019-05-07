package org.amura.interview.client.converter;

import org.amura.interview.client.dto.MatrixProblemDTO;
import org.amura.interview.rep.MatrixProblemRep;

public class MatrixProblemConverter {
	
	public static MatrixProblemRep convertDtoToRep(MatrixProblemDTO dto) {
		MatrixProblemRep rep = new MatrixProblemRep();
		
		rep.setArray(dto.getArray());
		
		rep.setLongestMatrixXCordinate(dto.getLongestMatrixXCordinate());
		rep.setLongestMatrixYCordinate(dto.getLongestMatrixYCordinate());
		
		rep.setHeight(dto.getHeight());
		rep.setWidth(dto.getWidth());
		
		return rep;
	}
	
	public static MatrixProblemDTO convertRepToDto(MatrixProblemRep rep) {
		MatrixProblemDTO dto = new MatrixProblemDTO();
		
		dto.setArray(rep.getArray());
		
		dto.setLongestMatrixXCordinate(rep.getLongestMatrixXCordinate());
		dto.setLongestMatrixYCordinate(rep.getLongestMatrixYCordinate());
		
		dto.setHeight(rep.getHeight());
		dto.setWidth(rep.getWidth());
		
		return dto;
	}
	
}

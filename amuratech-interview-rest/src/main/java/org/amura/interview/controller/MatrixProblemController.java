package org.amura.interview.controller;

import org.amura.interview.client.converter.MatrixProblemConverter;
import org.amura.interview.client.dto.MatrixProblemDTO;
import org.amura.interview.client.exception.InvalidMatrixException;
import org.amura.interview.client.service.MatrixProblemService;
import org.amura.interview.client.util.RestConstants;
import org.amura.interview.rep.ErrorMessageRep;
import org.amura.interview.rep.MatrixProblemRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value=RestConstants.REST_ENDPOINT_MATRIX_PROBLEM)
public class MatrixProblemController {
	
	@Autowired
	private MatrixProblemService matrixProblemService;
	
	@RequestMapping( method=RequestMethod.POST, 
			consumes = RestConstants.MEDIA_TYPE_JSON,
			produces = RestConstants.MEDIA_TYPE_JSON)
	public ResponseEntity<?> solveMatrix(@RequestBody MatrixProblemRep rep) {
		try {
			
			MatrixProblemDTO dto = MatrixProblemConverter.convertRepToDto(rep);
			MatrixProblemDTO rv = getMatrixProblemService().findBiggestInnerMatrix(dto);
			MatrixProblemRep rvRep = MatrixProblemConverter.convertDtoToRep(rv);
			
			return new ResponseEntity<MatrixProblemRep>(rvRep, HttpStatus.OK);
		}
		catch(InvalidMatrixException ex) {
			ErrorMessageRep errorRep = new ErrorMessageRep();
			errorRep.setCode(ex.getErrorCode());
			errorRep.setMessage(ex.getMessage());
			return new ResponseEntity<ErrorMessageRep>(errorRep, HttpStatus.BAD_REQUEST);
		}
		catch(Exception ex) {
			ErrorMessageRep errorRep = new ErrorMessageRep();
			errorRep.setCode(98998889); // Generic error code
			errorRep.setMessage(ex.getMessage());
			return new ResponseEntity<ErrorMessageRep>(errorRep, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public MatrixProblemService getMatrixProblemService() {
		return matrixProblemService;
	}

	public void setMatrixProblemService(MatrixProblemService matrixProblemService) {
		this.matrixProblemService = matrixProblemService;
	}
}

package org.amura.interview.client.service;

import org.amura.interview.client.dto.MatrixProblemDTO;
import org.amura.interview.client.exception.InvalidMatrixException;

public interface MatrixProblemService {
	public MatrixProblemDTO findBiggestInnerMatrix(MatrixProblemDTO dto) throws InvalidMatrixException;
}

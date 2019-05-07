package org.amura.interview.client.dto;

public class MatrixProblemDTO {
	private int array[][];
	private int longestMatrixXCordinate;
	private int longestMatrixYCordinate;
	private int width;
	private int height;
	
	public int[][] getArray() {
		return array;
	}
	public void setArray(int[][] array) {
		this.array = array;
	}
	public int getLongestMatrixXCordinate() {
		return longestMatrixXCordinate;
	}
	public void setLongestMatrixXCordinate(int longestMatrixXCordinate) {
		this.longestMatrixXCordinate = longestMatrixXCordinate;
	}
	public int getLongestMatrixYCordinate() {
		return longestMatrixYCordinate;
	}
	public void setLongestMatrixYCordinate(int longestMatrixYCordinate) {
		this.longestMatrixYCordinate = longestMatrixYCordinate;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
}

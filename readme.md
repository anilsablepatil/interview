Test outputs
====================


Successful Scenarios
=====================
$ curl -s -X POST -H "Content-Type: application/json" -d "{\"array\":[ [1,0,0,0,0,0], [0,1,1,1,1,0], [0,1,1,1,1,0], [0,0,0,0,0,0] ]}" http://localhost:8080/amuratech/matrixproblem


Output :
{
	"array":[
		[1,0,0,0,0,0], 
		[0,1,1,1,1,0], 
		[0,1,1,1,1,0], 
		[0,0,0,0,0,0]
	],
	"longestMatrixXCordinate":1,
	"longestMatrixYCordinate":1,
	"width":4,
	"height":2}


$ curl -s -X POST -H "Content-Type: application/json" -d "{\"array\":[[0,1,1,0,1], [1,1,0,1,0],[0,1,1,1,0], [1,1,1,1,0],[1,1,1,1,1],[0,0,0,0,0]]}" http://localhost:8080/amuratech/matrixproblem

Output : 
{	
	"array":[
		[0,1,1,0,1], 
		[1,1,0,1,0], 
		[0,1,1,1,0], 
		[1,1,1,1,0], 
		[1,1,1,1,1], 
		[0,0,0,0,0] 
	],
	"longestMatrixXCordinate":2,
	"longestMatrixYCordinate":1,
	"width":3,
	"height":3
}

Negative Test Scenario 
=======================
(because its last rows has extra items and did not form a matrix)
$ curl -s -X POST -H "Content-Type: application/json" -d "{\"array\":[[0,1,1,0,1], [1,1,0,1,0],[0,1,1,1,0], [1,1,1,1,0],[1,1,1,1,1],[0,0,0,0,0,1]]}" http://localhost:8080/amuratech/matrixproblem

Output : 
{"code":12345,"message":"The Specified Matrix is Invalid"}
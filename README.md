Employee Controller List of Endpoints :

1. Get Employees for a given department:
	Method - GET,
 	URI - /employees/{dapartmentId},

2. Get a List of Employees who are hired after a given date and a given min salary:
	Method - GET,
	URI - /employees?hireDate=date&salary=sal,

	Request Parameters -
	hireDate - Hire Date,
	salary- Minimum Salary

3. Delete Employee records from salary table whose hire date is before a given date

	Method - GET,
	URI - /deleteEmployees?hireDate=date,

	Request Parameters -
	hireDate- Hire Date



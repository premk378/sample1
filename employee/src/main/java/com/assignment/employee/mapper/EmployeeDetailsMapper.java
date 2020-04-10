package com.assignment.employee.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.assignment.employee.models.EmployeeDetails;

public class EmployeeDetailsMapper implements RowMapper<EmployeeDetails> {

	@Override
	public EmployeeDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		EmployeeDetails emp = new EmployeeDetails();
		if(null != rs.getString("BIRTH_DATE"))
			emp.setDateOfBirth(rs.getString("BIRTH_DATE"));
		if(null != rs.getString("FIRST_NAME"))
			emp.setFirstName(rs.getString("FIRST_NAME"));
		if(null != rs.getString("LAST_NAME"))
			emp.setLastName(rs.getString("LAST_NAME"));
		if(null != rs.getString("EMP_NO"))
			emp.setEmpNo(rs.getLong("EMP_NO"));
		if(null != rs.getString("SALARY"))
			emp.setSalary(rs.getLong("SALARY"));
		if(null != rs.getString("HIRE_DATE"))
			emp.setHireDate(rs.getString("HIRE_DATE"));
		return emp;
	}
	
}

package com.assignment.employee.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.assignment.employee.models.Employee;

public class EmployeeMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee emp = new Employee();
		if(null != rs.getString("BIRTH_DATE"))
			emp.setDateOfBirth(rs.getString("BIRTH_DATE"));
		if(null != rs.getString("FIRST_NAME"))
			emp.setFirstName(rs.getString("FIRST_NAME"));
		if(null != rs.getString("LAST_NAME"))
			emp.setLastName(rs.getString("LAST_NAME"));
		return emp;
	}
	
}

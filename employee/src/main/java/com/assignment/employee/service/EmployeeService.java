package com.assignment.employee.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.assignment.employee.mapper.EmployeeRowMapper;
import com.assignment.employee.models.Employee;

@Service
public class EmployeeService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Value("${date.format}")
	String dateFormat;

	public List<Employee> getEmployeeDetails(String departmentId) {
		List<Employee> lstEmployees = new ArrayList<Employee>();
		String getEmployeesSql = "SELECT E.FIRST_NAME,E.LAST_NAME,E.BIRTH_DATE FROM EMPLOYEES E,DEPARTMENTS D,DEPT_EMP DE WHERE D.DEPT_NO=DE.DEPT_NO AND E.EMP_NO=DE.EMP_NO AND D.DEPT_NO = ?";
		try {
			lstEmployees = jdbcTemplate.query(getEmployeesSql, new Object[] { departmentId }, new EmployeeRowMapper());
		} catch (Exception ex) {
			lstEmployees = null;
		}
		return lstEmployees;
	}

	public List<Employee> getEmployeesBeforeHireDate(String hireDate, Long salary) {
		List<Employee> lstEmployees = new ArrayList<Employee>();
		String empSql = "SELECT E.FIRST_NAME,E.LAST_NAME,E.BIRTH_DATE FROM EMPLOYEES E,SALARIES S WHERE E.HIRE_DATE<TO_DATE(?,?) AND S.EMP_NO=E.EMP_NO AND S.SALARY > ?";
		try {
			lstEmployees = jdbcTemplate.query(empSql, new Object[] { hireDate, dateFormat, salary },
					new EmployeeRowMapper());
		} catch (Exception ex) {
			lstEmployees = null;
		}
		return lstEmployees;
	}
	
	public String deleteEmployeeRecord(String date) {
		String message = "";
		String deleteEmployeeSql = "DELETE FROM SALARIES S WHERE S.EMP_NO IN (SELECT E.EMP_NO FROM EMPLOYEES E WHERE E.HIRE_DATE < TO_DATE(?,?))";
		try {
			int rows = jdbcTemplate.update(deleteEmployeeSql, new Object[] { date, dateFormat });
			message = rows + " Rows deleted";
		} catch (Exception ex) {
			message = "Error occured during operation : " + ex.getMessage();
		}
		return message;
	}
}

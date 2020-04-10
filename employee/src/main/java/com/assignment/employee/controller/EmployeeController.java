package com.assignment.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.employee.models.Employee;
import com.assignment.employee.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/employees/{departmentId}")
	public List<Employee> getEmployeeNames(@PathVariable String departmentId)
	{
		List<Employee> lstEmployees = employeeService.getEmployeeDetails(departmentId);
		return lstEmployees;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/employees")
	public List<Employee> getEmployeeBeforeHireDate(@RequestParam String hireDate, @RequestParam Long salary)
	{
		List<Employee> lstEmployees = employeeService.getEmployeesBeforeHireDate(hireDate,salary);
		return lstEmployees;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/deleteEmployees")
	public String deleteEmployeeRecord(@RequestParam String hireDate)
	{
		String message = employeeService.deleteEmployeeRecord(hireDate);
		return message;
	}
		
}

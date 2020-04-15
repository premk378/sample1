package com.assignment.employee.models;

public class EmployeeDetails {
	private Long empNo;
	private String firstName;
	private String lastName;
	private String dateOfBirth;
	private Long salary;
	private String hireDate;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Long getEmpNo() {
		return empNo;
	}
	public void setEmpNo(Long empNo) {
		this.empNo = empNo;
	}
	public Long getSalary() {
		return salary;
	}
	public void setSalary(Long salary) {
		this.salary = salary;
	}
	public String getHireDate() {
		return hireDate;
	}
	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}
	public EmployeeDetails(Long empNo, String firstName, String lastName, String dateOfBirth, Long salary,
			String hireDate) {
		this.empNo = empNo;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.salary = salary;
		this.hireDate = hireDate;
	}
	
	public EmployeeDetails()
	{
		
	}
}

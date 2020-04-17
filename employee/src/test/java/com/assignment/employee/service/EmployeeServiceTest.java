package com.assignment.employee.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import com.assignment.employee.mapper.EmployeeMapper;
import com.assignment.employee.models.Employee;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {
	
	@Mock
	private JdbcTemplate jdbcTemplate;
	
	@InjectMocks
	private EmployeeService employeeService;
	
	@Before
    public void setUp() {
        ReflectionTestUtils.setField(employeeService, "dateFormat", "YYYY-MM-DD");
    }
	
	@Test
	public void getEmployeeDetails_Test()
	{
		Employee e1 = new Employee("Sumant", "Peac", "1952-04-19");
		Employee e2 = new Employee("Duangkaew", "Piveteau", "1963-06-01");
		Employee e3 = new Employee("Otmar", "Herbst", "1956-12-13");
		when(jdbcTemplate.query(anyString(), any(Object[].class), any(EmployeeMapper.class)))
				.thenReturn(Arrays.asList(e1, e2, e3));
		assertTrue(employeeService.getEmployeeDetails("d006").size() == 3);
		assertEquals(Arrays.asList(e1, e2, e3), employeeService.getEmployeeDetails("d006"));
	}
	
}

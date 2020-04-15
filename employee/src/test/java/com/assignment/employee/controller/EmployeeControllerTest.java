package com.assignment.employee.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.assignment.employee.models.Employee;
import com.assignment.employee.models.EmployeeDetails;
import com.assignment.employee.service.EmployeeService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeController.class)
class EmployeeControllerTest {
	
	private MediaType TEXT_PLAIN_UTF8 = new MediaType(MediaType.TEXT_PLAIN,Charset.forName("utf8"));

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EmployeeService employeeService;
	
	@Test
	void getEmployeeNames_test() throws Exception {
		Employee e1 = new Employee("Sumant", "Peac", "1952-04-19");
		Employee e2 = new Employee("Duangkaew", "Piveteau", "1963-06-01");
		Employee e3 = new Employee("Otmar", "Herbst", "1956-12-13");

		when(employeeService.getEmployeeDetails(anyString())).thenReturn(Arrays.asList(e1,e2,e3));
			
		mockMvc.perform(get("/employees/d006"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].firstName", is("Sumant")))
		        .andExpect(jsonPath("$[0].lastName", is("Peac")))
		        .andExpect(jsonPath("$[0].dateOfBirth", is("1952-04-19")));
		
		verify(employeeService,times(1)).getEmployeeDetails(anyString());
				
	}
	
	@Test
	void getEmployeesHiredAfterDate_test() throws Exception{
		EmployeeDetails e1 = new EmployeeDetails(10011L, "Mary", "Sluis", "1953-11-07", 80013L, "1990-01-22");
		EmployeeDetails e2 = new EmployeeDetails(10012L, "Patricio", "Bridgland", "1960-10-04", 81025L, "1992-12-18");
		
		when(employeeService.getEmployeesHiredAfterDate(anyString(), anyLong())).thenReturn(Arrays.asList(e1,e2));
		
		mockMvc.perform(get("/employees?date=1990-01-01&salary=80000"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[1].firstName", is("Patricio")))
		        .andExpect(jsonPath("$[1].lastName", is("Bridgland")))
		        .andExpect(jsonPath("$[1].dateOfBirth", is("1960-10-04")))
		        .andExpect(jsonPath("$[1].empNo", is(10012)))
		        .andExpect(jsonPath("$[1].hireDate", is("1992-12-18")))
		        .andExpect(jsonPath("$[1].salary", is(81025)));
		
		verify(employeeService,times(1)).getEmployeesHiredAfterDate(anyString(), anyLong());
	}

	@Test
	void deleteEmployeeRecord_test() throws Exception
	{
		when(employeeService.deleteEmployeeRecord(anyString())).thenReturn(anyInt() + " Rows deleted");
		MvcResult mvcResult = mockMvc.perform(get("/deleteEmployees?date=1985-11-22"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(TEXT_PLAIN_UTF8))
				.andReturn();
		
		assertTrue(mvcResult.getResponse().getContentAsString().contains("Rows deleted"));
		verify(employeeService,times(1)).deleteEmployeeRecord(anyString());
	}
}

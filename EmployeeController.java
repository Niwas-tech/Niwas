package com.example.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Employee")
public class EmployeeController {
	
	private EmployeeService employeeService;
	
	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService=employeeService;
	}
	@PostMapping("insert")
	private Employee create(@RequestBody Employee emp) {
		return employeeService.create(emp);
	}
	
	@GetMapping("display")
	private List<Employee> display(){
		return employeeService.display();
		
	}

}

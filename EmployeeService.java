package com.example.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@org.springframework.transaction.annotation.Transactional
	public Employee create(Employee emp) {
		return employeeRepository.save(emp);
	}
	
	public List<Employee> display(){
		return employeeRepository.findAll();
	}

}

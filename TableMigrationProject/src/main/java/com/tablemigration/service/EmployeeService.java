package com.tablemigration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tablemigration.ModelandEntity.Employee;
import com.tablemigration.employeeRepository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	/*Search All Employees*/
	public List<Employee> getAllEmployees(){
		List<Employee> employee = employeeRepository.findAll();
		return employee;
	}
	
	public Employee addEmp(Employee emp) {
		return employeeRepository.saveAndFlush(emp);
	}
	
	

}

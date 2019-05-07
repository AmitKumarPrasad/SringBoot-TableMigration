package com.tablemigration.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tablemigration.ModelandEntity.Employee;
import com.tablemigration.service.EmployeeService;

@RestController
@RequestMapping("/migration")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping("/serachEmployees")
	public List<Employee> getEmployees(){
		return employeeService.getAllEmployees();
	}
	/*Create Employee*/
	@PostMapping("/createEmployee")
	public Employee addEmployee(@Valid @RequestBody Employee emp) {
		return employeeService.addEmp(emp);
	}

}

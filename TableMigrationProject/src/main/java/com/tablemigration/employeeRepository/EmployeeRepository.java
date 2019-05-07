package com.tablemigration.employeeRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tablemigration.ModelandEntity.Employee;
@Repository("EmpRepository")
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}

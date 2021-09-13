package com.ITO.SpringbootBackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ITO.SpringbootBackend.Model.Employee;
@Repository
public interface EmpoyeeRepository extends JpaRepository<Employee, Long>
{
	public Employee getEmployeeByFirstName(String FirstName);
	
}

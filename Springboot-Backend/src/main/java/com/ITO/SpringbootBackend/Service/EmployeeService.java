package com.ITO.SpringbootBackend.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.ITO.SpringbootBackend.Model.Employee;
import com.ITO.SpringbootBackend.Repository.EmpoyeeRepository;

@Service
public class EmployeeService 
{
	@Autowired
	private EmpoyeeRepository  employeeRepository;
	//get all employees
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees()
	{
		return employeeRepository.findAll();
	}
	//get employee by ID
	public Optional<Employee> getEmployeeById(long id)
	{
		return employeeRepository.findById(id);
	}
	public Employee createEmployee(Employee employee)
	{
		employee.setRecord_Created_Time(new Date());
		return employeeRepository.save(employee);
	}
	public Employee updateEmployee(Employee employee, long id)
	{
		Optional<Employee> em=employeeRepository.findById(id);
		em.get().setFirstName(employee.getFirstName());
		em.get().setLastName(employee.getLastName());
		//em.get().setRecord_Created_Time(employee.getRecord_Created_Time());
		em.get().setRecord_Modified_Time(new Date());
		em.get().setRecord_Created_User(employee.getRecord_Created_User());
		return employeeRepository.save(em.get());	
	}
	public void deleteEmployee(long id)
	{
		employeeRepository.deleteById(id);
	}
	
	public Employee findByFirstName(String firstName)
	{
		Employee emm=employeeRepository.getEmployeeByFirstName(firstName);
		System.out.println(emm);
		return emm;
	}
}

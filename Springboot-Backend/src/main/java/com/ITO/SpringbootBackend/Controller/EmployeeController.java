package com.ITO.SpringbootBackend.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ITO.SpringbootBackend.Model.Employee;
import com.ITO.SpringbootBackend.Service.EmployeeService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/v1/")
public class EmployeeController
{	
	/*
	@Autowired
	private EmpoyeeRepository  employeeRepository;
	//get all employees
	@GetMapping("/employees")
	*/
	@Autowired
	private EmployeeService employeeService;
	

	@GetMapping("/employees")
	public List<Employee> getAllEmployees()
	{
		return employeeService.getAllEmployees();
	}
	
	@GetMapping("/employees/{id}")
	public Optional<Employee> getEmployeeById(@PathVariable("id") long id)
	{	
		return employeeService.getEmployeeById(id);
	}
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee)
	{
		return employeeService.createEmployee(employee);
	}
	
	@PutMapping("/employees/{id}")
	public Employee updateEmployee(@RequestBody Employee employee,@PathVariable("id") long id)
	{	
		return employeeService.updateEmployee(employee,id);
	}
	@DeleteMapping("/employees/{id}")
	public void deleteEmployee(@PathVariable("id") long id)
	{
		employeeService.deleteEmployee(id);
	}
	
	@GetMapping("/employees/firstName/{firstName}")
	public Employee searchByEmployeeName(@PathVariable("firstName") String firstName)
	{
		return employeeService.findByFirstName(firstName);	
	}	
}

package com.backend.EmployeeService.Controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.backend.EmployeeService.Entity.Employee;
import com.backend.EmployeeService.Entity.LoginDTO;
import com.backend.EmployeeService.Entity.Tax;
import com.backend.EmployeeService.Exceptions.EmployeeNotFoundException;
import com.backend.EmployeeService.Exceptions.InputErrorException;
import com.backend.EmployeeService.Service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService empService;
	
	@GetMapping("/employee/admin/all")
	public List<Employee> getAllEmployees(){
		return empService.getAllEmployee();
	}
	
	@GetMapping("/employee/normal/{id}")
	public Optional<Employee> getEmployeeById(@PathVariable Integer id) throws EmployeeNotFoundException{
		return empService.getEmployeeById(id);
	}
	
	@PostMapping("/employee/admin/save")
	public Employee saveEmployee(@RequestBody @Valid Employee employee) throws InputErrorException{
		return empService.saveEmployee(employee);
	}
	
	@PutMapping("/employee/normal/update/{id}")
	public Employee updateEmployee(@RequestBody Employee employee, @PathVariable Integer id) throws EmployeeNotFoundException{
		return empService.updateEmployee(employee, id);
	}
	
	@DeleteMapping("/employee/admin/delete/{id}")
	public String deleteEmployee(@PathVariable Integer id) {
		return empService.deleteEmployee(id);
	}

	@GetMapping("/employee/normal/leaves/{id}")
	public Employee getEmployeeWithLeaves(@PathVariable Integer id) throws EmployeeNotFoundException{
		return empService.getAllLeavesByUserId(id);
	}
	
	@GetMapping("/employee/normal/tax/{id}")
		public Tax getTaxByEmpId(@PathVariable Integer id) throws JsonMappingException, JsonProcessingException, EmployeeNotFoundException{
		return empService.getTaxByEmpId(id);
	}
	
	@PostMapping("/employee/login")
	public Employee logInService(@RequestBody LoginDTO logInDto) throws EmployeeNotFoundException {
		return empService.logInService(logInDto);
	}
}

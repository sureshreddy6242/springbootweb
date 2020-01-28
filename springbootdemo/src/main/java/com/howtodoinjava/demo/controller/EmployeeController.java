package com.howtodoinjava.demo.controller;

import java.util.ArrayList;
import java.util.List;

import com.howtodoinjava.demo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.howtodoinjava.demo.model.Employee;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class EmployeeController {
	
	/*@RequestMapping("/getEmployees")
    public List<Employee> getEmployees() 
    {
		List<Employee> employeesList = new ArrayList<Employee>();
		employeesList.add(new Employee(1,"lokesh","gupta","howtodoinjava@gmail.com"));
		return employeesList;
    }*/


	@GetMapping("/addUser")
	public String sendForm(User user) {

		return "addUser";
	}



	@PostMapping("/addUser")
	public String processForm(User user) {

		return "showMessage";
	}


	@RequestMapping("/toxics")
	public RedirectView addtoxis(User user) {

		RedirectView view = new RedirectView();
		view.setUrl("http://localhost:8083/addUser");

		return view;
	}



}

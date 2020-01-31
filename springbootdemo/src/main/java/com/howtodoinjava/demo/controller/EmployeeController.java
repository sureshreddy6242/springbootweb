package com.howtodoinjava.demo.controller;

import java.util.ArrayList;
import java.util.List;

import com.howtodoinjava.demo.model.User;
import eu.rekawek.toxiproxy.Proxy;
import eu.rekawek.toxiproxy.ToxiproxyClient;
import eu.rekawek.toxiproxy.model.ToxicDirection;
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

	ToxiproxyClient client;
	Proxy httpProxy;

	public void setup() throws Exception {

		//toxyproxycliet ip
		client = new ToxiproxyClient("3.18.108.18", 8474);
		//client = new ToxiproxyClient("2.3.45.6", 8474);

		// creating toxi proxy for balandeloader
		httpProxy = client.createProxy("http-tproxy", "3.18.108.18:8888", "3.17.129.12:5000");
	}

	@GetMapping("/addUser")
	public String sendForm(User user) {

		return "addUser";
	}



	@PostMapping("/addUser")
	public String processForm(User user) {

		return "showMessage";
	}

//bandwidth
	@RequestMapping("/bandwidth")
	public RedirectView addtoxis(User user) throws Exception {
		setup();

		httpProxy.toxics().latency("latency-toxic", ToxicDirection.DOWNSTREAM, 12_000).setJitter(15);


		RedirectView view = new RedirectView();
		view.setUrl("http://3.18.108.18:8888/");

		return view;
	}

//latency
	@RequestMapping("/latency")
	public RedirectView addtoxis2(User user) {

		RedirectView view = new RedirectView();
		view.setUrl("http://3.18.108.18:26379/");

		return view;
	}




}

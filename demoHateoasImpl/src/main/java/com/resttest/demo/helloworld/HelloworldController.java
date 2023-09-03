package com.resttest.demo.helloworld;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloworldController {
	
	@GetMapping("/helloworld")
	public String getMessage() {
		return "Helloworld";
	}
	
	
	@GetMapping("/helloworldBean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");
	}
	
	//helloworld/pathvar/mahesh
	@GetMapping("/helloworld/pathvar/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello %s", name));
	}
}

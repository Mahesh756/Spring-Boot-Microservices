package com.resttest.demo.helloworld;


import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloworldController {
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping("/helloworld")
	public String getMessage() {
		return "Helloworld";
	}
	
	
	@GetMapping("/helloworldBean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");
	}
	
	// the below method say gd mrng, but we are not sure about from which place the gd mrng will get 
	// it might be from france or italy or from india right. so we need customize it 
	@GetMapping("/helloworld-internationalized")
	public String helloworldInternationalized(){
		return messageSource.getMessage("good.morining.message",null,LocaleContextHolder.getLocale());
	}
//	another way - enable with SessionLocaleResolver
//	public String helloworldInternationalized(@RequestHeader(name = "Accept-Language",required=false) Locale locale) {
//		return messageSource.getMessage("good.morining.message",null,locale);
//	}
	
	
	
	
	//helloworld/pathvar/mahesh
	@GetMapping("/helloworld/pathvar/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello %s", name));
	}
}

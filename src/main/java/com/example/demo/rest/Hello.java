package com.example.demo.rest;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Hello {
	//@GetMapping //Servicio dispopnible por el metodo Get, toma como la url la base localhost:8080/
	@RequestMapping(value="hello", method=RequestMethod.GET)//http://localhost:8080/hello
	public String hello () {
		return "Hello mundo";
	}
}

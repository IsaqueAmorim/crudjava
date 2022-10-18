package com.encontro.inscricao.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class HomeResources {

	@GetMapping
	public String getHello() {
		return "Helo Porraaaa";
	}
}

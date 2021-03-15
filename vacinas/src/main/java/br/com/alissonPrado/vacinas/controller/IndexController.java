package br.com.alissonPrado.vacinas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String index() {

		System.out.println("Entou na Index Controller.");
		return "index";

	}

}

package br.com.alissonPrado.vacinas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alissonPrado.vacinas.controller.dto.VacinaDto;
import br.com.alissonPrado.vacinas.modelo.Vacina;
import br.com.alissonPrado.vacinas.repository.VacinaRepository;

@RestController
@RequestMapping("/vacinas")
public class VacinaColtroller {
	
	@Autowired
	private VacinaRepository vacinaRepository;
	
	// @RequestMapping(value = "/topicos", method = RequestMethod.GET)
	@GetMapping
	public List<VacinaDto> listaVacinas() {
		
		List<Vacina> vacinas = vacinaRepository.findAll();
		return VacinaDto.converteListaVacinaDto(vacinas);
		
	}

}

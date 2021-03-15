package br.com.alissonPrado.vacinas.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.alissonPrado.vacinas.modelo.Vacina;

public class VacinaDto {

	private Long id;
	private String nome;

	public VacinaDto(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public VacinaDto(Vacina vacina) {
		this.id = vacina.getId();
		this.nome = vacina.getNome();
	}	
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	public static List<VacinaDto> converteListaVacinaDto(List<Vacina> vacinas) {
		return vacinas.stream().map(VacinaDto::new).collect(Collectors.toList());
	}

}

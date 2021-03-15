package br.com.alissonPrado.vacinas.controller.dto;

import java.time.LocalDate;

import br.com.alissonPrado.vacinas.modelo.Cidadao;

public class CidadaoDto {

	private Long id;
	private String nome;
	private String email;
	private String cpf;
	private LocalDate dataNascimento;

	public CidadaoDto() {
		// TODO Auto-generated constructor stub
	}

	public CidadaoDto(Long id, String nome, String email, String cpf, LocalDate dataNascimento) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}

	public CidadaoDto(Cidadao cidadao) {
		super();
		this.id = cidadao.getId();
		this.nome = cidadao.getNome();
		this.email = cidadao.getEmail();
		this.cpf = cidadao.getCpf();
		this.dataNascimento = cidadao.getDataNascimento();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getCpf() {
		return cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

}

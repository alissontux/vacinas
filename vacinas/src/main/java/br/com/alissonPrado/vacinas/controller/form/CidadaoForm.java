package br.com.alissonPrado.vacinas.controller.form;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

public class CidadaoForm {

	@NotNull
	@NotBlank(message = "O nome não pode ser vazio.")
	@Size(min = 3, max = 50, message = "O nome precisar ser entre 3 a 50 caracteres")
	private String nome;

	@NotNull
	@NotBlank(message = "O e-mail não pode ser vazio.")
	@Email
	private String email;

	@NotNull
	@NotBlank(message = "O CPF não pode ser vazio.")
	@CPF
	private String cpf;

	@NotNull
	@DateTimeFormat
	private LocalDate dataNascimento;

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}

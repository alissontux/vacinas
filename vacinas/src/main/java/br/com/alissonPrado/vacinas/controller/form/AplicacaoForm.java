package br.com.alissonPrado.vacinas.controller.form;

import java.time.LocalDateTime;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AplicacaoForm {

	@NotNull
	private Long idVacina;

	@NotNull
	@CPF
	private String cpf;

	@NotNull
	@Min(1)
	@Max(2)
	private Integer dose;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime dataAplicacao;

	public Long getIdVacina() {
		return idVacina;
	}

	public void setIdVacina(Long idVacina) {
		this.idVacina = idVacina;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Integer getDose() {
		return dose;
	}

	public void setDose(Integer dose) {
		this.dose = dose;
	}

	public LocalDateTime getDataAplicacao() {
		return dataAplicacao;
	}

	public void setDataAplicacao(LocalDateTime dataAplicacao) {
		this.dataAplicacao = dataAplicacao;
	}

}

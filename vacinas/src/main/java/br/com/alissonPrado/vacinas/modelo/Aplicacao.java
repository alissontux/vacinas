package br.com.alissonPrado.vacinas.modelo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Aplicacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Vacina vacina;

	@ManyToOne
	private Cidadao cidadao;

	private Integer dose;

	@Column(nullable = false)
	private LocalDateTime dataAplicacao;

	public Aplicacao() {
		// TODO Auto-generated constructor stub
	}

	public Aplicacao(Vacina vacina, Cidadao cidadao, Integer dose, LocalDateTime dataAplicacao) {
		this.vacina = vacina;
		this.cidadao = cidadao;
		this.dose = dose;
		this.dataAplicacao = dataAplicacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Vacina getVacina() {
		return vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}

	public Cidadao getCidadao() {
		return cidadao;
	}

	public void setCidadao(Cidadao cidadao) {
		this.cidadao = cidadao;
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

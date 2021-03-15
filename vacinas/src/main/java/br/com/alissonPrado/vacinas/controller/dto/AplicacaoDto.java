package br.com.alissonPrado.vacinas.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alissonPrado.vacinas.modelo.Aplicacao;
import br.com.alissonPrado.vacinas.modelo.Cidadao;
import br.com.alissonPrado.vacinas.modelo.Vacina;

public class AplicacaoDto {

	private Long id;
	private Vacina vacina;
	private Cidadao cidadao;
	private Integer dose;
	private LocalDateTime dataAplicacao;

	public AplicacaoDto() {
		// TODO Auto-generated constructor stub
	}

	public AplicacaoDto(Aplicacao aplicacaoVacina) {
		this.id = aplicacaoVacina.getId();
		this.vacina = aplicacaoVacina.getVacina();
		this.cidadao = aplicacaoVacina.getCidadao();
		this.dose = aplicacaoVacina.getDose();
		this.dataAplicacao = aplicacaoVacina.getDataAplicacao();
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

	public static List<AplicacaoDto> converteListaAplicacaoVacinaDto(List<Aplicacao> listAplicacaoVacina) {
		return listAplicacaoVacina.stream().map(AplicacaoDto::new).collect(Collectors.toList());
	}

}

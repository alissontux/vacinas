package br.com.alissonPrado.vacinas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.alissonPrado.vacinas.modelo.Aplicacao;

public interface AplicacaoRepository extends JpaRepository<Aplicacao, Long> {
	
	List<Aplicacao> findByCidadao_Cpf(String cpf);
	
	@Query("SELECT count(a) FROM Aplicacao a WHERE a.vacina.id = :idVacina and a.cidadao.cpf = :cpf and a.dose = :dose")
	int findOcorrencia(@Param("idVacina") Long idVacina, @Param("cpf") String cpf, @Param("dose") Integer dose);

}

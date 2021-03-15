package br.com.alissonPrado.vacinas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alissonPrado.vacinas.modelo.Cidadao;

public interface CidadaoRepository extends JpaRepository<Cidadao, Long>{
	
	Optional<Cidadao> findByCpf(String cpf);
	
	Optional<Cidadao> findByEmail(String email);

}

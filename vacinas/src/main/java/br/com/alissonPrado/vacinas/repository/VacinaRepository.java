package br.com.alissonPrado.vacinas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alissonPrado.vacinas.modelo.Vacina;

public interface VacinaRepository extends JpaRepository<Vacina, Long> {

}
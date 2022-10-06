package br.com.ifooddevweek.sacola.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ifooddevweek.sacola.models.Sacola;

@Repository
public interface SacolaRepository extends JpaRepository<Sacola, Long> {

}

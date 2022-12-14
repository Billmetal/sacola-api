package br.com.ifooddevweek.sacola.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ifooddevweek.sacola.models.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

}

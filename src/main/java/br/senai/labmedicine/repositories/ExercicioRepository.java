package br.senai.labmedicine.repositories;

import br.senai.labmedicine.models.Exercicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExercicioRepository extends JpaRepository<Exercicio, Long> {
}

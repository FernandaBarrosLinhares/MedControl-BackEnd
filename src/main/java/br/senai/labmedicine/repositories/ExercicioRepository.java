package br.senai.labmedicine.repositories;

import br.senai.labmedicine.models.Exercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExercicioRepository extends JpaRepository<Exercicio, Long> {
    List<Exercicio> findAllByPaciente_NomeCompletoContainingIgnoreCase(String nomePaciente);//TODO TESTAR

    List<Exercicio> findAllByPacienteId(Long id);
}

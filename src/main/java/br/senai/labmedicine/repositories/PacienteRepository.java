package br.senai.labmedicine.repositories;

import br.senai.labmedicine.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    List<Paciente> findByNomeCompleto(String nomeCompleto);
}

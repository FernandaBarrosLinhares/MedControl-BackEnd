package br.senai.labmedicine.repositories;

import br.senai.labmedicine.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}

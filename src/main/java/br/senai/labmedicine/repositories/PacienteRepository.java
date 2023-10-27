package br.senai.labmedicine.repositories;

import br.senai.labmedicine.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    List<Paciente> findByNomeCompletoContainingIgnoreCase(String nomeCompleto);

    @Query(value = "SELECT * FROM PACIENTE WHERE nome_completo ILIKE %:filtro% or email ILIKE %:filtro% or cpf ILIKE :filtro% or telefone ILIKE :filtro%",nativeQuery = true)
    List<Paciente> buscarComFiltro(String filtro);

    Boolean existsByCpf(String cpf);
    Boolean existsByEmail(String email);
}

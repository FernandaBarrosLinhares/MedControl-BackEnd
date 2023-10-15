package br.senai.labmedicine.repositories;

import br.senai.labmedicine.models.Dieta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DietaRepository extends JpaRepository<Dieta,Long> {
    List<Dieta> findAllByPacienteNomeCompletoOrderByDataAscHorario(String nomeCompleto);

}

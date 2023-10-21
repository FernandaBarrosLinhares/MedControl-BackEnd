package br.senai.labmedicine.repositories;

import java.util.List;

import br.senai.labmedicine.models.Dieta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.senai.labmedicine.models.Exame;

public interface ExameRepository extends JpaRepository<Exame, Long> {
	List<Exame> findAllByPacienteNomeCompletoOrderByDataAscHorario(String nomeCompleto);

	List<Exame> findAllByPacienteId(Long id);
	
}

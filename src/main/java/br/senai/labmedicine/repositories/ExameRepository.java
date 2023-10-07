package br.senai.labmedicine.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.senai.labmedicine.models.Exame;

public interface ExameRepository extends JpaRepository<Exame, Long> {

	// TODO alterar para buscar exame por nome do Usuario
	@Query(value = "SELECT e.id,e.nome,e.data,e.horario,e.tipo,e.laboratio, e.url_documento, e.resultado, e.status,e.paciente_id FROM dieta d inner join paciente as p on p.nome_completo = :nomePaciente order by e.data,e.horario",nativeQuery = true)
    List<Exame> BuscaPorNomePaciente(String nomePaciente);
	
}

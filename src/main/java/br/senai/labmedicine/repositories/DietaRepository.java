package br.senai.labmedicine.repositories;

import br.senai.labmedicine.models.Dieta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DietaRepository extends JpaRepository<Dieta,Long> {

    @Query(value = "SELECT d.id,d.nome,d.data,d.horario,d.tipo_dieta,d.descricao,d.status,d.paciente_id FROM dieta d inner join paciente as p on p.nome_completo = :nomePaciente order by d.data,d.horario",nativeQuery = true)
    List<Dieta> BuscaPorNomePaciente(String nomePaciente);

}

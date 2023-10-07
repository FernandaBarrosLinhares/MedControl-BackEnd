package br.senai.labmedicine.repositories;

import br.senai.labmedicine.models.Exercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExercicioRepository extends JpaRepository<Exercicio, Long> {
//    @Query(value = "SELECT d.id,d.nome,d.data,d.horario,d.tipo_exercicio_enum,d.quantidade_por_semana,d.descricao,d.status,d.paciente_id FROM exercicio d inner join paciente as p on p.nome_completo = :nomePaciente order by d.data,d.horario",nativeQuery = true)
//    List<Exercicio> BuscaPorNomePaciente(String nomePaciente);
    List<Exercicio> findByPaciente_NomeCompleto(String nomePaciente);
}

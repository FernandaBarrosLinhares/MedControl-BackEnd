package br.senai.labmedicine.repositories;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.senai.labmedicine.models.Medicamento;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {

	@Query(value = "select m.* from medicamento as m inner join consulta as c on m.id = c.medicamento_id inner join paciente as p on c.paciente_id = p.id where p.nome_completo like %:nome%", nativeQuery = true)
	List<Medicamento> buscarPorNomePaciente(String nome);

	@Query(value = "select m.* from medicamento as m inner join consulta as c on c.medicamento_id = m.id where c.medicamento_id = :id",nativeQuery = true)
	List<Medicamento> existsByMedicamentoId(long id);
}

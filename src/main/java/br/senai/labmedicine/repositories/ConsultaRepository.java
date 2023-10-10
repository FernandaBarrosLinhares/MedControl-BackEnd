package br.senai.labmedicine.repositories;

import br.senai.labmedicine.models.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultaRepository  extends JpaRepository<Consulta, Long> {

    //TODO BUSCA PELAS MEDICACOES(LISTA), PACIENTE E USUARIO

    List<Consulta> findAllByPacienteId(Long id);
}

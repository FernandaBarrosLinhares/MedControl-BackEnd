package br.senai.labmedicine.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.senai.labmedicine.dtos.exame.ExameResponseDTO;
import br.senai.labmedicine.dtos.paciente.PacienteResponseDTO;
import br.senai.labmedicine.dtos.prontuario.ProntuarioRequestDTO;
import br.senai.labmedicine.dtos.dieta.DietaResponseDTO;
import br.senai.labmedicine.dtos.exercicio.ExercicioResponseDTO;
import br.senai.labmedicine.dtos.prontuario.ProntuarioResponseDTO;

@Service
public class ProntuarioService {
	@Autowired
	private PacienteService pacienteService;

	@Autowired
	private DietaService dietaService;

	@Autowired
	private ExameService exameService;

	@Autowired
	private ExercicioService exercicioService;

	// TODO descomentar abaixo quando consulta estiver disponível
	// @Autowired
	// private ConsultaService consultaService;

	public List<ProntuarioResponseDTO> buscarTodos() {
		List<ProntuarioResponseDTO> prontuariosDTO = new ArrayList<>();
		List<PacienteResponseDTO> pacientes = this.pacienteService.buscarTodos();

		for (PacienteResponseDTO paciente : pacientes) {

			ProntuarioResponseDTO prontuarioDTO = montarProntuarioResponseDTO(paciente);

			prontuariosDTO.add(prontuarioDTO);
		}

		return prontuariosDTO;
	}

	public List<ProntuarioResponseDTO> buscarPorIdOuNome(ProntuarioRequestDTO prontuario) {
		List<ProntuarioResponseDTO> prontuariosDTO = new ArrayList<>();
		boolean nomePreenchido = false;
		boolean idPreenchido = false;

		if (prontuario.getNomeCompletoPaciente() != null && !prontuario.getNomeCompletoPaciente().isBlank())
			nomePreenchido = true;
		if (prontuario.getPacienteId() > 0)
			idPreenchido = true;

		if (nomePreenchido && idPreenchido) {
			PacienteResponseDTO pacienteDTO = this.pacienteService.buscarPorId(prontuario.getPacienteId());
			if (!pacienteDTO.getNomeCompleto().equals(prontuario.getNomeCompletoPaciente())) {
				new IllegalArgumentException("O id e nome devem ser do mesmo paciente.");
			}			
		} 
		if (idPreenchido) {
			PacienteResponseDTO pacienteDTO = this.pacienteService.buscarPorId(prontuario.getPacienteId());
			prontuariosDTO.add(montarProntuarioResponseDTO(pacienteDTO));
		} else if (nomePreenchido) {

			List<PacienteResponseDTO> pacientesDTO = this.pacienteService.buscarPorNome(
				prontuario.getNomeCompletoPaciente()
			);

			for (PacienteResponseDTO paciente : pacientesDTO) {

				ProntuarioResponseDTO prontuarioDTO = montarProntuarioResponseDTO(paciente);

				prontuariosDTO.add(prontuarioDTO);
			}
		} else {

			prontuariosDTO = this.buscarTodos();
		}

		return prontuariosDTO;
	}

	private ProntuarioResponseDTO montarProntuarioResponseDTO(PacienteResponseDTO paciente) {
		ProntuarioResponseDTO prontuarioDTO = new ProntuarioResponseDTO();

		prontuarioDTO.setPaciente(paciente);
		prontuarioDTO.setDietas(this.buscarDietasPorNomePaciente(paciente.getNomeCompleto()));
		prontuarioDTO.setExames(this.buscarExamesPorNomePaciente(paciente.getNomeCompleto()));
		prontuarioDTO.setExercicios(this.buscarExerciciosPorNomePaciente(paciente.getNomeCompleto()));
		// TODO descomentar abaixo quando consulta estiver disponível
		// prontuariosDTO.setConsultas(this.buscarConsultasPorNomePaciente(paciente.getNomeCompleto()));

		return prontuarioDTO;
	}

	private List<DietaResponseDTO> buscarDietasPorNomePaciente(String nomeCompleto) {
		return this.dietaService.buscarDietaPorPaciente(nomeCompleto);
	}

	private List<ExameResponseDTO> buscarExamesPorNomePaciente(String nomeCompleto) {
		return this.exameService.buscarExamePorPaciente(nomeCompleto);
	}

	private List<ExercicioResponseDTO> buscarExerciciosPorNomePaciente(String nomeCompleto) {
		return this.exercicioService.buscarExercicioPorPaciente(nomeCompleto);
	}

	// TODO descomentar abaixo quando consulta estiver disponível
	// private List<ConsultaReponseDTO> buscarConsultasPorNomePaciente(String
	// nomeCompleto) {
	// return this.consultaService.buscarConsultaPorPaciente(nomeCompleto);
	// }
}

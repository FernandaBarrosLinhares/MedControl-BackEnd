package br.senai.labmedicine.services;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

import br.senai.labmedicine.dtos.consulta.ConsultaResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.senai.labmedicine.dtos.exame.ExameResponseDTO;
import br.senai.labmedicine.dtos.paciente.PacienteResponseDTO;
import br.senai.labmedicine.dtos.dieta.DietaResponseDTO;
import br.senai.labmedicine.dtos.exercicio.ExercicioResponseDTO;
import br.senai.labmedicine.dtos.medicamento.MedicamentoResponseDTO;
import br.senai.labmedicine.dtos.prontuario.ProntuarioResponseDTO;

@Service
public class ProntuarioService {

	@Autowired
	private MedicamentoService medicamentoService;

	@Autowired
	private PacienteService pacienteService;

	@Autowired
	private DietaService dietaService;

	@Autowired
	private ExameService exameService;

	@Autowired
	private ExercicioService exercicioService;
	
	 @Autowired
	 private ConsultaService consultaService;

	public List<ProntuarioResponseDTO> buscarTodos(Long idUsuarioLogado) throws AccessDeniedException {
		List<ProntuarioResponseDTO> prontuariosDTO = new ArrayList<>();
		List<PacienteResponseDTO> pacientes = this.pacienteService.buscarTodos();

		for (PacienteResponseDTO paciente : pacientes) {

			ProntuarioResponseDTO prontuarioDTO = montarProntuarioResponseDTO(idUsuarioLogado,paciente);

			prontuariosDTO.add(prontuarioDTO);
		}

		return prontuariosDTO;
	}

	public List<ProntuarioResponseDTO> buscarPorIdOuNome(Long idUsuarioLogado,String nomeCompletoPaciente, Long pacienteId) throws AccessDeniedException {
		List<ProntuarioResponseDTO> prontuariosDTO = new ArrayList<>();
		boolean nomePreenchido = false;
		boolean idPreenchido = false;

		if (nomeCompletoPaciente != null && !nomeCompletoPaciente.isBlank())
			nomePreenchido = true;
		if (pacienteId != null)
			idPreenchido = true;

		if (nomePreenchido && idPreenchido) {
			PacienteResponseDTO pacienteDTO = this.pacienteService.buscarPorId(pacienteId);
			if (!pacienteDTO.getNomeCompleto().equals(nomeCompletoPaciente)) {
                throw new IllegalArgumentException("O id e nome devem ser do mesmo paciente.");
			}
		}
		if (idPreenchido) {
			PacienteResponseDTO pacienteDTO = this.pacienteService.buscarPorId(pacienteId);
			prontuariosDTO.add(montarProntuarioResponseDTO(idUsuarioLogado,pacienteDTO));
		} else if (nomePreenchido) {

			List<PacienteResponseDTO> pacientesDTO = this.pacienteService.buscarPorNome(nomeCompletoPaciente);

			for (PacienteResponseDTO paciente : pacientesDTO) {

				ProntuarioResponseDTO prontuarioDTO = montarProntuarioResponseDTO(idUsuarioLogado,paciente);

				prontuariosDTO.add(prontuarioDTO);
			}
		} else {

			prontuariosDTO = this.buscarTodos(idUsuarioLogado);
		}

		return prontuariosDTO;
	}

	private ProntuarioResponseDTO montarProntuarioResponseDTO(Long idUsuarioLogado,PacienteResponseDTO paciente) throws AccessDeniedException {
		ProntuarioResponseDTO prontuarioDTO = new ProntuarioResponseDTO();

		prontuarioDTO.setPaciente(paciente);
		prontuarioDTO.setDietas(this.buscarDietasPorNomePaciente(paciente.getNomeCompleto()));
		prontuarioDTO.setExames(this.buscarExamesPorNomePaciente(idUsuarioLogado,paciente.getNomeCompleto()));
		prontuarioDTO.setExercicios(this.buscarExerciciosPorNomePaciente(paciente.getNomeCompleto()));
		prontuarioDTO.setConsultas(this.buscarConsultasPorIdPaciente(idUsuarioLogado,paciente.getId()));
		prontuarioDTO.setMedicamentos(this.buscarMedicamentosPorNomePaciente(paciente.getNomeCompleto()));

		return prontuarioDTO;
	}

	private List<DietaResponseDTO> buscarDietasPorNomePaciente(String nomeCompleto) {
		return this.dietaService.buscarDietaPorPaciente(nomeCompleto);
	}

	private List<ExameResponseDTO> buscarExamesPorNomePaciente(Long idUsuarioLogado,String nomeCompleto) throws AccessDeniedException {
		return this.exameService.buscarExamePorPaciente(idUsuarioLogado,nomeCompleto);
	}

	private List<ExercicioResponseDTO> buscarExerciciosPorNomePaciente(String nomeCompleto) {
		return this.exercicioService.buscarExercicioPorPaciente(nomeCompleto);
	}


	 private List<ConsultaResponseDTO> buscarConsultasPorIdPaciente(Long idUsuarioLogado, Long id) throws AccessDeniedException {
	 return this.consultaService.buscarConsultaPorPaciente(idUsuarioLogado,id);
	 }

	 private List<MedicamentoResponseDTO> buscarMedicamentosPorNomePaciente(String nomeCompleto) {
		return this.medicamentoService.buscar(nomeCompleto);
	 }
}

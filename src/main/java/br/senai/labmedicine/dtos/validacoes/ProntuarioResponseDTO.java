package br.senai.labmedicine.dtos.validacoes;

import java.util.List;

import br.senai.labmedicine.dtos.consulta.ConsultaResponseDTO;
import br.senai.labmedicine.dtos.dieta.DietaResponseDTO;
import br.senai.labmedicine.dtos.exame.ExameResponseDTO;
import br.senai.labmedicine.dtos.exercicio.ExercicioResponseDTO;
import br.senai.labmedicine.dtos.medicamento.MedicamentoResponseDTO;
import br.senai.labmedicine.dtos.paciente.PacienteResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProntuarioResponseDTO {
	private List<ConsultaResponseDTO> consultas;
	private List<DietaResponseDTO> dietas;
	private List<ExameResponseDTO> exames;
	private List<ExercicioResponseDTO> exercicios;
	private List<MedicamentoResponseDTO> medicamentos;
	private PacienteResponseDTO paciente;
}

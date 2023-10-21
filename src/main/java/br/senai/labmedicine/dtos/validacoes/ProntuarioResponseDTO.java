package br.senai.labmedicine.dtos.validacoes;

import java.util.List;

import br.senai.labmedicine.dtos.exame.ExameResponseDTO;
import br.senai.labmedicine.dtos.paciente.PacienteResponseDTO;
import br.senai.labmedicine.dtos.dieta.DietaResponseDTO;
import br.senai.labmedicine.dtos.exercicio.ExercicioResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProntuarioResponseDTO {
	private PacienteResponseDTO paciente;
	private List<DietaResponseDTO> dietas;
	private List<ExameResponseDTO> exames;
	private List<ExercicioResponseDTO> exercicios;
	// TODO descomentar abaixo quando consulta estiver disponível
	// private List<ConsultaResponseDTO> consultas;
}

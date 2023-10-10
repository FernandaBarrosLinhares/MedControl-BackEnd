package br.senai.labmedicine.dtos;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProntuarioRequestDTO {

	private Long pacienteId;

	@Size(min = 8, max = 64, message = "O nome do paciente deve ter entre 8 e 64 caracteres")
	private String nomeCompletoPaciente;

}

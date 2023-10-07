package br.senai.labmedicine.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExameEdicaoDTO {
	
	@NotBlank(message = "Nome do exame deve ser informado")
	@Size(min = 8, max = 64, message = "Campo nome deve conter de 8 a 64 caracteres")
	private String nome;

	@NotNull(message = "Tipo do exame deve ser informado")
	@Size(min = 4, max = 32, message = "Campo tipo deve conter de 4 a 32 caracteres")
	private String tipo;

	@NotNull(message = "Laboratório deve ser informado")
	@Size(min = 4, max = 32, message = "Campo laboratório deve conter de 4 a 32 caracteres")
	private String laboratorio;

	@Size(max = 150, message = "Campo URL do documento deve conter no máximo 150 caracteres")
	private String url_documento;

	@Column(nullable = false, length = 1024)
	@NotNull(message = "Resultado deve ser informado")
	@Size(min = 16, max = 1024, message = "Campo resultado deve conter de 16 a 1024 caracteres")
	private String resultado;

	@NotNull(message = "Status deve ser informado")
	private final Boolean status = true;

	@NotNull(message = "Paciente obrigatório")
	private PacienteResponseDTO paciente;

}

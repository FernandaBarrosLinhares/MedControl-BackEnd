package br.senai.labmedicine.dtos.exame;

import java.time.LocalDate;
import java.time.LocalTime;

import br.senai.labmedicine.dtos.paciente.PacienteResponseDTO;
import br.senai.labmedicine.models.Exame;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExameResponseDTO {

	private Long id;

	private String nome;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;

	private LocalTime horario;

	private String tipo;

	private String laboratorio;

	private String url_documento;

	private String resultado;

	private Boolean status;

	private PacienteResponseDTO paciente;

	public ExameResponseDTO(Exame exame) {
		this.id = exame.getId();
		this.nome = exame.getNome();
		this.data = exame.getData();
		this.horario = exame.getHorario();
		this.tipo = exame.getTipo();
		this.laboratorio = exame.getLaboratorio();
		this.url_documento = exame.getUrl_documento();
		this.resultado = exame.getResultado();
		this.status = exame.getStatus();
		this.paciente = new PacienteResponseDTO(exame.getPaciente());
	}
}

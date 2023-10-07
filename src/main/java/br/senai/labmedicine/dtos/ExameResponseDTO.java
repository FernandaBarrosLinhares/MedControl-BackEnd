package br.senai.labmedicine.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

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
}

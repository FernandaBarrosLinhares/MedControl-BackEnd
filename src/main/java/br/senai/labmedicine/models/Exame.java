package br.senai.labmedicine.models;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Exame {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false,length = 150)
	private String nome;

	@Column(nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;

	@Column(nullable = false)
	@DateTimeFormat(pattern = "HH:mm:ss")
	private LocalTime horario;

	@Column(nullable = false, length = 32)	
	private String tipo;

	@Column(nullable = false, length = 32)	
	private String laboratorio;

	@Column(length = 150)
	private String url_documento;

	@Column(nullable = false, length = 1024)
	private String resultado;

	@Column(nullable = false)
	private Boolean status;

	@ManyToOne
    @JoinColumn(name = "paciente_id",referencedColumnName = "id",nullable = false)
	private Paciente paciente;

}

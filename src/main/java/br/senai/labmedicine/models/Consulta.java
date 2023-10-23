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
@AllArgsConstructor
@NoArgsConstructor
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 100)
    private String motivo;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime horario;
    @Column(nullable = false,length = 1024)
    private String descricao;
    @Column(nullable = false,length = 256)
    private String dosagensPrecaucoes;
    @Column(nullable = false)
    private Boolean status;
    @ManyToOne
    @JoinColumn(name = "paciente_id",referencedColumnName = "id",nullable = false)
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name = "usuario_id",referencedColumnName = "id",nullable = false)
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "medicamento_id",referencedColumnName = "id",nullable = true)
    private Medicamento medicamento;
}

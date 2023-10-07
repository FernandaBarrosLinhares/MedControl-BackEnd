package br.senai.labmedicine.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @Column(nullable = false,length = 100)
    private String indicadorMedicacao;
    @Column(nullable = false,length = 256)
    private String dosagensPrecaucoes;
    @Column(nullable = false)
    private Boolean status;

    //TODO relacionamento usuario
    //TODO relacionamento medicamento
    @ManyToOne
    @JoinColumn(name = "paciente_id",referencedColumnName = "id",nullable = false)
    private Paciente paciente;
}

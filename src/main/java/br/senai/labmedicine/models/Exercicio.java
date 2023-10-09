package br.senai.labmedicine.models;

import br.senai.labmedicine.enums.TipoExercicioEnum;
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
@NoArgsConstructor
@AllArgsConstructor
public class Exercicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 100)
    private String nome;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime horario;

    @Column(nullable = false)
    private TipoExercicioEnum tipoExercicioEnum;

    @Column(nullable = false)
    private int quantidadePorSemana;

    @Column(length = 1024)
    private String descricao;

    @Column(nullable = false)
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "paciente_id",referencedColumnName = "id",nullable = false)
    private Paciente paciente;
}

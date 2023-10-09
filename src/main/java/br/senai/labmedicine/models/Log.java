package br.senai.labmedicine.models;

import br.senai.labmedicine.dtos.log.LogCadastroDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime horario;

    @Column(nullable = false,length = 1000)
    private String mensagem;

    public Log(LogCadastroDTO log) {
        this.id = null;
        this.data = log.getData();
        this.horario = log.getHorario();
        this.mensagem = log.getMensagem();
    }
}

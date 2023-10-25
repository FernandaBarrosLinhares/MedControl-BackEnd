package br.senai.labmedicine.dtos.exercicio;

import br.senai.labmedicine.dtos.paciente.PacienteResponseDTO;
import br.senai.labmedicine.enums.TipoExercicioEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExercicioResponseDTO {

    private Long id;

    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    private LocalTime horario;

    private TipoExercicioEnum tipoExercicioEnum;

    private int quantidadePorSemana;

    private String descricao;

    private Boolean status;

    private PacienteResponseDTO paciente;
}

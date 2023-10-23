package br.senai.labmedicine.dtos.exercicio;

import br.senai.labmedicine.dtos.paciente.PacienteResponseDTO;
import br.senai.labmedicine.enums.TipoExercicioEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExercicioCadastroDTO {

    @NotBlank(message = "Nome do exercício deve ser informado")
    @Size(min = 5,max = 100,message = "Campo nome deve conter de 5 a 100 caracteres")
    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "Data deve ser informada")
    private LocalDate data;

    @NotNull(message = "Hora deve ser informada")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime horario;

    @NotNull(message = "Tipo de exercício deve ser informado")
    private TipoExercicioEnum tipoExercicioEnum;

    @NotNull(message = "Quantidade de exercícios deve ser informada")
    private int quantidadePorSemana;

    @NotBlank(message = "A descrição deve ser informada")
    @Size(min =10, max = 1000,message = "Campo descrição deve conter no máximo 100 caracteres")
    private String descricao;

    @Setter(AccessLevel.NONE)
    private final Boolean status = true;

    @NotNull(message = "Paciente obrigatório")
    private PacienteResponseDTO paciente;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ExercicioCadastroDTO(String data,String horario){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatoHorario = DateTimeFormatter.ofPattern("HH:mm:ss");
        if (data != null && !data.isBlank()) {
            try {
                this.data = LocalDate.from(formatter.parse(data));
            }catch (DateTimeParseException e){
                throw new DateTimeParseException("Data inválida", "", 0);
            }
        }
        if (horario != null && !horario.isBlank()){
            try {
                this.horario = LocalTime.from(formatoHorario.parse(horario));
            }catch (DateTimeParseException e){
                throw new DateTimeParseException("Horário inválido", "", 0);
            }
        }
    }
}

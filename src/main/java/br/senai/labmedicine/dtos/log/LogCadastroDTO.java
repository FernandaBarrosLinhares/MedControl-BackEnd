package br.senai.labmedicine.dtos.log;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class LogCadastroDTO {

    @NotNull(message = "Data do log dever ser informada.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    @NotNull(message = "Horario do log dever ser informada.")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime horario;

    @NotBlank(message = "Mensagem do log dever ser informada.")
    @Size(min=10,max = 1000,message ="O log deve ter no mínimo 10 e máximo 1000 caracteres")
    private String mensagem;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public LogCadastroDTO(String data,String horario){
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

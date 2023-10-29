package br.senai.labmedicine.dtos.consulta;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import br.senai.labmedicine.dtos.paciente.PacienteResponseDTO;
import br.senai.labmedicine.dtos.medicamento.MedicamentoResponseDTO;
import br.senai.labmedicine.dtos.usuario.UsuarioResponseDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaCadastroDTO {

    @NotBlank(message = "Motivo da consulta deve ser informado")
    @Size(min = 8, message = "Campo nome deve conter 8 caracteres")
    private String motivo;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "Data deve ser informada")
    private LocalDate data;
    @NotNull(message = "Hora deve ser informada")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime horario;
    @Size(min = 16,max = 1024,message = "Campo descrição deve conter de 16 a 1024 caracteres")
    private String descricao;
    @NotBlank (message= "Campo dosagens e precauçoes  deve ser obrigatório")
    @Size(min = 16,max = 256,message = "Campo dosagens e precauçoes  deve conter de 16 a 256 caracteres")
    private String dosagensPrecaucoes;
    @Setter(AccessLevel.NONE)
    private final Boolean status = true;
    @NotNull(message = "Paciente obrigatório")
    private PacienteResponseDTO paciente;
    @NotNull(message = "Usuário obrigatório")
    private UsuarioResponseDTO usuario;
    @NotNull(message = "Medicamento obrigatório")
    private MedicamentoResponseDTO medicamento;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ConsultaCadastroDTO(String data,String horario){
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

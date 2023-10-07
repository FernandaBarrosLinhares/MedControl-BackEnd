package br.senai.labmedicine.dtos.consulta;


import br.senai.labmedicine.dtos.PacienteResponseDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaAtualizacaoDTO {

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
    @NotBlank
    private String indicadorMedicacao;
    @Size(min = 16,max = 256,message = "Campo dosagens e precauçoes  deve conter de 16 a 256 caracteres")
    private String dosagensPrecaucoes;
    @Setter(AccessLevel.NONE)
    private final Boolean status = true;
    @NotNull(message = "Paciente obrigatório")
    private PacienteResponseDTO paciente;
}

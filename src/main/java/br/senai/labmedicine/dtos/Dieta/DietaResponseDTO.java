package br.senai.labmedicine.dtos.Dieta;

import br.senai.labmedicine.dtos.PacienteResponseDTO;
import br.senai.labmedicine.enums.TipoDietaEnum;
import br.senai.labmedicine.models.Dieta;
import br.senai.labmedicine.models.Paciente;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.extern.java.Log;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DietaResponseDTO {

    private Long id;

    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    private LocalTime horario;

    @Getter
    private TipoDietaEnum tipoDieta;

    private String descricao;

    private Boolean status;

    private PacienteResponseDTO paciente;

    public DietaResponseDTO(Dieta dieta) {
        this.id = dieta.getId();
        this.nome = dieta.getNome();
        this.data = dieta.getData();
        this.horario = dieta.getHorario();
        this.tipoDieta = dieta.getTipoDieta();
        this.descricao = dieta.getDescricao();
        this.status = dieta.getStatus();
        this.paciente = new PacienteResponseDTO(dieta.getPaciente());
    }
}

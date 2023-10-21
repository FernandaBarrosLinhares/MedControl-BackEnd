package br.senai.labmedicine.dtos.dieta;

import br.senai.labmedicine.dtos.paciente.PacienteResponseDTO;
import br.senai.labmedicine.enums.TipoDietaEnum;
import br.senai.labmedicine.models.Dieta;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

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

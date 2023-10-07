package br.senai.labmedicine.dtos.consulta;

import br.senai.labmedicine.dtos.PacienteResponseDTO;
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
public class ConsultaResponseDTO {
    private Long id;
    private String motivo;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;
    private LocalTime horario;
    private String descricao;
    private String indicadorMedicacao;
    private String dosagensPrecaucoes;
    private Boolean status;
    private PacienteResponseDTO paciente;
}

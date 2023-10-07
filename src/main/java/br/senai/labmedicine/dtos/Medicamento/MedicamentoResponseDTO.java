package br.senai.labmedicine.dtos.Medicamento;

import br.senai.labmedicine.enums.TipoMedicamentosEnum;
import br.senai.labmedicine.enums.UnidadeMedicamentosEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicamentoResponseDTO {
    private Long id;
    private String nomeMedicamento;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime horario;
    private TipoMedicamentosEnum tipoMedicamento;
    private Double quantidade;
    private UnidadeMedicamentosEnum unidadeMedicamentos;
    private String observacao;
    private Boolean status;
}
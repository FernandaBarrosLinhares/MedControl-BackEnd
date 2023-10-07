package br.senai.labmedicine.dtos.Medicamento;

import br.senai.labmedicine.enums.TipoMedicamentosEnum;
import br.senai.labmedicine.enums.UnidadeMedicamentosEnum;
import jakarta.validation.constraints.*;
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
public class MedicamentoCadastroDTO {

    @NotBlank(message = "Nome do medicamento deve ser informado.")
    @Size(min = 5, max = 100, message = "Nome deve ter entre 5 e 100 caracteres.")
    private String nomeMedicamento;

    @NotNull(message = "Data deve ser informada.")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    @NotNull(message = "Horário deve ser informado.")
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime horario;

    @NotNull(message = "Tipo de medicamento deve ser informado.")
    private TipoMedicamentosEnum tipoMedicamento;

    @NotNull(message = "Quantidade deve ser informada.")
    @DecimalMax("100000000000.00") @DecimalMin("0.00")
    private Double quantidade;

    @NotNull(message = "Unidade de medicamento deve ser informada.")
    private UnidadeMedicamentosEnum unidadeMedicamentos;

    @NotBlank(message = "Observações deve ser informada.")
    @Size(min = 10, max = 1000, message = "Nome deve ter entre 10 e 1000 caracteres.")
    private String observacao;

    @NotNull
    private Boolean status;
}

package br.senai.labmedicine.dtos.Medicamento;

import br.senai.labmedicine.enums.TipoMedicamentosEnum;
import br.senai.labmedicine.enums.UnidadeMedicamentosEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicamentoAtualizacaoDTO {
    @NotNull
    private Long id;
    @NotBlank(message = "Nome do medicamento deve ser informado.")
    @Size(min = 5, max = 100, message = "Nome deve ter entre 5 e 100 caracteres.")
    private String nomeMedicamento;

    @NotNull(message = "Tipo de medicamento deve ser informado.")
    private TipoMedicamentosEnum tipoMedicamento;

    @NotBlank(message = "Quantidade deve ser informada.")
    private Double quantidade;

    @NotNull(message = "Unidade de medicamento deve ser informada.")
    private UnidadeMedicamentosEnum unidadeMedicamentos;

    @NotBlank(message = "Observações deve ser informada.")
    @Size(min = 10, max = 1000, message = "Nome deve ter entre 10 e 1000 caracteres.")
    private String observacao;

    @NotNull
    private Boolean status;
}

package br.senai.labmedicine.dtos.dieta;


import br.senai.labmedicine.dtos.paciente.PacienteResponseDTO;
import br.senai.labmedicine.enums.TipoDietaEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class DietaAtualizacaoDTO {

    @NotBlank(message = "Nome da dieta deve ser informado.")
    @Size(min = 5,max = 100,message = "Campo nome deve conter de 5 a 100 caracteres.")
    private String nome;

    @NotNull(message = "Tipo da dieta deve ser informada.")
    private TipoDietaEnum tipoDieta;

    @Size(max = 100,message = "Campo descrição deve conter no máximo 100 caracteres.")
    private String descricao;

    @NotNull(message = "O status deve ser informado.")
    private Boolean status;

}

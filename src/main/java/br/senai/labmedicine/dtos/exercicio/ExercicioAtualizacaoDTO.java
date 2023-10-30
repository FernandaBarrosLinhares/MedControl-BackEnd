package br.senai.labmedicine.dtos.exercicio;

import br.senai.labmedicine.enums.TipoExercicioEnum;
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
public class ExercicioAtualizacaoDTO {
    @NotBlank(message = "Nome do exercício deve ser informado.")
    @Size(min = 5,max = 100,message = "Campo nome deve conter de 5 a 100 caracteres.")
    private String nome;

    @NotNull(message = "Tipo do exercício deve ser informado.")
    private TipoExercicioEnum tipoExercicioEnum;

    @NotBlank
    @Size(min = 10, max = 1000,message = "Campo descrição deve conter no minimo 10 caracteres e no máximo 100.")
    private String descricao;

    @NotNull(message = "O status deve ser informado.")
    private Boolean status;

    @NotNull(message = "Quantidade de exercícios deve ser informada")
    private int quantidadePorSemana;
}

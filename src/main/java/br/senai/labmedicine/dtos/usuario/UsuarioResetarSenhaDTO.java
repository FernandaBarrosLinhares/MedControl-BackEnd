package br.senai.labmedicine.dtos.usuario;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResetarSenhaDTO extends UsuarioLoginDTO{
    @NotNull(message = "Id deve ser informado!")
    Long id;
}

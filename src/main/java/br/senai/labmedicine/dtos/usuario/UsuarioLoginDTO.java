package br.senai.labmedicine.dtos.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioLoginDTO {

    @NotBlank(message ="Email é obrigatório")
    @Email
    @Size(max = 150,message = "Máximo de 150 caracteres")
    private String email;

    @NotBlank(message = "Senha é obrigatória.")
    @Size(min = 6,message = "Mínimo de 6 caracteres.")
    private String senha;
}

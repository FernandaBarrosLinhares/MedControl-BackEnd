package br.senai.labmedicine.dtos.usuario;

import br.senai.labmedicine.enums.GeneroEnum;
import br.senai.labmedicine.enums.TipoUsuarioEnum;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioCadastroDTO {

    @NotBlank(message ="Nome completo é obrigatório")
    @Size(max = 150,message = "Máximo de 150 caracteres")
    private String nomeCompleto;

    @NotNull(message= "Gênero é obrigatório")
    private GeneroEnum genero;

    @CPF
    @NotBlank (message = "Cpf é obrigatório")
    private String cpf;

    @NotBlank (message ="Telefone é obrigatório")
    @Pattern(regexp = "\\d{11}",message = "Telefone: informe apenas números com DDD")
    private String telefone;

    @NotBlank (message ="Email é obrigatório")
    @Email
    @Size(max = 150,message = "Máximo de 150 caracteres")
    private String email;

    @NotNull(message = "Tipo de usuário é obrigatório.")
    private TipoUsuarioEnum tipoUsuario;

    @NotBlank(message = "Senha é obrigatória.")
    @Size(min = 6,message = "Mínimo de 6 caracteres.")
    private String senha;


}

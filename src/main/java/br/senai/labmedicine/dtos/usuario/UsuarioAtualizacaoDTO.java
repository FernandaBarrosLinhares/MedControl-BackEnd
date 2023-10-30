package br.senai.labmedicine.dtos.usuario;

import br.senai.labmedicine.enums.GeneroEnum;
import br.senai.labmedicine.enums.TipoUsuarioEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioAtualizacaoDTO {

    @NotBlank(message ="Nome completo é obrigatório")
    @Size(max = 150,message = "Máximo de 150 caracteres")
    private String nomeCompleto;

    @NotNull(message= "Gênero é obrigatório")
    private GeneroEnum genero;

    @NotBlank (message ="Telefone é obrigatório")
    @JsonFormat(pattern = "\\d{11}")
    private String telefone;

    @NotBlank(message = "Senha é obrigatória.")
    @Size(min = 6,message = "Mínimo de 6 caracteres.")
    private String senha;

    @NotNull(message = "Tipo de usuário é obrigatório.")
    private TipoUsuarioEnum tipoUsuario;

    @NotNull(message = "Status deve ser informado")
    private Boolean status;
}

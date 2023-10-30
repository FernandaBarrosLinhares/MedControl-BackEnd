package br.senai.labmedicine.dtos.usuario;

import br.senai.labmedicine.enums.GeneroEnum;
import br.senai.labmedicine.enums.TipoUsuarioEnum;
import br.senai.labmedicine.models.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {

    private Long id;

    @NotBlank(message ="Nome completo é obrigatório")
    @Size(max = 150,message = "Máximo de 150 caracteres")
    private String nomeCompleto;

    @NotNull(message= "Gênero é obrigatório")
    private GeneroEnum genero;

    @CPF
    @NotBlank (message = "Cpf é obrigatório")
    private String cpf;

    @NotBlank (message ="Telefone é obrigatório")
    @JsonFormat(pattern = "\\d{11}")
    private String telefone;

    @NotBlank (message ="Email é obrigatório")
    @Email
    @Size(max = 150,message = "Máximo de 150 caracteres")
    private String email;

    @NotNull (message= "Status é obrigatório")
    private Boolean status;

    @NotNull(message = "Tipo de usuário é obrigatório.")
    private TipoUsuarioEnum tipoUsuario;

    public UsuarioResponseDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nomeCompleto = usuario.getNomeCompleto();
        this.genero = usuario.getGenero();
        this.cpf = usuario.getCpf();
        this.telefone = usuario.getTelefone();
        this.email = usuario.getEmail();
        this.status = usuario.getStatus();
        this.tipoUsuario = usuario.getTipoUsuario();
    }
}

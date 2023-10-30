package br.senai.labmedicine.models;

import br.senai.labmedicine.dtos.usuario.UsuarioCadastroDTO;
import br.senai.labmedicine.dtos.usuario.UsuarioResponseDTO;
import br.senai.labmedicine.enums.GeneroEnum;
import br.senai.labmedicine.enums.TipoUsuarioEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario extends Pessoa{

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private TipoUsuarioEnum tipoUsuario;

    public Usuario(UsuarioCadastroDTO usuarioDTO) {
        super(null,usuarioDTO.getNomeCompleto(),usuarioDTO.getGenero(),usuarioDTO.getCpf(),
                usuarioDTO.getTelefone(), usuarioDTO.getEmail(), true);
        this.senha = usuarioDTO.getSenha();
        this.tipoUsuario = usuarioDTO.getTipoUsuario();
    }
}

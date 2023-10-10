package br.senai.labmedicine.services;

import br.senai.labmedicine.dtos.usuario.*;
import br.senai.labmedicine.models.Usuario;
import br.senai.labmedicine.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;
import java.nio.file.AccessDeniedException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioResponseDTO login(UsuarioLoginDTO usuarioLogin) throws AuthenticationException,NoSuchAlgorithmException {
        Usuario usuarioBd = this.usuarioRepository.findByEmail(usuarioLogin.getEmail());
        if(usuarioBd == null || !usuarioBd.getSenha().equals(criptografarSenha(usuarioLogin.getSenha())) || !usuarioBd.getEmail().equals(usuarioLogin.getEmail())) {
            throw new AuthenticationException("Usuário ou senha inválido.");
        }
        return new UsuarioResponseDTO(usuarioBd);
    }

    public void resetarSenha(UsuarioResetarSenhaDTO usuarioReset) throws AuthenticationException, NoSuchAlgorithmException{
        Usuario usuarioBd = this.usuarioRepository.findByEmail(usuarioReset.getEmail());
        if (usuarioBd == null || !usuarioBd.getEmail().equals(usuarioReset.getEmail()) || !usuarioBd.getId().equals(usuarioReset.getId())){
            throw new AuthenticationException("Erro ao resetar a senha.");
        }
        usuarioBd.setSenha(criptografarSenha(usuarioReset.getSenha()));
        this.usuarioRepository.save(usuarioBd);
    }

    public UsuarioResponseDTO cadastrarUsuario(Long idUsuarioLogado,UsuarioCadastroDTO novoUsuario) throws AccessDeniedException,NoSuchAlgorithmException {
        Usuario usuarioLogado = this.usuarioRepository.findById(idUsuarioLogado).orElseThrow(()->new EntityNotFoundException("Usuário não encontrado"));
        if(!usuarioLogado.getTipoUsuario().getDescricao().equals("Administrador")){
            throw new AccessDeniedException("Usuário sem acesso!");
        }
        novoUsuario.setSenha(this.criptografarSenha(novoUsuario.getSenha()));
        Usuario usuario = new Usuario(novoUsuario);
        return new UsuarioResponseDTO(this.usuarioRepository.save(usuario));
    }

    public List<UsuarioResponseDTO> buscarTodosUsuarios(){
        return this.usuarioRepository.findAll().stream().map(UsuarioResponseDTO::new).toList();
    }
    public UsuarioResponseDTO buscarUsuarioPorId(Long id){
        Usuario usuario = this.usuarioRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Usuário não encontrado."));
        return new UsuarioResponseDTO(usuario);
    }

    public UsuarioResponseDTO buscarUsuarioPorEmail(String email) throws AuthenticationException {
        Usuario usuario = this.usuarioRepository.findByEmail(email);
        if(usuario == null){
            throw new InternalError("Erro ao buscar.");
        }
        return new UsuarioResponseDTO(usuario);
    }

    public UsuarioResponseDTO atualizarUsuario(Long id,UsuarioAtualizacaoDTO usuarioAtualizado) throws NoSuchAlgorithmException {
        Usuario usuario = this.usuarioRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Usuário não encontrado."));
        BeanUtils.copyProperties(usuarioAtualizado,usuario);
        usuario.setSenha(criptografarSenha(usuarioAtualizado.getSenha()));
        return new UsuarioResponseDTO(this.usuarioRepository.save(usuario));
    }

    public void deletarUsuario(Long id,Long idUsuarioLogado) throws AccessDeniedException {
        Usuario usuarioLogado = this.usuarioRepository.findById(idUsuarioLogado).orElseThrow(()->new EntityNotFoundException("Usuário Logado não encontrado."));
        Usuario usuarioParaDeletar = this.usuarioRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Usuário não encontrado."));
        if(!usuarioLogado.getTipoUsuario().getDescricao().equals("Administrador")){
            throw new AccessDeniedException("Usuário sem acesso!");
        }
        if(usuarioLogado.getId().equals(usuarioParaDeletar.getId())){
            throw new AccessDeniedException("Id informado não pode ser deletado");
        }
        this.usuarioRepository.deleteById(id);
    }

    public String criptografarSenha(String senha) throws NoSuchAlgorithmException {
        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte[] senhaByte = algorithm.digest(senha.getBytes());
        return Arrays.toString(senhaByte);
    }
}

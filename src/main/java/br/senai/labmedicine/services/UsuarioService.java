package br.senai.labmedicine.services;

import java.nio.file.AccessDeniedException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.senai.labmedicine.dtos.log.LogCadastroDTO;
import br.senai.labmedicine.dtos.usuario.UsuarioAtualizacaoDTO;
import br.senai.labmedicine.dtos.usuario.UsuarioCadastroDTO;
import br.senai.labmedicine.dtos.usuario.UsuarioLoginDTO;
import br.senai.labmedicine.dtos.usuario.UsuarioResetarSenhaDTO;
import br.senai.labmedicine.dtos.usuario.UsuarioResponseDTO;
import br.senai.labmedicine.models.Consulta;
import br.senai.labmedicine.models.Usuario;
import br.senai.labmedicine.repositories.ConsultaRepository;
import br.senai.labmedicine.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LogService logService;

    @Autowired
    private ConsultaRepository consultaRepository;


    public UsuarioResponseDTO login(UsuarioLoginDTO usuarioLogin) throws AuthenticationException,NoSuchAlgorithmException {
        Usuario usuarioBd = this.usuarioRepository.findByEmail(usuarioLogin.getEmail());
        if(usuarioBd == null || !usuarioBd.getSenha().equals(criptografarSenha(usuarioLogin.getSenha())) || !usuarioBd.getEmail().equals(usuarioLogin.getEmail())) {
            throw new AuthenticationException("Usuário ou senha inválido.");
        }
        String mensagem = "O usuário: (id: "+usuarioBd.getId()+") "+usuarioBd.getNomeCompleto()+" Logou no Sistema";
        logService.cadastrarLog(new LogCadastroDTO(LocalDate.now(), LocalTime.now(),mensagem));
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
        checarDisponibilidadeEmailECpf(novoUsuario.getCpf(), novoUsuario.getEmail());
        Usuario usuarioLogado = this.usuarioRepository.findById(idUsuarioLogado).orElseThrow(()->new EntityNotFoundException("Usuário não encontrado"));
        if(!usuarioLogado.getTipoUsuario().getDescricao().equals("Administrador")){
            throw new AccessDeniedException("Usuário sem acesso!");
        }
        novoUsuario.setSenha(this.criptografarSenha(novoUsuario.getSenha()));
        Usuario usuario = new Usuario(novoUsuario);
        usuario = this.usuarioRepository.save(usuario);
        String mensagem = "O usuário: (id: "+usuarioLogado.getId()+") "+usuarioLogado.getNomeCompleto()+" Cadastrou o usuário: (id: "+usuario.getId()+") "+usuario.getNomeCompleto();
        logService.cadastrarLog(new LogCadastroDTO(LocalDate.now(), LocalTime.now(),mensagem));
        return new UsuarioResponseDTO(usuario);
    }

    public List<UsuarioResponseDTO> buscarTodosUsuarios(Long idUsuarioLogado) throws AccessDeniedException {
        UsuarioResponseDTO usuarioLogado = this.buscarUsuarioPorId(idUsuarioLogado);
        if(!usuarioLogado.getTipoUsuario().getDescricao().equals("Administrador")){
            throw new AccessDeniedException("Usuário sem acesso!");
        }
        return this.usuarioRepository.findAll().stream().map(UsuarioResponseDTO::new).toList();
    }
    public UsuarioResponseDTO buscarUsuarioPorId(Long id){
        Usuario usuario = this.usuarioRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Usuário não encontrado."));
        return new UsuarioResponseDTO(usuario);
    }

    public UsuarioResponseDTO buscarUsuarioPorEmail(String email) throws AccessDeniedException {
        Usuario usuario = this.usuarioRepository.findByEmail(email);
        if(usuario == null){
            throw new InternalError("Nenhum usuário encontrado para o email fornecido.");
        }
        return new UsuarioResponseDTO(usuario);
    }

    public UsuarioResponseDTO atualizarUsuario(Long idUsuarioLogado,Long id,UsuarioAtualizacaoDTO usuarioAtualizado) throws NoSuchAlgorithmException, AccessDeniedException {
        Usuario usuario = this.usuarioRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Usuário não encontrado."));
        Usuario usuarioLogado = this.usuarioRepository.findById(idUsuarioLogado).orElseThrow(()->new EntityNotFoundException("Usuário Logado não encontrado."));
        if(!usuarioLogado.getTipoUsuario().getDescricao().equals("Administrador")){
            throw new AccessDeniedException("Usuário sem acesso!");
        }
        BeanUtils.copyProperties(usuarioAtualizado,usuario);
        usuario.setSenha(criptografarSenha(usuarioAtualizado.getSenha()));
        this.inativarConsulta(usuarioLogado.getId(),usuario.getId(),usuarioAtualizado.getStatus());
        String mensagem = "O usuário: (id: "+usuarioLogado.getId()+") "+usuarioLogado.getNomeCompleto()+" atualizou o usuário: (id: "+usuario.getId()+") "+usuario.getNomeCompleto();
        logService.cadastrarLog(new LogCadastroDTO(LocalDate.now(), LocalTime.now(),mensagem));
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
        try {
            this.usuarioRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Usuário em uso não pode ser deletado.");
        }
        String mensagem = "O usuário: (id: "+usuarioLogado.getId()+") "+usuarioLogado.getNomeCompleto()+" deletou o usuário: (id: "+usuarioParaDeletar.getId()+") "+usuarioParaDeletar.getNomeCompleto();
        logService.cadastrarLog(new LogCadastroDTO(LocalDate.now(), LocalTime.now(),mensagem));
    }

    public String criptografarSenha(String senha) throws NoSuchAlgorithmException {
        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte[] senhaByte = algorithm.digest(senha.getBytes());
        return Arrays.toString(senhaByte);
    }

    public void inativarConsulta(Long idUsuarioLogado,Long idUsuario,boolean status){
        List<Consulta> consultas = this.consultaRepository.findAllByUsuarioId(idUsuario);
        for(Consulta consulta: consultas){
            consulta.setStatus(status);
            this.consultaRepository.save(consulta);
        }
    }

    public void checarDisponibilidadeEmailECpf(String cpf,String email){
        boolean cpfExiste = this.usuarioRepository.existsByCpf(cpf);
        boolean emailExiste = this.usuarioRepository.existsByEmail(email);
        if(cpfExiste){
            throw new DataIntegrityViolationException("CPF já cadastrado.");
        }else if(emailExiste){
            throw new DataIntegrityViolationException("Email já cadastrado.");
        }
    }

    public List<UsuarioResponseDTO> buscarProFiltro(Long idUsuarioLogado,String filtro) throws AccessDeniedException {
        Usuario usuarioLogado = this.usuarioRepository.findById(idUsuarioLogado).orElseThrow(()->new EntityNotFoundException("Usuário Logado não encontrado."));
        if(!usuarioLogado.getTipoUsuario().getDescricao().equals("Administrador")){
            throw new AccessDeniedException("Usuário sem acesso!");
        }
        List<UsuarioResponseDTO> usuariosDTO;
        usuariosDTO = this.usuarioRepository.buscarComFiltro(filtro).stream().map(UsuarioResponseDTO::new).toList();
        return usuariosDTO;
    }
}

package br.senai.labmedicine.controllers;

import br.senai.labmedicine.dtos.usuario.*;
import br.senai.labmedicine.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;
import java.io.UnsupportedEncodingException;
import java.nio.file.AccessDeniedException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrarUsuario(@RequestBody @Valid UsuarioCadastroDTO novoUsuario,
                                                               @RequestHeader(value = "idUsuarioLogado",required = true)Long idUsuarioLogado) throws AccessDeniedException, NoSuchAlgorithmException {
        return new ResponseEntity<>(this.usuarioService.cadastrarUsuario(idUsuarioLogado,novoUsuario), HttpStatus.CREATED);
    }

    @GetMapping("/buscarporemail/{email}")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorEmail(@PathVariable String email) throws AuthenticationException {
        return new ResponseEntity<>(this.usuarioService.buscarUsuarioPorEmail(email),HttpStatus.OK);
    }

    @PostMapping("/{login}")
    public ResponseEntity<UsuarioResponseDTO> loginUsuario(@RequestBody @Valid UsuarioLoginDTO usuarioLogin) throws AuthenticationException, NoSuchAlgorithmException {
        return new ResponseEntity<>(this.usuarioService.login(usuarioLogin),HttpStatus.OK);
    }

    @PatchMapping("/resetarsenha")
    public ResponseEntity<Void> resetarSenha(@RequestBody @Valid UsuarioResetarSenhaDTO usuario) throws AuthenticationException, NoSuchAlgorithmException {
        this.usuarioService.resetarSenha(usuario);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorId(@PathVariable Long id){
        return new ResponseEntity<>(this.usuarioService.buscarUsuarioPorId(id),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> buscarTodosUsuarios(){
        return new ResponseEntity<>(this.usuarioService.buscarTodosUsuarios(),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(@PathVariable Long id,@RequestBody @Valid UsuarioAtualizacaoDTO usuarioAtualizacaoDTO) throws NoSuchAlgorithmException {
        return new ResponseEntity<>(this.usuarioService.atualizarUsuario(id,usuarioAtualizacaoDTO),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id,@RequestHeader(value = "idUsuarioLogado",required = true)Long idUsuarioLogado) throws AccessDeniedException {
        this.usuarioService.deletarUsuario(id,idUsuarioLogado);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}

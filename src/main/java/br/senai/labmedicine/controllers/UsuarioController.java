package br.senai.labmedicine.controllers;

import java.nio.file.AccessDeniedException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.senai.labmedicine.dtos.usuario.UsuarioAtualizacaoDTO;
import br.senai.labmedicine.dtos.usuario.UsuarioCadastroDTO;
import br.senai.labmedicine.dtos.usuario.UsuarioLoginDTO;
import br.senai.labmedicine.dtos.usuario.UsuarioResetarSenhaDTO;
import br.senai.labmedicine.dtos.usuario.UsuarioResponseDTO;
import br.senai.labmedicine.services.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrarUsuario(@RequestBody @Valid UsuarioCadastroDTO novoUsuario,
                                                               @RequestHeader(value = "idUsuarioLogado",required = true)Long idUsuarioLogado) throws AccessDeniedException, NoSuchAlgorithmException {
        return new ResponseEntity<>(this.usuarioService.cadastrarUsuario(idUsuarioLogado,novoUsuario), HttpStatus.CREATED);
    }

    @GetMapping("/buscarPorEmail")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorEmail(@RequestParam String email) throws AccessDeniedException {
        return new ResponseEntity<>(this.usuarioService.buscarUsuarioPorEmail(email),HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioResponseDTO> loginUsuario(@RequestBody @Valid UsuarioLoginDTO usuarioLogin) throws AuthenticationException, NoSuchAlgorithmException {
        return new ResponseEntity<>(this.usuarioService.login(usuarioLogin),HttpStatus.OK);
    }

    @PatchMapping("/resetarSenha")
    public ResponseEntity<Void> resetarSenha(@RequestBody @Valid UsuarioResetarSenhaDTO usuario) throws AuthenticationException, NoSuchAlgorithmException {
        this.usuarioService.resetarSenha(usuario);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorId(@PathVariable Long id){
        return new ResponseEntity<>(this.usuarioService.buscarUsuarioPorId(id),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> buscarTodosUsuarios(@RequestHeader(value = "idUsuarioLogado",required = true)Long idUsuarioLogado) throws AccessDeniedException {
        return new ResponseEntity<>(this.usuarioService.buscarTodosUsuarios(idUsuarioLogado),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(@PathVariable Long id,
                                                               @RequestBody @Valid UsuarioAtualizacaoDTO usuarioAtualizacaoDTO,
                                                               @RequestHeader(value = "idUsuarioLogado",required = true)Long idUsuarioLogado) throws NoSuchAlgorithmException, AccessDeniedException {
        return new ResponseEntity<>(this.usuarioService.atualizarUsuario(idUsuarioLogado,id,usuarioAtualizacaoDTO),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id,@RequestHeader(value = "idUsuarioLogado",required = true)Long idUsuarioLogado) throws AccessDeniedException {

        this.usuarioService.deletarUsuario(id,idUsuarioLogado);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/listagem/{filtro}")
    public ResponseEntity<List<UsuarioResponseDTO>> buscarPorFiltro(@RequestHeader(value = "idUsuarioLogado",required = true)Long idUsuarioLogado,@PathVariable String filtro) throws Exception {
        return new ResponseEntity<>(this.usuarioService.buscarProFiltro(idUsuarioLogado,filtro), HttpStatus.OK);
    }
}

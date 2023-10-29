package br.senai.labmedicine.controllers;


import br.senai.labmedicine.dtos.paciente.PacienteAtualizacaoDTO;
import br.senai.labmedicine.dtos.paciente.PacienteCadastroDTO;
import br.senai.labmedicine.dtos.paciente.PacienteResponseDTO;
import br.senai.labmedicine.services.PacienteService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @GetMapping
    public ResponseEntity<List<PacienteResponseDTO>> buscarTodos() {
        return new ResponseEntity<>(this.service.buscarTodos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> buscarPorId(@PathVariable Long id) {
        return new ResponseEntity<>(this.service.buscarPorId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PacienteResponseDTO> cadastrarPaciente(@RequestHeader(value = "idUsuarioLogado",required = true)Long idUsuarioLogado,
                                                                 @Valid @RequestBody PacienteCadastroDTO paciente){
        return new ResponseEntity<PacienteResponseDTO>(this.service.salvar(idUsuarioLogado,paciente), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> atualizarPaciente(@RequestHeader(value = "idUsuarioLogado",required = true)Long idUsuarioLogado,
                                                                 @RequestBody @Valid PacienteAtualizacaoDTO paciente,
                                                                 @PathVariable Long id){
        return new ResponseEntity<>(this.service.atualizarPaciente(idUsuarioLogado,id,paciente),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPaciente(@RequestHeader(value = "idUsuarioLogado",required = true)Long idUsuarioLogado,
                                                @PathVariable Long id) throws Exception {
        this.service.remover(idUsuarioLogado,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/listagem/{filtro}")
    public ResponseEntity<List<PacienteResponseDTO>> buscarPorFiltro(@PathVariable String filtro) throws Exception {
        return new ResponseEntity<>(this.service.buscarProFiltro(filtro), HttpStatus.OK);
    }

    @GetMapping("/buscarPorNome")
    public ResponseEntity<List<PacienteResponseDTO>> buscarPorNome(@RequestParam String nomeCompleto) {
        return new ResponseEntity<>(this.service.buscarPorNome(nomeCompleto), HttpStatus.OK);
    }
}

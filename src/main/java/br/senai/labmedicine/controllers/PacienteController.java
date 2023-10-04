package br.senai.labmedicine.controllers;


import br.senai.labmedicine.dtos.PacienteCadastroDTO;
import br.senai.labmedicine.dtos.PacienteResponseDTO;
import br.senai.labmedicine.services.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")

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
    public ResponseEntity<PacienteResponseDTO> cadastrarPaciente(@Valid @RequestBody PacienteCadastroDTO paciente){
        return new ResponseEntity<PacienteResponseDTO>(this.service.salvar(paciente), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> atualizarPaciente(@RequestBody @Valid PacienteResponseDTO paciente,
                                                              @PathVariable Long id){
        return new ResponseEntity<>(this.service.atualizarPaciente(id,paciente),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPaciente(@PathVariable Long id) throws Exception {
        this.service.remover(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}

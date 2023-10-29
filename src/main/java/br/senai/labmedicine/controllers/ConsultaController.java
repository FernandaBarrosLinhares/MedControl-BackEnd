package br.senai.labmedicine.controllers;


import br.senai.labmedicine.dtos.consulta.ConsultaAtualizacaoDTO;
import br.senai.labmedicine.dtos.consulta.ConsultaCadastroDTO;
import br.senai.labmedicine.dtos.consulta.ConsultaResponseDTO;
import br.senai.labmedicine.services.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping

    public ResponseEntity<ConsultaResponseDTO> salvarConsulta(@RequestHeader(value = "idUsuarioLogado",required = true)Long idUsuarioLogado,@RequestBody @Valid ConsultaCadastroDTO novaConsulta) throws AccessDeniedException {
        return new ResponseEntity<>(this.consultaService.salvar(idUsuarioLogado,novaConsulta), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaResponseDTO> buscarConsultaPorId(@RequestHeader(value = "idUsuarioLogado",required = true)Long idUsuarioLogado,@PathVariable Long id) throws AccessDeniedException {
        return new ResponseEntity<>(this.consultaService.buscarConsultaPorId(idUsuarioLogado,id),HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<ConsultaResponseDTO>> buscarConsultaPorPaciente(@RequestHeader(value = "idUsuarioLogado",required = true)Long idUsuarioLogado,@RequestParam(required = false) Long id) throws AccessDeniedException {
        return new ResponseEntity<>(this.consultaService.buscarConsultaPorPaciente(idUsuarioLogado,id),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarConsulta(@RequestHeader(value = "idUsuarioLogado",required = true)Long idUsuarioLogado,@PathVariable Long id) throws AccessDeniedException {
        this.consultaService.deletarConsulta(idUsuarioLogado,id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("{id}")
    public ResponseEntity<ConsultaResponseDTO> atualizarConsulta(@RequestHeader(value = "idUsuarioLogado",required = true)Long idUsuarioLogado,@PathVariable Long id,@RequestBody @Valid ConsultaAtualizacaoDTO consultaAtualizada) throws AccessDeniedException {
        return new ResponseEntity<>(this.consultaService.atualizarConsulta(idUsuarioLogado,id,consultaAtualizada),HttpStatus.CREATED);
    }

}

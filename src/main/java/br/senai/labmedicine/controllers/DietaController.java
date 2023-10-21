package br.senai.labmedicine.controllers;

import br.senai.labmedicine.dtos.dieta.DietaAtualizacaoDTO;
import br.senai.labmedicine.dtos.dieta.DietaCadastroDTO;
import br.senai.labmedicine.dtos.dieta.DietaResponseDTO;
import br.senai.labmedicine.services.DietaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dietas")
public class DietaController {

    @Autowired
    private DietaService dietaService;

    @PostMapping
    public ResponseEntity<DietaResponseDTO> salvarDieta(@RequestHeader(value = "idUsuarioLogado",required = true)Long idUsuarioLogado,@RequestBody @Valid DietaCadastroDTO novaDieta){
        return new ResponseEntity<>(this.dietaService.salvar(idUsuarioLogado,novaDieta), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DietaResponseDTO> buscarDietaPorId(@PathVariable Long id){
        return new ResponseEntity<>(this.dietaService.buscarDietaPorId(id),HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<DietaResponseDTO>> buscarDietasPorPaciente(@RequestParam(required = false) String nomePaciente){
        return new ResponseEntity<>(this.dietaService.buscarDietaPorPaciente(nomePaciente),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarDieta(@RequestHeader(value = "idUsuarioLogado",required = true)Long idUsuarioLogado,@PathVariable Long id){
        this.dietaService.deletarDieta(idUsuarioLogado,id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("{id}")
    public ResponseEntity<DietaResponseDTO> atualizarDieta(@RequestHeader(value = "idUsuarioLogado",required = true)Long idUsuarioLogado,@PathVariable Long id,@RequestBody @Valid DietaAtualizacaoDTO dietaAtualizada){
        return new ResponseEntity<>(this.dietaService.atualizarDieta(idUsuarioLogado,id,dietaAtualizada),HttpStatus.CREATED);
    }

}

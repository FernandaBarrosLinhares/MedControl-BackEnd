package br.senai.labmedicine.controllers;

import br.senai.labmedicine.dtos.Dieta.DietaAtualizacaoDTO;
import br.senai.labmedicine.dtos.Dieta.DietaCadastroDTO;
import br.senai.labmedicine.dtos.Dieta.DietaResponseDTO;
import br.senai.labmedicine.services.DietaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dieta")
public class DietaController {

    @Autowired
    private DietaService dietaService;

    @PostMapping
    public ResponseEntity<DietaResponseDTO> salvarDieta(@RequestBody @Valid DietaCadastroDTO novaDieta){
        return new ResponseEntity<>(this.dietaService.salvar(novaDieta), HttpStatus.CREATED);
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
    public ResponseEntity<Void> deletarDieta(@PathVariable Long id){
        this.dietaService.deletarDieta(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("{id}")
    public ResponseEntity<DietaResponseDTO> atualizarDieta(@PathVariable Long id,@RequestBody @Valid DietaAtualizacaoDTO dietaAtualizada){
        return new ResponseEntity<>(this.dietaService.atualizarDieta(id,dietaAtualizada),HttpStatus.CREATED);
    }

}

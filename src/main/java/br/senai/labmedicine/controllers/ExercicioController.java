package br.senai.labmedicine.controllers;

import br.senai.labmedicine.dtos.exercicio.ExercicioAtualizacaoDTO;
import br.senai.labmedicine.dtos.exercicio.ExercicioCadastroDTO;
import br.senai.labmedicine.dtos.exercicio.ExercicioResponseDTO;
import br.senai.labmedicine.services.ExercicioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercicios")
public class ExercicioController {

    @Autowired
    private ExercicioService exercicioService;

    @PostMapping
    public ResponseEntity<ExercicioResponseDTO> salvarExercicio(@RequestBody @Valid ExercicioCadastroDTO novoExercicio) {
        return new ResponseEntity<>(this.exercicioService.salvar(novoExercicio), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public  ResponseEntity<ExercicioResponseDTO> buscarExercicioPorId(@PathVariable Long id) {
        return new ResponseEntity<>(this.exercicioService.buscarExercicioPorId(id) ,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ExercicioResponseDTO>> buscarExerciciosPorPaciente(@RequestParam(required = false) String nomePaciente){
        return new ResponseEntity<>(this.exercicioService.buscarExercicioPorPaciente(nomePaciente),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarExercicio(@PathVariable Long id){
        this.exercicioService.deletarExercicio(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("{id}")
    public ResponseEntity<ExercicioResponseDTO> atualizarExercicio(@PathVariable Long id, @RequestBody @Valid ExercicioAtualizacaoDTO exercicioAtualizado) {
        return new ResponseEntity<>(this.exercicioService.atualizarExercicio(id, exercicioAtualizado) ,HttpStatus.OK);
    }
}

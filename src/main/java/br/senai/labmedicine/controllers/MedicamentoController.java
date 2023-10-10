package br.senai.labmedicine.controllers;

import br.senai.labmedicine.dtos.Medicamento.MedicamentoAtualizacaoDTO;
import br.senai.labmedicine.dtos.Medicamento.MedicamentoCadastroDTO;
import br.senai.labmedicine.dtos.Medicamento.MedicamentoResponseDTO;
import br.senai.labmedicine.services.MedicamentoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicamentos")
public class MedicamentoController {
    @Autowired
    private MedicamentoService service;
    // cadastra medicamento
    @PostMapping
    public ResponseEntity<MedicamentoResponseDTO> cadastraMedicamento(@RequestBody @Valid MedicamentoCadastroDTO medicamento){
        MedicamentoResponseDTO response = service.cadastraMedicamento(medicamento);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // atualiza medicamento
    @PutMapping(value = "/{id}")
    public ResponseEntity<MedicamentoResponseDTO> atualizaMedicamento (@PathVariable Long id, @RequestBody @Valid MedicamentoAtualizacaoDTO medicamento){
        MedicamentoResponseDTO response = service.atualizaMedicamentoId(id, medicamento);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // retorna medicamento pelo id
    @GetMapping(value = "/{id}")
    public ResponseEntity<MedicamentoResponseDTO> listaMedicamentoId(@PathVariable Long id){

        MedicamentoResponseDTO response = service.ListaMedicamentoId(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //excluir medicamento
    @DeleteMapping(value = "{id}")
    @Transactional
    public ResponseEntity excluirMediamentoid(@PathVariable Long id){

        service.excluirMedicamentoId(id);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    //Retorna Medicamentos por Paciente
    //@GetMapping
    //public ResponseEntity<List<MedicamentoResponseDTO>> buscarMedicamentoPorPaciente(@RequestParam(required = false) String nomePaciente){
    //    return new ResponseEntity<>(HttpStatus.OK);
    //}

}
package br.senai.labmedicine.controllers;

import br.senai.labmedicine.dtos.medicamento.MedicamentoAtualizacaoDTO;
import br.senai.labmedicine.dtos.medicamento.MedicamentoCadastroDTO;
import br.senai.labmedicine.dtos.medicamento.MedicamentoResponseDTO;
import br.senai.labmedicine.services.MedicamentoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentoController {
    @Autowired
    private MedicamentoService service;
    // cadastra medicamento
    @PostMapping
    public ResponseEntity<MedicamentoResponseDTO> cadastraMedicamento(@RequestHeader(value = "idUsuarioLogado",required = true)Long idUsuarioLogado,@RequestBody @Valid MedicamentoCadastroDTO medicamento){
        MedicamentoResponseDTO response = service.cadastraMedicamento(idUsuarioLogado,medicamento);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // atualiza medicamento
    @PutMapping(value = "/{id}")
    public ResponseEntity<MedicamentoResponseDTO> atualizaMedicamento (@RequestHeader(value = "idUsuarioLogado",required = true)Long idUsuarioLogado,@PathVariable Long id, @RequestBody @Valid MedicamentoAtualizacaoDTO medicamento){
        MedicamentoResponseDTO response = service.atualizaMedicamentoId(idUsuarioLogado,id, medicamento);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // retorna medicamento pelo id
    @GetMapping(value = "/{id}")
    public ResponseEntity<MedicamentoResponseDTO> listaMedicamentoId(@PathVariable Long id){

        MedicamentoResponseDTO response = service.buscarMedicamentoPorId(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //excluir medicamento
    @DeleteMapping(value = "{id}")
    @Transactional
    public ResponseEntity excluirMediamentoid(@RequestHeader(value = "idUsuarioLogado",required = true)Long idUsuarioLogado,@PathVariable Long id){
        service.excluirMedicamentoId(idUsuarioLogado,id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<MedicamentoResponseDTO>> buscar(@RequestParam(required = false) String nomePaciente){
       return new ResponseEntity<>(this.service.buscar(nomePaciente), HttpStatus.OK);
    }

}
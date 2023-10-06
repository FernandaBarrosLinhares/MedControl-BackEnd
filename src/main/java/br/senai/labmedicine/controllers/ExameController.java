package br.senai.labmedicine.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.labmedicine.dtos.ExameCadastroDTO;
import br.senai.labmedicine.dtos.ExameEdicaoDTO;
import br.senai.labmedicine.dtos.ExameResponseDTO;
import br.senai.labmedicine.services.ExameService;

@RestController
@RequestMapping("/exames")
public class ExameController {

	@Autowired
	private ExameService exameService;

	@GetMapping
	public ResponseEntity<List<ExameResponseDTO>> buscar(@RequestBody String nomeUsuario) {
		return new ResponseEntity<>(this.exameService.buscarExamePorPaciente(nomeUsuario), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ExameResponseDTO> buscarPorId(@PathVariable Long id) {
		return new ResponseEntity<>(this.exameService.buscarExamePorId(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ExameResponseDTO> salvar(@RequestBody ExameCadastroDTO exameDTO) {
		return new ResponseEntity<>(this.exameService.salvar(exameDTO), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ExameResponseDTO> editar(@PathVariable Long id, @RequestBody ExameEdicaoDTO exameDTO) {
		return new ResponseEntity<>(this.exameService.atualizarExame(id, exameDTO), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
}

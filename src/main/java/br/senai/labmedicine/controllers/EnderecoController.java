package br.senai.labmedicine.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.senai.labmedicine.dtos.endereco.EnderecoCadastro;
import br.senai.labmedicine.dtos.endereco.EnderecoResponse;
import br.senai.labmedicine.services.EnderecoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
	@Autowired
	private EnderecoService service;

	@GetMapping
	public ResponseEntity<List<EnderecoResponse>> buscarTodos() {
		return new ResponseEntity<>(this.service.buscarTodos(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EnderecoResponse> buscarPorId(@PathVariable Long id) {
		return new ResponseEntity<>(this.service.buscarPorId(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<EnderecoResponse> cadastrarEndereco(@Valid @RequestBody EnderecoCadastro endereco){
        return new ResponseEntity<EnderecoResponse>(this.service.salvar(endereco), HttpStatus.CREATED);
    }

	@PutMapping("/{id}")
	public ResponseEntity<EnderecoResponse> atualizarEndereco(@RequestBody @Valid EnderecoResponse endereco,
															  @PathVariable Long id){
		return new ResponseEntity<>(this.service.atualizarEndereco(id,endereco),HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> removerEndereco(@PathVariable Long id) throws Exception {
			this.service.remover(id);
			return new ResponseEntity<>(HttpStatus.OK);
	}

}
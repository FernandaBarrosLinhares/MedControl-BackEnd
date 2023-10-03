package br.senai.labmedicine.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.senai.labmedicine.dtos.EnderecoCadastro;
import br.senai.labmedicine.dtos.EnderecoResponse;
import br.senai.labmedicine.services.EnderecoService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/endereco")
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

	@DeleteMapping("/{id}")
	public ResponseEntity removerEndereco(@PathVariable Long id) {
		try {
			this.service.remover(id);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// @PostMapping
	// public ResponseEntity<String> salvar(@Valid @RequestBody EnderecoResponse enderecoDTO, BindingResult bindingResult) {
	// 	// return this.service.salvar(enderecoDTO, bindingResult);
	// }

	// @PutMapping("/{id}")
	// public ResponseEntity<String> editar(@PathVariable Long id, @Valid @RequestBody EnderecoResponse enderecoDTO,
	// 		BindingResult bindingResult) {
	// 	// return this.service.editar(id, enderecoDTO, bindingResult);
	// }
}
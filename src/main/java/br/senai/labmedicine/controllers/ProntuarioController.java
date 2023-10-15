package br.senai.labmedicine.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.senai.labmedicine.dtos.ProntuarioRequestDTO;
import br.senai.labmedicine.dtos.validacoes.ProntuarioResponseDTO;
import br.senai.labmedicine.services.ProntuarioService;

@RestController
@RequestMapping("/prontuarios")
public class ProntuarioController {

	@Autowired
	private ProntuarioService prontuarioService;

	@GetMapping
	public ResponseEntity<List<ProntuarioResponseDTO>> buscar(@RequestParam ProntuarioRequestDTO prontuarioRequestDTO) {
		return new ResponseEntity<>(this.prontuarioService.buscarPorIdOuNome(prontuarioRequestDTO), HttpStatus.OK);
	}
}
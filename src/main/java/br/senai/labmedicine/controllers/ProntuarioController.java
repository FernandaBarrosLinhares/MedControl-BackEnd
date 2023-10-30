package br.senai.labmedicine.controllers;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.senai.labmedicine.dtos.prontuario.ProntuarioResponseDTO;
import br.senai.labmedicine.services.ProntuarioService;

@RestController
@RequestMapping("/prontuarios")
public class ProntuarioController {

	@Autowired
	private ProntuarioService prontuarioService;

	@GetMapping
	public ResponseEntity<List<ProntuarioResponseDTO>> buscar(@RequestHeader(value = "idUsuarioLogado",required = true)Long idUsuarioLogado, @RequestParam(required = false) String nomeCompletoPaciente, @RequestParam(required = false) Long pacienteId) throws AccessDeniedException {
		return new ResponseEntity<>(this.prontuarioService.buscarPorIdOuNome(idUsuarioLogado,nomeCompletoPaciente, pacienteId), HttpStatus.OK);
	}
}

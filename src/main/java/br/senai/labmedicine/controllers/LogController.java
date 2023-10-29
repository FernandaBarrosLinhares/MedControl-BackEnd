package br.senai.labmedicine.controllers;

import br.senai.labmedicine.dtos.log.LogCadastroDTO;
import br.senai.labmedicine.dtos.log.LogResponseDTO;
import br.senai.labmedicine.services.LogService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class LogController {

    @Autowired
    private LogService logService;
    @PostMapping()
    public ResponseEntity<LogResponseDTO> cadastrarLog(@RequestBody @Valid LogCadastroDTO novoLog){
        return new ResponseEntity<>(this.logService.cadastrarLog(novoLog),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<LogResponseDTO>> listarLogs(){
        return new ResponseEntity<>(this.logService.listarLogs(),HttpStatus.OK);
    }
}

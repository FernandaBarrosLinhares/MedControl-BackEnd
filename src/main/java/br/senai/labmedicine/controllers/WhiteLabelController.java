package br.senai.labmedicine.controllers;

import br.senai.labmedicine.dtos.whiteLabel.WhiteLabelDTO;
import br.senai.labmedicine.services.WhiteLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/config")
public class WhiteLabelController {

    @Autowired
    private WhiteLabelService whiteLabelService;

    @PutMapping("{id}")
    public ResponseEntity<WhiteLabelDTO> atualizarConfig(@RequestHeader(value = "idUsuarioLogado",required = true)Long idUsuarioLogado,@PathVariable(required = true)Long id,@RequestBody WhiteLabelDTO config) throws AccessDeniedException {
        return new ResponseEntity<>(this.whiteLabelService.atualizarConfig(idUsuarioLogado,id,config), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<WhiteLabelDTO> buscarConfig(@PathVariable(required = true)Long id){
        return new ResponseEntity<>(this.whiteLabelService.buscarConfig(id), HttpStatus.OK);
    }

}

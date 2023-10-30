package br.senai.labmedicine.controllers;

import br.senai.labmedicine.dtos.estatistica.EstatisticaDTO;
import br.senai.labmedicine.services.EstatisticasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatisticas")
public class EstatisticaController {
    @Autowired
    private EstatisticasService estatisticasService;

    @GetMapping
    public ResponseEntity<EstatisticaDTO> listarEstatisticas(){
        return new ResponseEntity<>(this.estatisticasService.listarEstatisticas(), HttpStatus.OK);
    }
}

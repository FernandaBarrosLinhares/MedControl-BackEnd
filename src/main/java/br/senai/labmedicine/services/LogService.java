package br.senai.labmedicine.services;

import br.senai.labmedicine.dtos.log.LogCadastroDTO;
import br.senai.labmedicine.dtos.log.LogResponseDTO;
import br.senai.labmedicine.models.Log;
import br.senai.labmedicine.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;
    public LogResponseDTO cadastrarLog(LogCadastroDTO novoLog){
        Log log = this.logRepository.save(new Log(novoLog));
        return new LogResponseDTO(log);

    }

    public List<LogResponseDTO> listarLogs() {
        return this.logRepository.findAll().stream().map(LogResponseDTO::new).toList();
    }
}

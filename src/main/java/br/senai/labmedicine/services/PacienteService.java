package br.senai.labmedicine.services;


import br.senai.labmedicine.dtos.PacienteCadastroDTO;
import br.senai.labmedicine.dtos.PacienteResponseDTO;
import br.senai.labmedicine.models.Paciente;
import br.senai.labmedicine.repositories.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<PacienteResponseDTO> buscarTodos() {
        List<PacienteResponseDTO> pacientesDTO = new ArrayList<> ();
        List<Paciente> pacientes = this.pacienteRepository.findAll();

        for (Paciente paciente : pacientes) {
            PacienteResponseDTO pacienteDTO = new PacienteResponseDTO();
            BeanUtils.copyProperties(paciente, pacienteDTO);
            pacientesDTO.add(pacienteDTO);
        }

        return pacientesDTO;
    }

    public PacienteResponseDTO buscarPorId(Long id) {
        Paciente pacienteBd = this.pacienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException ("Paciente não existe."));
        return new PacienteResponseDTO(pacienteBd);
    }

    public PacienteResponseDTO salvar(PacienteCadastroDTO pacienteDTO) {
        Paciente paciente = new Paciente();
        PacienteResponseDTO response = new PacienteResponseDTO();

        BeanUtils.copyProperties(pacienteDTO, paciente);
        paciente = this.pacienteRepository.save(paciente);
        BeanUtils.copyProperties(paciente, response);

        return response;
    }

    public void remover(Long id) throws Exception {
        this.pacienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Paciente não existe."));
        this.pacienteRepository.deleteById(id);
    }

    public PacienteResponseDTO atualizarPaciente(Long id, PacienteResponseDTO paciente) {
        Paciente pacienteBd = this.pacienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Paciente não existe."));
        BeanUtils.copyProperties(paciente,pacienteBd);
        pacienteBd = this.pacienteRepository.save(pacienteBd);
        PacienteResponseDTO response = new PacienteResponseDTO(pacienteBd);
        return response;
    }
}

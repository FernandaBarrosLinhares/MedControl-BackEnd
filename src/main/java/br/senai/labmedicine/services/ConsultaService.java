package br.senai.labmedicine.services;

import br.senai.labmedicine.dtos.PacienteResponseDTO;
import br.senai.labmedicine.dtos.consulta.ConsultaAtualizacaoDTO;
import br.senai.labmedicine.dtos.consulta.ConsultaCadastroDTO;
import br.senai.labmedicine.dtos.consulta.ConsultaResponseDTO;
import br.senai.labmedicine.models.Consulta;
import br.senai.labmedicine.models.Paciente;
import br.senai.labmedicine.repositories.ConsultaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private PacienteService pacienteService;
    public ConsultaResponseDTO salvar(ConsultaCadastroDTO novaConsulta) {
        Consulta consulta = new Consulta();
        ConsultaResponseDTO consultaDTO = new ConsultaResponseDTO();
        PacienteResponseDTO pacienteDTO = this.pacienteService.buscarPorId(novaConsulta.getPaciente().getId());
        Paciente paciente = new Paciente();
        BeanUtils.copyProperties(novaConsulta ,consulta);
        consulta.setPaciente(paciente);
        consulta.getPaciente().setId(novaConsulta.getPaciente().getId());
        consulta = this.consultaRepository.save(consulta);
        BeanUtils.copyProperties(consulta,consultaDTO);
        consultaDTO.setPaciente(pacienteDTO);
        return consultaDTO;
    }

    public ConsultaResponseDTO buscarConsultaPorId(Long id) {
        Consulta consulta = this.consultaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException ("Consulta não encontrada."));
        ConsultaResponseDTO consultaResponseDTO = new ConsultaResponseDTO();
        PacienteResponseDTO pacienteDTO = new PacienteResponseDTO();
        BeanUtils.copyProperties(consulta.getPaciente(),pacienteDTO);
        BeanUtils.copyProperties(consulta,consultaResponseDTO);
        consultaResponseDTO.setPaciente(pacienteDTO);
        return consultaResponseDTO;
    }

    public List<ConsultaResponseDTO> buscarConsultaPorPaciente(Long id){
        List<Consulta> consultas;
        List<ConsultaResponseDTO> consultasDTO = new ArrayList<> ();
        if(id == null){
            consultas = this.consultaRepository.findAll();
        }else {
            consultas = this.consultaRepository.findAllByPacienteId(id);
        }
        for(Consulta consulta : consultas){
            PacienteResponseDTO pacienteDTO = new PacienteResponseDTO();
            ConsultaResponseDTO consultaDTO = new ConsultaResponseDTO();
            BeanUtils.copyProperties(consulta,consultaDTO);
            BeanUtils.copyProperties(consulta.getPaciente(),pacienteDTO);
            consultaDTO.setPaciente(pacienteDTO);
            consultasDTO.add(consultaDTO);
        }
        return consultasDTO;
    }

    public void deletarConsulta(Long id){
        Consulta consulta = this.consultaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada."));
        this.consultaRepository.deleteById(id);
    }

    public ConsultaResponseDTO atualizarConsulta(Long id, ConsultaAtualizacaoDTO consultaAtualizada) {
        Consulta consulta = this.consultaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada."));
        if (consultaAtualizada.getPaciente().getId() == null) {
            throw new EntityNotFoundException("Paciente não encontrado.");
        }
        PacienteResponseDTO pacienteDTO = this.pacienteService.buscarPorId(consultaAtualizada.getPaciente().getId());
        ConsultaResponseDTO consultaResponseDTO = new ConsultaResponseDTO();
        Paciente paciente = new Paciente();
        BeanUtils.copyProperties(consultaAtualizada,consulta);
        paciente.setId(consultaAtualizada.getPaciente().getId());
        consulta.setPaciente(paciente);
        consulta = this.consultaRepository.save(consulta);
        BeanUtils.copyProperties(consulta,consultaResponseDTO);
        consultaResponseDTO.setPaciente(pacienteDTO);
        return consultaResponseDTO;
    }
}



package br.senai.labmedicine.services;

import br.senai.labmedicine.dtos.PacienteResponseDTO;
import br.senai.labmedicine.dtos.Medicamento.MedicamentoResponseDTO;
import br.senai.labmedicine.dtos.consulta.ConsultaAtualizacaoDTO;
import br.senai.labmedicine.dtos.consulta.ConsultaCadastroDTO;
import br.senai.labmedicine.dtos.consulta.ConsultaResponseDTO;
import br.senai.labmedicine.dtos.usuario.UsuarioResponseDTO;
import br.senai.labmedicine.models.Consulta;
import br.senai.labmedicine.models.Medicamento;
import br.senai.labmedicine.models.Paciente;
import br.senai.labmedicine.models.Usuario;
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
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private MedicamentoService medicamentoService;

    public ConsultaResponseDTO salvar(ConsultaCadastroDTO novaConsulta) {
        Consulta consulta = new Consulta();
        ConsultaResponseDTO consultaDTO = new ConsultaResponseDTO();
        PacienteResponseDTO pacienteDTO = this.pacienteService.buscarPorId(novaConsulta.getPaciente().getId());
        UsuarioResponseDTO usuarioDTO = this.usuarioService.buscarUsuarioPorId(novaConsulta.getUsuario().getId());
        MedicamentoResponseDTO medicamentoDTO = this.medicamentoService
                .buscarMedicamentoPorId(novaConsulta.getMedicamento().getId());

        BeanUtils.copyProperties(novaConsulta, consulta);

        Paciente paciente = new Paciente();
        consulta.setPaciente(paciente);
        consulta.getPaciente().setId(novaConsulta.getPaciente().getId());

        Usuario usuario = new Usuario();
        consulta.setUsuario(usuario);
        consulta.getUsuario().setId(novaConsulta.getUsuario().getId());

        Medicamento medicamento = new Medicamento();
        consulta.setMedicamento(medicamento);
        consulta.getMedicamento().setId(novaConsulta.getMedicamento().getId());

        consulta = this.consultaRepository.save(consulta);
        BeanUtils.copyProperties(consulta, consultaDTO);
        consultaDTO.setPaciente(pacienteDTO);
        consultaDTO.setUsuario(usuarioDTO);
        consultaDTO.setMedicamento(medicamentoDTO);
        return consultaDTO;
    }

    public ConsultaResponseDTO buscarConsultaPorId(Long id) {
        Consulta consulta = this.consultaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada."));
        ConsultaResponseDTO consultaResponseDTO = new ConsultaResponseDTO();

        PacienteResponseDTO pacienteDTO = new PacienteResponseDTO();
        BeanUtils.copyProperties(consulta.getPaciente(), pacienteDTO);

        UsuarioResponseDTO usuarioDTO = new UsuarioResponseDTO();
        BeanUtils.copyProperties(consulta.getUsuario(), usuarioDTO);

        MedicamentoResponseDTO medicamentoDTO = new MedicamentoResponseDTO();
        BeanUtils.copyProperties(consulta.getMedicamento(), medicamentoDTO);

        BeanUtils.copyProperties(consulta, consultaResponseDTO);
        consultaResponseDTO.setPaciente(pacienteDTO);
        consultaResponseDTO.setUsuario(usuarioDTO);
        consultaResponseDTO.setMedicamento(medicamentoDTO);
        return consultaResponseDTO;
    }

    public List<ConsultaResponseDTO> buscarConsultaPorPaciente(Long id) {
        List<Consulta> consultas;
        List<ConsultaResponseDTO> consultasDTO = new ArrayList<>();
        if (id == null) {
            consultas = this.consultaRepository.findAll();
        } else {
            consultas = this.consultaRepository.findAllByPacienteId(id);
        }
        for (Consulta consulta : consultas) {
            ConsultaResponseDTO consultaDTO = new ConsultaResponseDTO();
            BeanUtils.copyProperties(consulta, consultaDTO);

            PacienteResponseDTO pacienteDTO = new PacienteResponseDTO();
            BeanUtils.copyProperties(consulta.getPaciente(), pacienteDTO);
            consultaDTO.setPaciente(pacienteDTO);

            UsuarioResponseDTO usuarioDTO = new UsuarioResponseDTO();
            BeanUtils.copyProperties(consulta.getUsuario(), usuarioDTO);
            consultaDTO.setUsuario(usuarioDTO);

            MedicamentoResponseDTO medicamentoDTO = new MedicamentoResponseDTO();
            BeanUtils.copyProperties(consulta.getMedicamento(), medicamentoDTO);
            consultaDTO.setMedicamento(medicamentoDTO);

            consultasDTO.add(consultaDTO);
        }
        return consultasDTO;
    }

    public void deletarConsulta(Long id) {
        Consulta consulta = this.consultaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada."));
        this.consultaRepository.deleteById(id);
    }

    public ConsultaResponseDTO atualizarConsulta(Long id, ConsultaAtualizacaoDTO consultaAtualizada) {
        Consulta consulta = this.consultaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada."));
        if (consultaAtualizada.getPaciente().getId() == null) {
            throw new EntityNotFoundException("Paciente não encontrado.");
        }
        PacienteResponseDTO pacienteDTO = this.pacienteService.buscarPorId(consultaAtualizada.getPaciente().getId());
        UsuarioResponseDTO usuarioDTO = this.usuarioService.buscarUsuarioPorId(consultaAtualizada.getUsuario().getId());
        MedicamentoResponseDTO medicamentoDTO = this.medicamentoService
                .buscarMedicamentoPorId(consultaAtualizada.getMedicamento().getId());
        ConsultaResponseDTO consultaResponseDTO = new ConsultaResponseDTO();
        Paciente paciente = new Paciente();
        BeanUtils.copyProperties(consultaAtualizada, consulta);
        paciente.setId(consultaAtualizada.getPaciente().getId());
        consulta.setPaciente(paciente);
        consulta = this.consultaRepository.save(consulta);
        BeanUtils.copyProperties(consulta, consultaResponseDTO);
        consultaResponseDTO.setPaciente(pacienteDTO);
        return consultaResponseDTO;
    }
}

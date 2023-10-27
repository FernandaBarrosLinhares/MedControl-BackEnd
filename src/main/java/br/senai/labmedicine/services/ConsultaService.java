package br.senai.labmedicine.services;

import br.senai.labmedicine.dtos.paciente.PacienteResponseDTO;
import br.senai.labmedicine.dtos.medicamento.MedicamentoResponseDTO;
import br.senai.labmedicine.dtos.consulta.ConsultaAtualizacaoDTO;
import br.senai.labmedicine.dtos.consulta.ConsultaCadastroDTO;
import br.senai.labmedicine.dtos.consulta.ConsultaResponseDTO;
import br.senai.labmedicine.dtos.log.LogCadastroDTO;
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


import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.time.LocalTime;
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
    @Autowired
    private LogService logService;

    public ConsultaResponseDTO salvar(Long idUsuarioLogado, ConsultaCadastroDTO novaConsulta) throws AccessDeniedException {
        UsuarioResponseDTO usuarioLogado = usuarioService.buscarUsuarioPorId(idUsuarioLogado);
        if(usuarioLogado.getTipoUsuario().getDescricao().equals("Enfermeiro")){
            throw new AccessDeniedException("Usuário sem acesso!");
        }
        Consulta consulta = new Consulta();
        ConsultaResponseDTO consultaDTO = new ConsultaResponseDTO();
        PacienteResponseDTO pacienteDTO = this.pacienteService.buscarPorId(novaConsulta.getPaciente().getId());
        UsuarioResponseDTO usuarioDTO = this.usuarioService.buscarUsuarioPorId(novaConsulta.getUsuario().getId());
        MedicamentoResponseDTO medicamentoDTO = this.medicamentoService.buscarMedicamentoPorId(novaConsulta.getMedicamento().getId());

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
        consulta.setStatus(true);
        consulta = this.consultaRepository.save(consulta);
        BeanUtils.copyProperties(consulta, consultaDTO);
        consultaDTO.setPaciente(pacienteDTO);
        consultaDTO.setUsuario(usuarioDTO);
        consultaDTO.setMedicamento(medicamentoDTO);
        String mensagem = "O usuário: (id: "+usuarioLogado.getId()+") "+usuarioLogado.getNomeCompleto()+" Realizou uma consulta (id:"+ consulta.getId() +") com o paciente: (id: "+pacienteDTO.getId()+") "+pacienteDTO.getNomeCompleto();
        logService.cadastrarLog(new LogCadastroDTO(LocalDate.now(), LocalTime.now(),mensagem));
        return consultaDTO;
    }

    public ConsultaResponseDTO buscarConsultaPorId(Long idUsuarioLogado,Long id) throws AccessDeniedException {
        Consulta consulta = this.consultaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada."));
        ConsultaResponseDTO consultaResponseDTO = new ConsultaResponseDTO();


        UsuarioResponseDTO usuarioLogado = usuarioService.buscarUsuarioPorId(idUsuarioLogado);
        if(usuarioLogado.getTipoUsuario().getDescricao().equals("Enfermeiro")){
            throw new AccessDeniedException("Usuário sem acesso!");
        }

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

    public List<ConsultaResponseDTO> buscarConsultaPorPaciente(Long idUsuarioLogado,Long id) throws AccessDeniedException {
        UsuarioResponseDTO usuarioLogado = usuarioService.buscarUsuarioPorId(idUsuarioLogado);
        if(usuarioLogado.getTipoUsuario().getDescricao().equals("Enfermeiro")){
            throw new AccessDeniedException("Usuário sem acesso!");
        }
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

    public void deletarConsulta(Long idUsuarioLogado,Long id) throws AccessDeniedException {
        Consulta consulta = this.consultaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada."));
        UsuarioResponseDTO usuarioLogado = usuarioService.buscarUsuarioPorId(idUsuarioLogado);
        if(usuarioLogado.getTipoUsuario().getDescricao().equals("Enfermeiro")){
            throw new AccessDeniedException("Usuário sem acesso!");
        }
        this.consultaRepository.deleteById(id);
        String mensagem = "O usuário: (id: "+usuarioLogado.getId()+") "+usuarioLogado.getNomeCompleto()+" Deletou a consulta (id:"+ consulta.getId()+") com o paciente: (id: "+consulta.getPaciente().getId()+") "+consulta.getPaciente().getNomeCompleto();
        logService.cadastrarLog(new LogCadastroDTO(LocalDate.now(), LocalTime.now(),mensagem));
    }

    public ConsultaResponseDTO atualizarConsulta(Long idUsuarioLogado,Long id, ConsultaAtualizacaoDTO consultaAtualizada) throws AccessDeniedException {
        Consulta consulta = this.consultaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada."));
        UsuarioResponseDTO usuarioLogado = usuarioService.buscarUsuarioPorId(idUsuarioLogado);
        if(usuarioLogado.getTipoUsuario().getDescricao().equals("Enfermeiro")){
            throw new AccessDeniedException("Usuário sem acesso!");
        }
        MedicamentoResponseDTO medicamentoDTO = this.medicamentoService.buscarMedicamentoPorId(consultaAtualizada.getMedicamento().getId());
        BeanUtils.copyProperties(consultaAtualizada, consulta);
        consulta.setMedicamento(new Medicamento(medicamentoDTO));
        consulta = this.consultaRepository.save(consulta);
//        BeanUtils.copyProperties(consulta, consultaResponseDTO);
        ConsultaResponseDTO consultaResponseDTO = new ConsultaResponseDTO(consulta);
        String mensagem = "O usuário: (id: "+usuarioLogado.getId()+") "+usuarioLogado.getNomeCompleto()+" Atualizou a consulta (id:"+ consulta.getId()+") com o paciente: (id: "+consulta.getPaciente().getId()+") "+consulta.getPaciente().getNomeCompleto();
        logService.cadastrarLog(new LogCadastroDTO(LocalDate.now(), LocalTime.now(),mensagem));
        return consultaResponseDTO;
    }

    public List<ConsultaResponseDTO> buscarConsultaPorUsuario(Long idUsuarioLogado,Long idUsuario) throws AccessDeniedException {

        UsuarioResponseDTO usuarioLogado = usuarioService.buscarUsuarioPorId(idUsuarioLogado);
        if(usuarioLogado.getTipoUsuario().getDescricao().equals("Enfermeiro")){
            throw new AccessDeniedException("Usuário sem acesso!");
        }
        return this.consultaRepository.findAllByUsuarioId(idUsuario).stream().map(ConsultaResponseDTO::new).toList();
    }
}

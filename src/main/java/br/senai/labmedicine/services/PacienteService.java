package br.senai.labmedicine.services;


import br.senai.labmedicine.dtos.*;
import br.senai.labmedicine.dtos.log.LogCadastroDTO;
import br.senai.labmedicine.dtos.usuario.UsuarioResponseDTO;
import br.senai.labmedicine.models.Endereco;
import br.senai.labmedicine.models.Paciente;
import br.senai.labmedicine.repositories.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LogService logService;

    @Autowired EnderecoService enderecoService;
    public List<PacienteResponseDTO> buscarTodos() {
//        List<PacienteResponseDTO> pacientesDTO = new ArrayList<> ();
//        List<Paciente> pacientes = this.pacienteRepository.findAll();
//        for (Paciente paciente : pacientes) {
//            PacienteResponseDTO pacienteDTO = new PacienteResponseDTO();
//            EnderecoResponse enderecoDTO = new EnderecoResponse();
//            BeanUtils.copyProperties(paciente, pacienteDTO);
//            BeanUtils.copyProperties(paciente.getEndereco(),enderecoDTO);
//            pacienteDTO.setEndereco(enderecoDTO);
//            pacientesDTO.add(pacienteDTO);
//        }
//        return pacientesDTO;

        List<PacienteResponseDTO> pacientesDTO;
        pacientesDTO = this.pacienteRepository.findAll().stream().map(PacienteResponseDTO::new).toList();
        return pacientesDTO;
    }

    public PacienteResponseDTO buscarPorId(Long id) {
        Paciente pacienteBd = this.pacienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException ("Paciente não existe."));
        return new PacienteResponseDTO(pacienteBd);
    }

    public PacienteResponseDTO salvar(Long idUsuarioLogado, PacienteCadastroDTO pacienteDTO) {
        UsuarioResponseDTO usuarioLogado = usuarioService.buscarUsuarioPorId(idUsuarioLogado);
        Paciente paciente = new Paciente(pacienteDTO);
        paciente = this.pacienteRepository.save(paciente);
        String mensagem = "O usuário: (id: "+usuarioLogado.getId()+") "+usuarioLogado.getNomeCompleto()+" Cadastrou o paciente: (id: "+paciente.getId()+") "+paciente.getNomeCompleto();
        logService.cadastrarLog(new LogCadastroDTO(LocalDate.now(), LocalTime.now(),mensagem));
        return new PacienteResponseDTO(paciente);
    }

    public void remover(Long id) throws Exception {
        this.pacienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Paciente não existe."));
        this.pacienteRepository.deleteById(id);
    }

    public PacienteResponseDTO atualizarPaciente(Long id, PacienteAtualizacaoDTO pacienteAtualizado) {
        Paciente pacienteBd = this.pacienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Paciente não existe."));
        BeanUtils.copyProperties(pacienteAtualizado,pacienteBd);
        Endereco endereco = new Endereco(pacienteAtualizado.getEndereco());
        endereco.setId(pacienteBd.getEndereco().getId());
        pacienteBd.setEndereco(endereco);
        pacienteBd = this.pacienteRepository.save(pacienteBd);
        return new PacienteResponseDTO(pacienteBd);
    }

    public List<PacienteResponseDTO> buscarPorNome(String nomeCompleto) {
//        List<PacienteResponseDTO> pacientesDTO = new ArrayList<> ();
//        List<Paciente> pacientes = this.pacienteRepository.findByNomeCompleto(nomeCompleto);
//        for (Paciente paciente : pacientes) {
//            PacienteResponseDTO pacienteDTO = new PacienteResponseDTO();
//            BeanUtils.copyProperties(paciente, pacienteDTO);
//            pacientesDTO.add(pacienteDTO);
//        }
//        return pacientesDTO;
        List<PacienteResponseDTO> pacientesDTO;
        pacientesDTO = this.pacienteRepository.findByNomeCompleto(nomeCompleto).stream().map(PacienteResponseDTO::new).toList();
        return pacientesDTO;
    }
}

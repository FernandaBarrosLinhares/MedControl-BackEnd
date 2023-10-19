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
import org.springframework.dao.DataIntegrityViolationException;
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
        List<PacienteResponseDTO> pacientesDTO;
        pacientesDTO = this.pacienteRepository.findAll().stream().map(PacienteResponseDTO::new).toList();
        return pacientesDTO;
    }

    public PacienteResponseDTO buscarPorId(Long id) {
        Paciente pacienteBd = this.pacienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException ("Paciente não existe."));
        return new PacienteResponseDTO(pacienteBd);
    }

    public PacienteResponseDTO salvar(Long idUsuarioLogado, PacienteCadastroDTO pacienteDTO) {
        checarDisponibilidadeEmailECpf(pacienteDTO.getCpf(), pacienteDTO.getEmail());
        UsuarioResponseDTO usuarioLogado = usuarioService.buscarUsuarioPorId(idUsuarioLogado);
        Paciente paciente = new Paciente(pacienteDTO);
        paciente = this.pacienteRepository.save(paciente);
        String mensagem = "O usuário: (id: "+usuarioLogado.getId()+") "+usuarioLogado.getNomeCompleto()+" Cadastrou o paciente: (id: "+paciente.getId()+") "+paciente.getNomeCompleto();
        logService.cadastrarLog(new LogCadastroDTO(LocalDate.now(), LocalTime.now(),mensagem));
        return new PacienteResponseDTO(paciente);
    }

    public void remover(Long idUsuarioLogado,Long id) throws Exception {
        Paciente pacienteBd = this.pacienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Paciente não existe."));
        UsuarioResponseDTO usuarioLogado = usuarioService.buscarUsuarioPorId(idUsuarioLogado);
        try {
            this.pacienteRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Paciente em uso não pode ser deletado.");
        }
        String mensagem = "O usuário: (id: "+usuarioLogado.getId()+") "+usuarioLogado.getNomeCompleto()+" deletou o paciente: (id: "+pacienteBd.getId()+") "+pacienteBd.getNomeCompleto();
        logService.cadastrarLog(new LogCadastroDTO(LocalDate.now(), LocalTime.now(),mensagem));
    }

    public PacienteResponseDTO atualizarPaciente(Long idUsuarioLogado,Long id, PacienteAtualizacaoDTO pacienteAtualizado) {
        Paciente pacienteBd = this.pacienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Paciente não existe."));
        UsuarioResponseDTO usuarioLogado = usuarioService.buscarUsuarioPorId(idUsuarioLogado);
        if(!pacienteBd.getEmail().equals(pacienteAtualizado.getEmail())){
            checarDisponibilidadeEmailECpf("", pacienteAtualizado.getEmail());
        }
        BeanUtils.copyProperties(pacienteAtualizado,pacienteBd);
        Endereco endereco = new Endereco(pacienteAtualizado.getEndereco());
        endereco.setId(pacienteBd.getEndereco().getId());
        pacienteBd.setEndereco(endereco);
        pacienteBd = this.pacienteRepository.save(pacienteBd);
        String mensagem = "O usuário: (id: "+usuarioLogado.getId()+") "+usuarioLogado.getNomeCompleto()+" atualizou o paciente: (id: "+pacienteBd.getId()+") "+pacienteBd.getNomeCompleto();
        logService.cadastrarLog(new LogCadastroDTO(LocalDate.now(), LocalTime.now(),mensagem));
        return new PacienteResponseDTO(pacienteBd);
    }

    public List<PacienteResponseDTO> buscarPorNome(String nomeCompleto) {
        List<PacienteResponseDTO> pacientesDTO;
        pacientesDTO = this.pacienteRepository.findByNomeCompleto(nomeCompleto).stream().map(PacienteResponseDTO::new).toList();
        return pacientesDTO;
    }

    public List<PacienteResponseDTO> buscarProFiltro(String filtro) {
        List<PacienteResponseDTO> pacientesDTO;
        pacientesDTO = this.pacienteRepository.buscarComFiltro(filtro).stream().map(PacienteResponseDTO::new).toList();
        return pacientesDTO;
    }


    public void checarDisponibilidadeEmailECpf(String cpf,String email){
        boolean cpfExiste = this.pacienteRepository.existsByCpf(cpf);
        boolean emailExiste = this.pacienteRepository.existsByEmail(email);
        if(cpfExiste){
            throw new DataIntegrityViolationException("CPF já cadastrado.");
        }else if(emailExiste){
            throw new DataIntegrityViolationException("Email já cadastrado.");
        }
    }
}

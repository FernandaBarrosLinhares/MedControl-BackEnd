package br.senai.labmedicine.services;

import br.senai.labmedicine.dtos.Dieta.DietaAtualizacaoDTO;
import br.senai.labmedicine.dtos.Dieta.DietaCadastroDTO;
import br.senai.labmedicine.dtos.Dieta.DietaResponseDTO;
import br.senai.labmedicine.dtos.PacienteResponseDTO;
import br.senai.labmedicine.dtos.log.LogCadastroDTO;
import br.senai.labmedicine.dtos.usuario.UsuarioResponseDTO;
import br.senai.labmedicine.models.Dieta;
import br.senai.labmedicine.models.Paciente;
import br.senai.labmedicine.repositories.DietaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DietaService {
    @Autowired
    private DietaRepository dietaRepository;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LogService logService;
    public DietaResponseDTO salvar(Long idUsuarioLogado,DietaCadastroDTO novaDieta) {
        UsuarioResponseDTO usuarioLogado = usuarioService.buscarUsuarioPorId(idUsuarioLogado);
        Dieta dieta = new Dieta();
        DietaResponseDTO dietaDTO = new DietaResponseDTO();
        PacienteResponseDTO pacienteDTO = this.pacienteService.buscarPorId(novaDieta.getPaciente().getId());
        Paciente paciente = new Paciente();
        BeanUtils.copyProperties(novaDieta,dieta);
        dieta.setPaciente(paciente);
        dieta.getPaciente().setId(novaDieta.getPaciente().getId());
        dieta = this.dietaRepository.save(dieta);
        BeanUtils.copyProperties(dieta,dietaDTO);
        dietaDTO.setPaciente(pacienteDTO);
        String mensagem = "O usuário: (id: "+usuarioLogado.getId()+") "+usuarioLogado.getNomeCompleto()+" Registrou a Dieta (id:"+ dieta.getId()+") para o paciente (id:"+dieta.getPaciente().getId()+") nome: "+ dieta.getPaciente().getNomeCompleto();
        logService.cadastrarLog(new LogCadastroDTO(LocalDate.now(), LocalTime.now(),mensagem));
        return dietaDTO;
    }

    public DietaResponseDTO buscarDietaPorId(Long id) {
        Dieta dieta = this.dietaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Dieta não encontrada."));
        DietaResponseDTO dietaResponseDTO = new DietaResponseDTO();
        PacienteResponseDTO pacienteDTO = new PacienteResponseDTO();
        BeanUtils.copyProperties(dieta.getPaciente(),pacienteDTO);
        BeanUtils.copyProperties(dieta,dietaResponseDTO);
        dietaResponseDTO.setPaciente(pacienteDTO);
        return dietaResponseDTO;
    }

    public List<DietaResponseDTO> buscarDietaPorPaciente(String nomePaciente){
        List<Dieta> dietas;
        List<DietaResponseDTO> dietasDTO = new ArrayList<>();
        if(nomePaciente == null || nomePaciente.isEmpty()){
            dietas = this.dietaRepository.findAll();
        }else {
            dietas = this.dietaRepository.findAllByPacienteNomeCompletoOrderByDataAscHorario(nomePaciente);
        }
           for(Dieta dieta : dietas){
                PacienteResponseDTO pacienteDTO = new PacienteResponseDTO();
                DietaResponseDTO dietaDTO = new DietaResponseDTO();
                BeanUtils.copyProperties(dieta,dietaDTO);
                BeanUtils.copyProperties(dieta.getPaciente(),pacienteDTO);
                dietaDTO.setPaciente(pacienteDTO);
                dietasDTO.add(dietaDTO);
           }
           return dietasDTO;
    }

    public void deletarDieta(Long idUsuarioLogado,Long id){
        Dieta dieta = this.dietaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Dieta não encontrada."));
        UsuarioResponseDTO usuarioLogado = usuarioService.buscarUsuarioPorId(idUsuarioLogado);
        this.dietaRepository.deleteById(id);
        String mensagem = "O usuário: (id: "+usuarioLogado.getId()+") "+usuarioLogado.getNomeCompleto()+" Deletou a Dieta (id:"+ dieta.getId()+") para o paciente (id:"+dieta.getPaciente().getId()+") nome: "+ dieta.getPaciente().getNomeCompleto();
        logService.cadastrarLog(new LogCadastroDTO(LocalDate.now(), LocalTime.now(),mensagem));
    }

    public DietaResponseDTO atualizarDieta(Long idUsuarioLogado,Long id,DietaAtualizacaoDTO dietaAtualizada) {
        Dieta dieta = this.dietaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Dieta não encontrada."));
        UsuarioResponseDTO usuarioLogado = usuarioService.buscarUsuarioPorId(idUsuarioLogado);
        if (dietaAtualizada.getPaciente().getId() == null) {
            throw new EntityNotFoundException("Paciente não encontrado.");
        }
        PacienteResponseDTO pacienteDTO = this.pacienteService.buscarPorId(dietaAtualizada.getPaciente().getId());
        DietaResponseDTO dietaResponseDTO = new DietaResponseDTO();
        Paciente paciente = new Paciente();
        BeanUtils.copyProperties(dietaAtualizada,dieta);
        paciente.setId(dietaAtualizada.getPaciente().getId());
        dieta.setPaciente(paciente);
        dieta = this.dietaRepository.save(dieta);
        BeanUtils.copyProperties(dieta,dietaResponseDTO);
        dietaResponseDTO.setPaciente(pacienteDTO);
        String mensagem = "O usuário: (id: "+usuarioLogado.getId()+") "+usuarioLogado.getNomeCompleto()+" Atualizou a Dieta (id:"+ dieta.getId()+") para o paciente (id:"+dieta.getPaciente().getId()+") nome: "+ dieta.getPaciente().getNomeCompleto();
        logService.cadastrarLog(new LogCadastroDTO(LocalDate.now(), LocalTime.now(),mensagem));
        return dietaResponseDTO;
    }
}

package br.senai.labmedicine.services;

import br.senai.labmedicine.dtos.paciente.PacienteResponseDTO;
import br.senai.labmedicine.dtos.exercicio.ExercicioAtualizacaoDTO;
import br.senai.labmedicine.dtos.exercicio.ExercicioCadastroDTO;
import br.senai.labmedicine.dtos.exercicio.ExercicioResponseDTO;
import br.senai.labmedicine.dtos.log.LogCadastroDTO;
import br.senai.labmedicine.dtos.usuario.UsuarioResponseDTO;
import br.senai.labmedicine.models.Exercicio;
import br.senai.labmedicine.models.Paciente;
import br.senai.labmedicine.repositories.ExercicioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExercicioService {
    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private ExercicioRepository exercicioRepository;

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private LogService logService;

    public ExercicioResponseDTO salvar(Long idUsuarioLogado,ExercicioCadastroDTO novoExercicio) {
        UsuarioResponseDTO usuarioLogado = usuarioService.buscarUsuarioPorId(idUsuarioLogado);
        Exercicio exercicio = new Exercicio();
        ExercicioResponseDTO exercicioDTO = new ExercicioResponseDTO();
        PacienteResponseDTO pacienteDTO = this.pacienteService.buscarPorId(novoExercicio.getPaciente().getId());
        Paciente paciente = new Paciente();
        BeanUtils.copyProperties(novoExercicio, exercicio);
        exercicio.setPaciente(paciente);
        exercicio.getPaciente().setId(novoExercicio.getPaciente().getId());
        exercicio.setStatus(true);
        exercicio = this.exercicioRepository.save(exercicio);
        BeanUtils.copyProperties(exercicio, exercicioDTO);
        exercicioDTO.setPaciente(pacienteDTO);
        String mensagem = "O usuário: (id: "+usuarioLogado.getId()+") "+usuarioLogado.getNomeCompleto()+" Cadastrou um exercício (id:"+ exercicio.getId()+") para o paciente (id:"+exercicio.getPaciente().getId()+") nome: "+ pacienteDTO.getNomeCompleto();
        logService.cadastrarLog(new LogCadastroDTO(LocalDate.now(), LocalTime.now(),mensagem));
        return exercicioDTO;
    }

    public ExercicioResponseDTO atualizarExercicio(Long idUsuarioLogado,Long id, ExercicioAtualizacaoDTO exercicioAtualizado) {
        Exercicio exercicio = this.exercicioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Exercício não encontrado"));
        UsuarioResponseDTO usuarioLogado = usuarioService.buscarUsuarioPorId(idUsuarioLogado);
        ExercicioResponseDTO exercicioResponseDTO = new ExercicioResponseDTO();
        BeanUtils.copyProperties(exercicioAtualizado, exercicio);
        exercicio = this.exercicioRepository.save(exercicio);
        BeanUtils.copyProperties(exercicio, exercicioResponseDTO);
        PacienteResponseDTO pacienteDTO = this.pacienteService.buscarPorId(exercicio.getPaciente().getId());
        exercicioResponseDTO.setPaciente(pacienteDTO);
        String mensagem = "O usuário: (id: "+usuarioLogado.getId()+") "+usuarioLogado.getNomeCompleto()+" Atualizou o exercício (id:"+ exercicio.getId()+") para o paciente (id:"+exercicio.getPaciente().getId()+") nome: "+ exercicio.getPaciente().getNomeCompleto();
        logService.cadastrarLog(new LogCadastroDTO(LocalDate.now(), LocalTime.now(),mensagem));
        return exercicioResponseDTO;
    }

    public void deletarExercicio(Long idUsuarioLogado,Long id) {
        Exercicio exercicio = this.exercicioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Exercício não encotrado"));
        UsuarioResponseDTO usuarioLogado = usuarioService.buscarUsuarioPorId(idUsuarioLogado);
        this.exercicioRepository.deleteById(id);
        String mensagem = "O usuário: (id: "+usuarioLogado.getId()+") "+usuarioLogado.getNomeCompleto()+" Deletou o exercício (id:"+ exercicio.getId()+") para o paciente (id:"+exercicio.getPaciente().getId()+") nome: "+ exercicio.getPaciente().getNomeCompleto();
        logService.cadastrarLog(new LogCadastroDTO(LocalDate.now(), LocalTime.now(),mensagem));
    }

    public ExercicioResponseDTO buscarExercicioPorId(Long id){
        Exercicio exercicio = this.exercicioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Exercício não encontrado"));
        ExercicioResponseDTO exercicioDTO = new ExercicioResponseDTO();
        BeanUtils.copyProperties(exercicio, exercicioDTO);
        PacienteResponseDTO pacienteDTO = this.pacienteService.buscarPorId(exercicio.getPaciente().getId());
        exercicioDTO.setPaciente(pacienteDTO);
        return exercicioDTO;
    }

    public List<ExercicioResponseDTO> buscarExercicioPorPaciente(String nomePaciente){
        List<Exercicio> exercicios;
        List<ExercicioResponseDTO> exerciciosDTO = new ArrayList<>();
        if(nomePaciente == null || nomePaciente.isEmpty()){
            exercicios = this.exercicioRepository.findAll();
        }else {
            exercicios = this.exercicioRepository.findAllByPaciente_NomeCompletoContainingIgnoreCase(nomePaciente);
        }
        for(Exercicio exercicio : exercicios){
            ExercicioResponseDTO exercicioDTO = new ExercicioResponseDTO();
            BeanUtils.copyProperties(exercicio,exercicioDTO);
            PacienteResponseDTO pacienteDTO = pacienteService.buscarPorId(exercicio.getPaciente().getId());
            exercicioDTO.setPaciente(pacienteDTO);
            exerciciosDTO.add(exercicioDTO);
        }
        return exerciciosDTO;
    }
}

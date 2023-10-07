package br.senai.labmedicine.services;

import br.senai.labmedicine.dtos.PacienteResponseDTO;
import br.senai.labmedicine.dtos.exercicio.ExercicioAtualizacaoDTO;
import br.senai.labmedicine.dtos.exercicio.ExercicioCadastroDTO;
import br.senai.labmedicine.dtos.exercicio.ExercicioResponseDTO;
import br.senai.labmedicine.models.Exercicio;
import br.senai.labmedicine.models.Paciente;
import br.senai.labmedicine.repositories.ExercicioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExercicioService {
    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private ExercicioRepository exercicioRepository;

    public ExercicioResponseDTO salvar(ExercicioCadastroDTO novoExercicio) {
        Exercicio exercicio = new Exercicio();
        ExercicioResponseDTO exercicioDTO = new ExercicioResponseDTO();
        PacienteResponseDTO pacienteDTO = this.pacienteService.buscarPorId(novoExercicio.getPaciente().getId());
        Paciente paciente = new Paciente();
        BeanUtils.copyProperties(novoExercicio, exercicio);
        exercicio.setPaciente(paciente);
        exercicio.getPaciente().setId(novoExercicio.getPaciente().getId());
        exercicio = this.exercicioRepository.save(exercicio);
        BeanUtils.copyProperties(exercicio, exercicioDTO);
        exercicioDTO.setPaciente(pacienteDTO);
        return exercicioDTO;
    }

    public ExercicioResponseDTO atualizarExercicio(Long id, ExercicioAtualizacaoDTO exercicioAtualizado) {
        Exercicio exercicio = this.exercicioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Exercicio não encontrado"));
        ExercicioResponseDTO exercicioResponseDTO = new ExercicioResponseDTO();
        BeanUtils.copyProperties(exercicioAtualizado, exercicio);
        exercicio = this.exercicioRepository.save(exercicio);
        BeanUtils.copyProperties(exercicio, exercicioResponseDTO);
        PacienteResponseDTO pacienteDTO = this.pacienteService.buscarPorId(exercicio.getPaciente().getId());
        exercicioResponseDTO.setPaciente(pacienteDTO);
        return exercicioResponseDTO;
    }

    public void deletarExercicio(Long id) {
        Exercicio exercicio = this.exercicioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Exercicio não encotrado"));
        this.exercicioRepository.deleteById(id);
    }

    public ExercicioResponseDTO buscarExercicioPorId(Long id){
        Exercicio exercicio = this.exercicioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Exercicio não encontrado"));
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
            exercicios = this.exercicioRepository.findByPaciente_NomeCompleto(nomePaciente);
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

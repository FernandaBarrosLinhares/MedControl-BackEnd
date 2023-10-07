package br.senai.labmedicine.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.senai.labmedicine.dtos.ExameCadastroDTO;
import br.senai.labmedicine.dtos.ExameEdicaoDTO;
import br.senai.labmedicine.dtos.ExameResponseDTO;
import br.senai.labmedicine.dtos.PacienteResponseDTO;
import br.senai.labmedicine.models.Exame;
import br.senai.labmedicine.models.Paciente;
import br.senai.labmedicine.repositories.ExameRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ExameService {

	@Autowired
	private ExameRepository exameRepository;

	@Autowired
	private PacienteService pacienteService;

	public ExameResponseDTO salvar(ExameCadastroDTO novoExame) {
		Exame exame = new Exame();
		ExameResponseDTO exameDTO = new ExameResponseDTO();
		PacienteResponseDTO pacienteDTO = this.pacienteService.buscarPorId(novoExame.getPaciente().getId());
		Paciente paciente = new Paciente();

		BeanUtils.copyProperties(novoExame, exame);
		exame.setPaciente(paciente);
		exame.getPaciente().setId(novoExame.getPaciente().getId());
		exame = this.exameRepository.save(exame);

		BeanUtils.copyProperties(exame, exameDTO);
		exameDTO.setPaciente(pacienteDTO);

		return exameDTO;
	}

	public ExameResponseDTO buscarExamePorId(Long id) {
		Exame exame = this.exameRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Exame não encontrado."));
		ExameResponseDTO exameResponseDTO = new ExameResponseDTO();
		PacienteResponseDTO pacienteDTO = new PacienteResponseDTO();

		BeanUtils.copyProperties(exame.getPaciente(), pacienteDTO);
		BeanUtils.copyProperties(exame, exameResponseDTO);
		exameResponseDTO.setPaciente(pacienteDTO);

		return exameResponseDTO;
	}

	public List<ExameResponseDTO> buscarExamePorPaciente(String nomePaciente) {
		List<Exame> exames;
		List<ExameResponseDTO> examesDTO = new ArrayList<>();

		if (nomePaciente == null || nomePaciente.isEmpty()) {
			exames = this.exameRepository.findAll();
		} else {
			exames = this.exameRepository.BuscaPorNomePaciente(nomePaciente);
		}

		for (Exame exame : exames) {
			PacienteResponseDTO pacienteDTO = new PacienteResponseDTO();
			ExameResponseDTO exameDTO = new ExameResponseDTO();
			BeanUtils.copyProperties(exame, exameDTO);
			BeanUtils.copyProperties(exame.getPaciente(), pacienteDTO);
			exameDTO.setPaciente(pacienteDTO);
			examesDTO.add(exameDTO);
		}

		return examesDTO;
	}

	public void deletarExame(Long id) {
		this.exameRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Exame não encontrado."));
		this.exameRepository.deleteById(id);
	}

	public ExameResponseDTO atualizarExame(Long id,ExameEdicaoDTO exameEdicao) {
        Exame exame = this.exameRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Dieta não encontrada."));

        if (exameEdicao.getPaciente().getId() == null) {
            throw new EntityNotFoundException("Paciente não encontrado.");
        }

        PacienteResponseDTO pacienteDTO = this.pacienteService.buscarPorId(exameEdicao.getPaciente().getId());
        ExameResponseDTO exameResponseDTO = new ExameResponseDTO();
        Paciente paciente = new Paciente();

        BeanUtils.copyProperties(exameEdicao,exame);
        paciente.setId(exameEdicao.getPaciente().getId());
        exame.setPaciente(paciente);
        exame = this.exameRepository.save(exame);
        BeanUtils.copyProperties(exame,exameResponseDTO);
        exameResponseDTO.setPaciente(pacienteDTO);
		
        return exameResponseDTO;
    }
}

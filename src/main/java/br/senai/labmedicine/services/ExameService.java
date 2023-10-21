package br.senai.labmedicine.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import br.senai.labmedicine.dtos.log.LogCadastroDTO;
import br.senai.labmedicine.dtos.usuario.UsuarioResponseDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.senai.labmedicine.dtos.exame.ExameCadastroDTO;
import br.senai.labmedicine.dtos.exame.ExameEdicaoDTO;
import br.senai.labmedicine.dtos.exame.ExameResponseDTO;
import br.senai.labmedicine.dtos.paciente.PacienteResponseDTO;
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

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private LogService logService;

	public ExameResponseDTO salvar(Long idUsuarioLogado,ExameCadastroDTO novoExame) {
		UsuarioResponseDTO usuarioLogado = usuarioService.buscarUsuarioPorId(idUsuarioLogado);
		Exame exame = new Exame();
		ExameResponseDTO exameDTO = new ExameResponseDTO();
		PacienteResponseDTO pacienteDTO = this.pacienteService.buscarPorId(novoExame.getPaciente().getId());
		Paciente paciente = new Paciente();

		BeanUtils.copyProperties(novoExame, exame);
		exame.setPaciente(paciente);
		exame.getPaciente().setId(novoExame.getPaciente().getId());
		exame.setStatus(true);
		exame = this.exameRepository.save(exame);

		BeanUtils.copyProperties(exame, exameDTO);
		exameDTO.setPaciente(pacienteDTO);
		String mensagem = "O usuário: (id: "+usuarioLogado.getId()+") "+usuarioLogado.getNomeCompleto()+" Registrou um exame (id:"+ exame.getId()+") para o paciente (id:"+exame.getPaciente().getId()+") nome: "+ exame.getPaciente().getNomeCompleto();
		logService.cadastrarLog(new LogCadastroDTO(LocalDate.now(), LocalTime.now(),mensagem));
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
			exames = this.exameRepository.findAllByPacienteNomeCompletoOrderByDataAscHorario(nomePaciente);
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

	public void deletarExame(Long idUsuarioLogado,Long id) {
		UsuarioResponseDTO usuarioLogado = usuarioService.buscarUsuarioPorId(idUsuarioLogado);
		Exame exame = this.exameRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Exame não encontrado."));
		this.exameRepository.deleteById(id);
		String mensagem = "O usuário: (id: "+usuarioLogado.getId()+") "+usuarioLogado.getNomeCompleto()+" deletou o exame (id:"+ exame.getId()+") para o paciente (id:"+exame.getPaciente().getId()+") nome: "+ exame.getPaciente().getNomeCompleto();
		logService.cadastrarLog(new LogCadastroDTO(LocalDate.now(), LocalTime.now(),mensagem));
	}

	public ExameResponseDTO atualizarExame(Long idUsuarioLogado,Long id,ExameEdicaoDTO exameEdicao) {
        Exame exame = this.exameRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Dieta não encontrada."));
		UsuarioResponseDTO usuarioLogado = usuarioService.buscarUsuarioPorId(idUsuarioLogado);
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
		String mensagem = "O usuário: (id: "+usuarioLogado.getId()+") "+usuarioLogado.getNomeCompleto()+" Atualizou o exame (id:"+ exame.getId()+") para o paciente (id:"+exame.getPaciente().getId()+") nome: "+ exame.getPaciente().getNomeCompleto();
		logService.cadastrarLog(new LogCadastroDTO(LocalDate.now(), LocalTime.now(),mensagem));
        return exameResponseDTO;
    }
}

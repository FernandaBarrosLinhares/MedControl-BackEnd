package br.senai.labmedicine.services;

import java.nio.file.AccessDeniedException;
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

	public ExameResponseDTO salvar(Long idUsuarioLogado,ExameCadastroDTO novoExame) throws AccessDeniedException {
		UsuarioResponseDTO usuarioLogado = usuarioService.buscarUsuarioPorId(idUsuarioLogado);
		if(usuarioLogado.getTipoUsuario().getDescricao().equals("Enfermeiro")){
			throw new AccessDeniedException("Usuário sem acesso!");
		}
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
		String mensagem = "O usuário: (id: "+usuarioLogado.getId()+") "+usuarioLogado.getNomeCompleto()+" Registrou um exame (id:"+ exame.getId()+") para o paciente (id:"+exame.getPaciente().getId()+") nome: "+ pacienteDTO.getNomeCompleto();
		logService.cadastrarLog(new LogCadastroDTO(LocalDate.now(), LocalTime.now(),mensagem));
		return exameDTO;
	}

	public ExameResponseDTO buscarExamePorId(Long idUsuarioLogado,Long id) throws AccessDeniedException {
		Exame exame = this.exameRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Exame não encontrado."));
		UsuarioResponseDTO usuarioLogado = usuarioService.buscarUsuarioPorId(idUsuarioLogado);
		if(usuarioLogado.getTipoUsuario().getDescricao().equals("Enfermeiro")){
			throw new AccessDeniedException("Usuário sem acesso!");
		}
		ExameResponseDTO exameResponseDTO = new ExameResponseDTO();
		PacienteResponseDTO pacienteDTO = new PacienteResponseDTO();

		BeanUtils.copyProperties(exame.getPaciente(), pacienteDTO);
		BeanUtils.copyProperties(exame, exameResponseDTO);
		ExameResponseDTO exameResponseDTO1 = new ExameResponseDTO(exame);
		exameResponseDTO.setPaciente(pacienteDTO);

		return exameResponseDTO1;
	}

	public List<ExameResponseDTO> buscarExamePorPaciente(Long idUsuarioLogado,String nomePaciente) throws AccessDeniedException {
		UsuarioResponseDTO usuarioLogado = usuarioService.buscarUsuarioPorId(idUsuarioLogado);
		if(usuarioLogado.getTipoUsuario().getDescricao().equals("Enfermeiro")){
			throw new AccessDeniedException("Usuário sem acesso!");
		}
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

	public void deletarExame(Long idUsuarioLogado,Long id) throws AccessDeniedException {
		UsuarioResponseDTO usuarioLogado = usuarioService.buscarUsuarioPorId(idUsuarioLogado);
		if(usuarioLogado.getTipoUsuario().getDescricao().equals("Enfermeiro")){
			throw new AccessDeniedException("Usuário sem acesso!");
		}
		Exame exame = this.exameRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Exame não encontrado."));
		this.exameRepository.deleteById(id);
		String mensagem = "O usuário: (id: "+usuarioLogado.getId()+") "+usuarioLogado.getNomeCompleto()+" deletou o exame (id:"+ exame.getId()+") para o paciente (id:"+exame.getPaciente().getId()+") nome: "+ exame.getPaciente().getNomeCompleto();
		logService.cadastrarLog(new LogCadastroDTO(LocalDate.now(), LocalTime.now(),mensagem));
	}

	public ExameResponseDTO atualizarExame(Long idUsuarioLogado,Long id,ExameEdicaoDTO exameEdicao) throws AccessDeniedException {
        Exame exame = this.exameRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Exame não encontrado."));
		UsuarioResponseDTO usuarioLogado = usuarioService.buscarUsuarioPorId(idUsuarioLogado);
		if(usuarioLogado.getTipoUsuario().getDescricao().equals("Enfermeiro")){
			throw new AccessDeniedException("Usuário sem acesso!");
		}
        ExameResponseDTO exameResponseDTO = new ExameResponseDTO();
        BeanUtils.copyProperties(exameEdicao,exame);

        exame = this.exameRepository.save(exame);
		ExameResponseDTO exameResponseDTO1 = new ExameResponseDTO(exame);
		String mensagem = "O usuário: (id: "+usuarioLogado.getId()+") "+usuarioLogado.getNomeCompleto()+" Atualizou o exame (id:"+ exame.getId()+") para o paciente (id:"+exame.getPaciente().getId()+") nome: "+ exame.getPaciente().getNomeCompleto();
		logService.cadastrarLog(new LogCadastroDTO(LocalDate.now(), LocalTime.now(),mensagem));
        return exameResponseDTO1;
    }
}

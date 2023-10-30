package br.senai.labmedicine.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.senai.labmedicine.dtos.endereco.EnderecoCadastro;
import br.senai.labmedicine.dtos.endereco.EnderecoResponse;
import br.senai.labmedicine.models.Endereco;
import br.senai.labmedicine.repositories.EnderecoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EnderecoService {
	@Autowired
	private EnderecoRepository enderecoRepository;

	public List<EnderecoResponse> buscarTodos() {
		List<EnderecoResponse> enderecosDTO = new ArrayList<>();
		List<Endereco> enderecos = this.enderecoRepository.findAll();

		for (Endereco endereco : enderecos) {
			EnderecoResponse enderecoDTO = new EnderecoResponse();
			BeanUtils.copyProperties(endereco, enderecoDTO);
			enderecosDTO.add(enderecoDTO);
		}

		return enderecosDTO;
	}

	public EnderecoResponse buscarPorId(Long id) {
		Endereco enderecoBd = this.enderecoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Endereço não existe."));
		return new EnderecoResponse(enderecoBd);
	}

	public EnderecoResponse salvar(EnderecoCadastro enderecoDTO) {
		Endereco endereco = new Endereco();
		EnderecoResponse response = new EnderecoResponse();

		BeanUtils.copyProperties(enderecoDTO, endereco);
		endereco = this.enderecoRepository.save(endereco);
		BeanUtils.copyProperties(endereco, response);

		return response;
	}

	public void remover(Long id) throws Exception {
		this.enderecoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Endereço não existe."));
		try{
		this.enderecoRepository.deleteById(id);
		}catch (DataIntegrityViolationException e){
			throw new DataIntegrityViolationException("Endereço em uso não pode ser deletado.");
		}
	}

	public EnderecoResponse atualizarEndereco(Long id, EnderecoResponse endereco) {
		Endereco enderecoBd = this.enderecoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Endereço não existe."));
		BeanUtils.copyProperties(endereco,enderecoBd);
		enderecoBd = this.enderecoRepository.save(enderecoBd);
		EnderecoResponse response = new EnderecoResponse(enderecoBd);
		return response;
	}
}

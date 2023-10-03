package br.senai.labmedicine.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.senai.labmedicine.dtos.EnderecoCadastro;
import br.senai.labmedicine.dtos.EnderecoResponse;
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
		this.enderecoRepository.deleteById(id);
	}
}

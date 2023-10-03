package br.senai.labmedicine.models;

import br.senai.labmedicine.dtos.EnderecoCadastro;
import br.senai.labmedicine.dtos.EnderecoResponse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String logradouro;

    @Column(nullable = false)
    private int numero;
	
    private String complemento;

    @Column(nullable = false)
    private String bairro;
	
    private String referencia;

	public Endereco(EnderecoResponse enderecoDTO) {
		this.estado = enderecoDTO.getEstado();
		this.cidade = enderecoDTO.getCidade();
		this.bairro = enderecoDTO.getBairro();
		this.cep = enderecoDTO.getCep();
		this.logradouro = enderecoDTO.getLogradouro();
		this.referencia = enderecoDTO.getReferencia();
		this.complemento = enderecoDTO.getComplemento();
		this.numero = enderecoDTO.getNumero();
	}

	public Endereco(EnderecoCadastro enderecoDTO) {
		this.estado = enderecoDTO.getEstado();
		this.cidade = enderecoDTO.getCidade();
		this.bairro = enderecoDTO.getBairro();
		this.cep = enderecoDTO.getCep();
		this.logradouro = enderecoDTO.getLogradouro();
		this.referencia = enderecoDTO.getReferencia();
		this.complemento = enderecoDTO.getComplemento();
		this.numero = enderecoDTO.getNumero();
	}
}

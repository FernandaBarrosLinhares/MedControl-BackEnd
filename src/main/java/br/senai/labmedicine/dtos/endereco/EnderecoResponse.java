package br.senai.labmedicine.dtos.endereco;

import br.senai.labmedicine.models.Endereco;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoResponse {
	private Long id;

	@NotBlank(message = "Cep Obrigatório")
	private String cep;

	@NotBlank(message = "Cidade Obrigatório")
	private String cidade;

	@NotBlank(message = "Estado Obrigatório")
	private String estado;

	@NotBlank(message = "Logradouro Obrigatório")
	private String logradouro;

	@NotBlank(message = "Número Obrigatório")
	private String numero;

	private String complemento;

	@NotBlank(message = "Bairro Obrigatório")
	private String bairro;

	private String referencia;

	public EnderecoResponse(Endereco endereco) {
		this.id = endereco.getId();
		this.estado = endereco.getEstado();
		this.cidade = endereco.getCidade();
		this.bairro = endereco.getBairro();
		this.cep = endereco.getCep();
		this.logradouro = endereco.getLogradouro();
		this.referencia = endereco.getReferencia();
		this.complemento = endereco.getComplemento();
		this.numero = endereco.getNumero();
	}

}

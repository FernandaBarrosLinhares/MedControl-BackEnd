package br.senai.labmedicine.dtos.endereco;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoCadastro {
	@NotBlank(message = "Cep Obrigatório")
	@Pattern(regexp = "\\d{8}",message = "O CEP deve ter 8 digitos")
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
}

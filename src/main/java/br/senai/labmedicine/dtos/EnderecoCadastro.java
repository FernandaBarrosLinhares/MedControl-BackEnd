package br.senai.labmedicine.dtos;

import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Range;

import br.senai.labmedicine.models.Endereco;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	@Pattern(regexp = "\\d{5}-\\d{3}",message = "O formato do cep deve ser '00000-000'")
	private String cep;

	@NotBlank(message = "Cidade Obrigatório")
	private String cidade;

	@NotBlank(message = "Estado Obrigatório")
	private String estado;

	@NotBlank(message = "Logradouro Obrigatório")
	private String logradouro;

	@NotNull(message = "Número Obrigatório")
	@Range(min = 0)
	private int numero;

	private String complemento;

	@NotBlank(message = "Bairro Obrigatório")
	private String bairro;

	private String referencia;
}

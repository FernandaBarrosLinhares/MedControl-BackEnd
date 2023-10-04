package br.senai.labmedicine.dtos;

import br.senai.labmedicine.enums.EstadoCivilEnum;
import br.senai.labmedicine.enums.GeneroEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;


import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PacienteCadastroDTO {

    @NotBlank(message ="Nome completo é obrigatório")
    @Min (value=8, message = "Tamanho mínimo para nome completo é 8 caracteres")
    @Max (value=150, message = "Tamanho máximo para nome completo é 150 caracteres")
    private String nomeCompleto;

    @NotNull(message= "Gênero é obrigatório")
    private GeneroEnum genero;

    @CPF @NotBlank (message = "Cpf é obrigatório")
    private String cpf;

    @NotBlank (message ="Telefone é obrigatório")
    @JsonFormat(pattern = "\\d{11}")

    private String telefone;

    @NotBlank (message ="Email é obrigatório")
    @Email
    @Max (value=150, message = "Tamanho máximo para email é 150 caracteres")
    private String email;

    @NotNull (message= "Status é obrigatório")
    private Boolean status;

    @NotNull (message = "Data de nascimento é obrigatória")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @NotBlank (message = "RG e orgão emissor é obrigatório ")
    @Max (value=150, message = "Tamanho máximo para RG é 150 caracteres")
    private String rg;

    @NotNull(message= "Gênero é obrigatório")
    private EstadoCivilEnum estadoCivil;

    @NotBlank (message = "Naturalidade é obrigatório")
    @Max (value=150, message = "Tamanho máximo para naturalidade é 150 caracteres")
    private String naturalidade;


    @JsonFormat(pattern = "\\d{11}")
    private String contatoEmergencia;

    @Max (value=300, message = "Tamanho máximo para lista de alergia é 300 caracteres")
    private String alergias;

    @Max (value=300, message = "Tamanho máximo para cuidados específicos é 300 caracteres")
    private String cuidadosEspecificos;

    @Max (value=150, message = "Tamanho máximo para convênio é 150 caracteres")
    private String convenio;

    @Max (value=20, message = "Tamanho máximo para número do convênio é 20 caracteres")
    private String numeroConvenio;

  //TODO REVER ESTE PONTO
    private String validadeConvenio;

    @NotNull(message = "Endereço deve ser informado")
    private EnderecoResponse endereco;


}

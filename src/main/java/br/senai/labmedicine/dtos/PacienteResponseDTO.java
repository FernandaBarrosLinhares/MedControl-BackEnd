package br.senai.labmedicine.dtos;


import br.senai.labmedicine.enums.GeneroEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PacienteResponseDTO {

    private String nomeCompleto;
    private GeneroEnum genero;
    private String cpf;
    private String telefone;
    private String email;
    private Boolean status;
    private LocalDate dataNascimento;
    private String rg;

    //private EstadoCivilEnum estadoCivil;
    private String naturalidade;
    private String contatoEmergencia;
    private String alergias;
    private String cuidadosEspecificos;
    private String convenio;
    private String numeroConvenio;
    private String validadeConvenio;
    private EnderecoResponse endereco;
}

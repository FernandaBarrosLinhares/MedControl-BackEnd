package br.senai.labmedicine.dtos.paciente;

import br.senai.labmedicine.dtos.endereco.EnderecoCadastro;
import br.senai.labmedicine.enums.EstadoCivilEnum;
import br.senai.labmedicine.enums.GeneroEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PacienteCadastroDTO {

    @NotBlank(message ="Nome completo é obrigatório.")
    @Size(min = 8,max = 64,message = "O nome dever ter de 8 a 64 caracteres.")
    private String nomeCompleto;

    @NotNull(message= "Gênero é obrigatório")
    private GeneroEnum genero;

    @NotNull (message = "Data de nascimento é obrigatória")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @NotNull(message= "Estado Civil é obrigatório")
    private EstadoCivilEnum estadoCivil;

    @CPF(message = "CPF inválido")
    @NotBlank (message = "Cpf é obrigatório")
    @Pattern(regexp = "\\d{11}",message = "CPF: informe apenas números.")
    private String cpf;

    @NotBlank (message ="Telefone é obrigatório")
    @Pattern(regexp = "\\d{11}",message = "Telefone: informe apenas números com DDD")
    private String telefone;

    @NotBlank (message ="Email é obrigatório")
    @Email
    @Size(max = 150,message = "Email deve ter no máximo 150 caracteres.")
    private String email;

    @NotNull (message= "Status é obrigatório")
    private Boolean status;

    @NotBlank (message = "RG e orgão emissor é obrigatório ")
    @Size(max = 150,message = "O RG dever ter até 150 caracteres.")
    private String rg;

    @NotBlank (message = "Naturalidade é obrigatório")
    @Size(min=8,max = 64,message = "A naturalidade dever ter de  8 a 64 caracteres.")
    private String naturalidade;

    @Pattern(regexp= "\\d{11}",message = "telefone em formato inválido: use apenas os números.")
    private String contatoEmergencia;

    @Size(max = 1024,message = "Campo alergias deve ter até 1024 caracteres.")
    private String alergias;

    @Size(max = 1024,message = "Campo Cuidados Específicos deve ter até 1024 caracteres.")
    private String cuidadosEspecificos;

    @Size(max = 200,message = "Campo Convênio deve ter até 200 caracteres.")
    private String convenio;

    @Size(max = 100,message = "Campo número do convênio deve ter até 100 caracteres.")
    private String numeroConvenio;

    @Pattern(regexp = "\\d{2}/\\d{4}",message = "Validade do convênio: informe mês e ano 00/0000.")
    private String validadeConvenio;

    @NotNull(message = "Endereço deve ser informado")
    @Valid
    private EnderecoCadastro endereco;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public PacienteCadastroDTO(String dataNascimento){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatoHorario = DateTimeFormatter.ofPattern("HH:mm:ss");
        if (dataNascimento != null && !dataNascimento.isBlank()) {
            try {
                this.dataNascimento = LocalDate.from(formatter.parse(dataNascimento));
            }catch (DateTimeParseException e){
                throw new DateTimeParseException("Data inválida use formato 00/00/0000", "", 0);
            }
        }
    }

}

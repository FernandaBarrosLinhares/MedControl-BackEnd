package br.senai.labmedicine.dtos.paciente;


import br.senai.labmedicine.dtos.endereco.EnderecoResponse;
import br.senai.labmedicine.enums.EstadoCivilEnum;
import br.senai.labmedicine.enums.GeneroEnum;
import br.senai.labmedicine.models.Paciente;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    private Long id;
    private String nomeCompleto;
    private GeneroEnum genero;
    private String cpf;
    private String telefone;
    private String email;
    private Boolean status;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    private String rg;

    private EstadoCivilEnum estadoCivil;
    private String naturalidade;
    private String contatoEmergencia;
    private String alergias;
    private String cuidadosEspecificos;
    private String convenio;
    private String numeroConvenio;
    @JsonFormat(pattern = "MM/yyyy")
    private String validadeConvenio;
    private EnderecoResponse endereco;

    public PacienteResponseDTO (Paciente paciente) {
        this.id = paciente.getId();
        this.nomeCompleto = paciente.getNomeCompleto();
        this.genero = paciente.getGenero();
        this.cpf = paciente.getCpf ();
        this.telefone = paciente.getTelefone ();
        this.email = paciente.getEmail ();
        this.status = paciente.getStatus ();
        this.dataNascimento = paciente.getDataNascimento ();
        this.rg = paciente.getRg ();
        this.estadoCivil = paciente.getEstadoCivil();
        this.naturalidade = paciente.getNaturalidade ();
        this.contatoEmergencia = paciente.getContatoEmergencia ();
        this.alergias = paciente.getAlergias ();
        this.cuidadosEspecificos = paciente.getCuidadosEspecificos ();
        this.convenio = paciente.getConvenio ();
        this.numeroConvenio = paciente.getNumeroConvenio ();
        this.validadeConvenio = paciente.getValidadeConvenio ();
        this.endereco = new EnderecoResponse(paciente.getEndereco ());
    }
}

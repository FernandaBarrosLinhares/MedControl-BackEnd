package br.senai.labmedicine.models;

import br.senai.labmedicine.dtos.paciente.PacienteCadastroDTO;
import br.senai.labmedicine.enums.EstadoCivilEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Paciente extends Pessoa{

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false,length = 150)
    private String rg;

    @Column(nullable = false)
    private EstadoCivilEnum estadoCivil;

    @Column(nullable = false,length = 64)
    private String naturalidade;

    @Column(nullable = false,length = 11)
    private String contatoEmergencia;

    @Column(length = 1024)
    private String alergias;

    @Column(length = 1024)
    private String cuidadosEspecificos;

    @Column(length = 200)
    private String convenio;

    @Column(length = 100)
    private String numeroConvenio;

    private String validadeConvenio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id",referencedColumnName = "id",nullable = false)
    private Endereco endereco;

    public Paciente(PacienteCadastroDTO pacienteDTO) {
        super(null, pacienteDTO.getNomeCompleto(),
                pacienteDTO.getGenero(), pacienteDTO.getCpf(),
                pacienteDTO.getTelefone(), pacienteDTO.getEmail(),
                pacienteDTO.getStatus());
        this.dataNascimento = pacienteDTO.getDataNascimento();
        this.rg = pacienteDTO.getRg();
        this.estadoCivil = pacienteDTO.getEstadoCivil();
        this.naturalidade = pacienteDTO.getNaturalidade();
        this.contatoEmergencia = pacienteDTO.getContatoEmergencia();
        this.alergias = pacienteDTO.getAlergias();
        this.cuidadosEspecificos = pacienteDTO.getCuidadosEspecificos();
        this.convenio = pacienteDTO.getConvenio();
        this.numeroConvenio = pacienteDTO.getNumeroConvenio();
        this.validadeConvenio = pacienteDTO.getValidadeConvenio();
        this.endereco = new Endereco(pacienteDTO.getEndereco());
    }
}

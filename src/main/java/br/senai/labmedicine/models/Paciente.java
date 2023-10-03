package br.senai.labmedicine.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @Column(nullable = false,length = 20)
    private String rg;

    //@Column(nullable = false)
    //private EstadoCivilEnum estadoCivil;

    @Column(nullable = false,length = 64)
    private String naturalidade;

    @Column(nullable = false)
    private String contatoEmergencia;

    @Column(length = 300)
    private String alergias;

    @Column(length = 300)
    private String cuidadosEspecificos;

    @Column(length = 150)
    private String convenio;

    @Column(length = 20)
    private String numeroConvenio;

    private String validadeConvenio;

    @ManyToOne
    @JoinColumn(name = "endereco_id",referencedColumnName = "id",nullable = false)
    private Endereco endereco;

}

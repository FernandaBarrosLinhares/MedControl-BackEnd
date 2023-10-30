package br.senai.labmedicine.models;

import br.senai.labmedicine.enums.GeneroEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 64)
    private String nomeCompleto;

    @Column(nullable = false)
    private GeneroEnum genero;

    @Column(unique = true,nullable = false,length = 11)
    private String cpf;

    @Column(nullable = false,length = 11)
    private String telefone;

    @Column(unique = true,nullable = false,length = 150)
    private String email;

    @Column(nullable = false)
    private Boolean status;

}

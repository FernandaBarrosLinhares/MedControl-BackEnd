package br.senai.labmedicine.models;

import br.senai.labmedicine.enums.TipoMedicamentosEnum;
import br.senai.labmedicine.enums.UnidadeMedicamentosEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 5, max = 100)
    private String nomeMedicamento;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    @Column(nullable = false)
    private LocalTime horario;

    @Column(nullable = false)
    private TipoMedicamentosEnum tipoMedicamento;

    @Column(nullable = false)
    private Double quantidade;

    @Column(nullable = false)
    private UnidadeMedicamentosEnum unidadeMedicamentos;

    @Column(nullable = false)
    @Size(min = 10, max = 1000)
    private String observacao;

    @Column(nullable = false)
    private Boolean status;
}

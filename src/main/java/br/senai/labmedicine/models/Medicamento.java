package br.senai.labmedicine.models;

import java.time.LocalDate;
import java.time.LocalTime;

import br.senai.labmedicine.dtos.medicamento.MedicamentoResponseDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import br.senai.labmedicine.enums.TipoMedicamentosEnum;
import br.senai.labmedicine.enums.UnidadeMedicamentosEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String nome;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    @Column(nullable = false)
    private LocalTime horario;

    @Column(nullable = false)
    private TipoMedicamentosEnum tipo;

    @Column(nullable = false)
    private Double quantidade;

    @Column(nullable = false)
    private UnidadeMedicamentosEnum unidade;

    @Column(nullable = false)
    @Size(min = 10, max = 1000)
    private String observacao;

    @Column(nullable = false)
    private Boolean status;

    public Medicamento(MedicamentoResponseDTO medicamentoResponseDTO) {
        this.id = medicamentoResponseDTO.getId();
        this.nome = medicamentoResponseDTO.getNome();
        this.data = medicamentoResponseDTO.getData();
        this.horario = medicamentoResponseDTO.getHorario();
        this.tipo = medicamentoResponseDTO.getTipo();
        this.quantidade = medicamentoResponseDTO.getQuantidade();
        this.unidade = medicamentoResponseDTO.getUnidade();
        this.observacao = medicamentoResponseDTO.getObservacao();
        this.status = medicamentoResponseDTO.getStatus();
    }
}

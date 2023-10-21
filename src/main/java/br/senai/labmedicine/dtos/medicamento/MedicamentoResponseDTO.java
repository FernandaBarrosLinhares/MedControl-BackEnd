package br.senai.labmedicine.dtos.medicamento;

import br.senai.labmedicine.enums.TipoMedicamentosEnum;
import br.senai.labmedicine.enums.UnidadeMedicamentosEnum;
import br.senai.labmedicine.models.Medicamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicamentoResponseDTO {
    private Long id;
    private String nome;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;
    private LocalTime horario;
    private TipoMedicamentosEnum tipo;
    private Double quantidade;
    private UnidadeMedicamentosEnum unidade;
    private String observacao;
    private Boolean status;

    public MedicamentoResponseDTO(Medicamento medicamento) {
        this.id = medicamento.getId();
        this.nome = medicamento.getNome();
        this.data = medicamento.getData();
        this.horario = medicamento.getHorario();
        this.tipo = medicamento.getTipo();
        this.quantidade = medicamento.getQuantidade();
        this.unidade = medicamento.getUnidade();
        this.observacao = medicamento.getObservacao();
        this.status = medicamento.getStatus();
    }
}
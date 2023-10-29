package br.senai.labmedicine.dtos.medicamento;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import br.senai.labmedicine.enums.TipoMedicamentosEnum;
import br.senai.labmedicine.enums.UnidadeMedicamentosEnum;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicamentoCadastroDTO {

    @NotBlank(message = "Nome do medicamento deve ser informado.")
    @Size(min = 5, max = 100, message = "Nome deve ter entre 5 e 100 caracteres.")
    private String nome;

    @NotNull(message = "Data deve ser informada.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    @NotNull(message = "Horário deve ser informado.")
    private LocalTime horario;

    @NotNull(message = "Tipo de medicamento deve ser informado.")
    private TipoMedicamentosEnum tipo;

    @NotNull(message = "Quantidade deve ser informada.")
    @DecimalMax("100000000000.00") @DecimalMin("0.00")
    private Double quantidade;

    @NotNull(message = "Unidade de medicamento deve ser informada.")
    private UnidadeMedicamentosEnum unidade;

    @NotBlank(message = "Observações deve ser informada.")
    @Size(min = 10, max = 1000, message = "Nome deve ter entre 10 e 1000 caracteres.")
    private String observacao;

    @NotNull
    private Boolean status;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public MedicamentoCadastroDTO(String data,String horario){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatoHorario = DateTimeFormatter.ofPattern("HH:mm:ss");
        if (data != null && !data.isBlank()) {
            try {
                this.data = LocalDate.from(formatter.parse(data));
            }catch (DateTimeParseException e){
                throw new DateTimeParseException("Data inválida", "", 0);
            }
        }
        if (horario != null && !horario.isBlank()){
            try {
                this.horario = LocalTime.from(formatoHorario.parse(horario));
            }catch (DateTimeParseException e){
                throw new DateTimeParseException("Horário inválido", "", 0);
            }
        }
    }
}

package br.senai.labmedicine.dtos.estatistica;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstattisticaDTO {
    private Long numPacientes;
    private Long numExames;
    private Long numExercicios;
    private Long numConsultas;
    private Long numMedicamentos;
    private Long numDietas;
}

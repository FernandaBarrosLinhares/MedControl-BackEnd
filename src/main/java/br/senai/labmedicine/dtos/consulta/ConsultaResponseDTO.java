package br.senai.labmedicine.dtos.consulta;

import br.senai.labmedicine.dtos.paciente.PacienteResponseDTO;
import br.senai.labmedicine.dtos.medicamento.MedicamentoResponseDTO;
import br.senai.labmedicine.dtos.usuario.UsuarioResponseDTO;

import br.senai.labmedicine.models.Consulta;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaResponseDTO {
    private Long id;
    private String motivo;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;
    private LocalTime horario;
    private String descricao;
    private String dosagensPrecaucoes;
    private Boolean status;
    private PacienteResponseDTO paciente;
    private UsuarioResponseDTO usuario;
    private MedicamentoResponseDTO medicamento;

    public ConsultaResponseDTO(Consulta consulta) {
        this.id = consulta.getId();
        this.motivo = consulta.getMotivo();
        this.data = consulta.getData();
        this.horario = consulta.getHorario();
        this.descricao = consulta.getDescricao();
        this.dosagensPrecaucoes = consulta.getDosagensPrecaucoes();
        this.status = consulta.getStatus();
        this.paciente = new PacienteResponseDTO(consulta.getPaciente());
        this.usuario = new UsuarioResponseDTO(consulta.getUsuario());
        this.medicamento = new MedicamentoResponseDTO(consulta.getMedicamento());
    }
}

package br.senai.labmedicine.dtos.log;

import br.senai.labmedicine.models.Log;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LogResponseDTO extends LogCadastroDTO{

    private Long id;

    public LogResponseDTO(Log log) {
        super(log.getData(), log.getHorario(), log.getMensagem());
        this.id = log.getId();
    }
}

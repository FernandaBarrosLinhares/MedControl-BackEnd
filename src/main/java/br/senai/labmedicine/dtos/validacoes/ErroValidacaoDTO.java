package br.senai.labmedicine.dtos.validacoes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.FieldError;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErroValidacaoDTO {
    private String campo;
    private String mensagem;

    public ErroValidacaoDTO(FieldError error) {
        this.campo = error.getField();
        this.mensagem = error.getDefaultMessage();
    }
}

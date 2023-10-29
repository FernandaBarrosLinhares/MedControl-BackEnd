package br.senai.labmedicine.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum GeneroEnum {

    MASCULINO("Masculino"),
    FEMININO("Feminino"),
    OUTROS("Outros");

    private final String descricao;

    GeneroEnum(String descricao) {
        this.descricao = descricao;

    }
    @JsonCreator
    public static GeneroEnum fromString(String value){
        for(GeneroEnum genero: GeneroEnum.values()){
            try {
                if(genero.name().equals(value)){
                    return genero;
                }
            }catch (NumberFormatException erro){
                throw new NumberFormatException("Gênero informado é inválido.");
            }
        };
        throw new IllegalArgumentException("Gênero informado é inválido");
    }
}

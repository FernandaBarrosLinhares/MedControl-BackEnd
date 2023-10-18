package br.senai.labmedicine.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum EstadoCivilEnum {
    SOLTEIRO("solteiro"),
    CASADO( "casado"),
    SEPARADO( "separado"),
    DIVORCIADO("divorciado"),
    VIUVO("viúvo"),
    UNIAO_ESTAVEL("união estável");

    private final String descricao;


    EstadoCivilEnum(String descricao) {
        this.descricao = descricao;
    }

    @JsonCreator
    public static EstadoCivilEnum fromString(String value){
        for(EstadoCivilEnum estado: EstadoCivilEnum.values()){
            try {
                if(estado.name().equals(value)){
                    return estado;
                }
            }catch (NumberFormatException erro){
                throw new NumberFormatException("Estado civil inválido.");
            }
        };
        throw new IllegalArgumentException("Estado civil inválido");
    }
}

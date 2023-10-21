package br.senai.labmedicine.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum TipoDietaEnum {
    LOW("Low Carb"),
    DASH("Dash"),
    PALEO("Paleolítica"),
    CETO("Cetogênica"),
    DUKAN("Dukan"),
    MEDITERRANEA("Mediterrânea"),
    OUTRA("Outra");


    private final String descricao;

    TipoDietaEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static TipoDietaEnum fromString(String value){
        for(TipoDietaEnum tipo: TipoDietaEnum.values()){
            try {
                if(tipo.name().equals(value)){
                    return tipo;
                }
            }catch (NumberFormatException erro){
                throw new NumberFormatException("Tipo dieta inválida");
            }
        };
        throw new IllegalArgumentException("Tipo dieta inválida");
    }
}

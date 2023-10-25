package br.senai.labmedicine.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UnidadeMedicamentosEnum {
    MG("mg"),
    MGC("mgc"),
    G("g"),
    ML("ml"),
    PORCENTAGEM("%");

    private final String descricao;


    UnidadeMedicamentosEnum(String descricao) {
        this.descricao = descricao;

    }

    @JsonCreator
    public static UnidadeMedicamentosEnum fromString(String value){
        for(UnidadeMedicamentosEnum unidade: UnidadeMedicamentosEnum.values()){
            try {
                if(unidade.name().equals(value)){
                    return unidade;
                }
            }catch (NumberFormatException erro){
                throw new NumberFormatException("Unidade de medicamento inválido.");
            }
        };
        throw new IllegalArgumentException("Unidade de medicamento inválido.");
    }
}

package br.senai.labmedicine.enums;

import lombok.Getter;

@Getter
public enum EstadoCivilEnum {
    SOLTEIRO(1, "solteiro"),
    CASADO(2, "casado"),
    SEPARADO(3, "separado"),
    DIVORCIADO(4, "divorciado"),
    VIUVO(5, "viúvo"),
    UNIAO_ESTAVEL(6, "união estável");

    private final String descricao;
    private final int valor;

    EstadoCivilEnum(int valor,String descricao) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public static EstadoCivilEnum retornarValor(int valor){
        for(EstadoCivilEnum estadoCivil: EstadoCivilEnum.values()){
            if(estadoCivil.valor == valor){
                return estadoCivil;
            }
        }
        throw new IllegalArgumentException("Valor inválido para enum");
    }
}

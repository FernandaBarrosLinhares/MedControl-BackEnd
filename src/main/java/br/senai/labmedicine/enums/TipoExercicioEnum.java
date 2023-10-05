package br.senai.labmedicine.enums;

import lombok.Getter;

@Getter
public enum TipoExercicioEnum {
    RESISTENCIA_AEROBICA(1,"Resistência Aeróbica"),
    RESISTENCIA_MUSCULAR(2,"Resistência Muscular"),
    FLEXIBILIDADE(3,"Flexibilidade"),
    FORCA(4,"Força"),
    AGILIDADE(5,"Agilidade"),
    OUTRO(6,"Outro");

    private final String descricao;
    private final int valor;

    TipoExercicioEnum(int valor,String descricao) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public static TipoExercicioEnum retornarValor(int valor){
        for(TipoExercicioEnum tipoExercicioEnum: TipoExercicioEnum.values()){
            if(tipoExercicioEnum.valor == valor){
                return tipoExercicioEnum;
            }
        }
        throw new IllegalArgumentException("Valor inválido para enum");
    }
}

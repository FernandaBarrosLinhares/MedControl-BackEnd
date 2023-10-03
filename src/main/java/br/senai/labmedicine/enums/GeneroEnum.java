package br.senai.labmedicine.enums;

import lombok.Getter;

@Getter
public enum GeneroEnum {

    MASCULINO(1,"Masculino"),
    FEMININO(2,"Feminino"),
    OUTROS(3,"Outros");

    private final String descricao;
    private final int valor;

    GeneroEnum(int valor,String descricao) {
        this.descricao = descricao;
        this.valor = valor;
    }
    public static GeneroEnum retornarValor(int valor){
        for(GeneroEnum genero: GeneroEnum.values()){
            if(genero.valor == valor){
                return genero;
            }
        }
        throw new IllegalArgumentException("Valor inválido para enum");
    }
}

package br.senai.labmedicine.enums;

public enum UnidadeMedicamentosEnum {
    MG(1, "mg"),
    MGC(2, "mgc"),
    G(3, "g"),
    ML(4, "ml"),
    PORCENTAGEM(5, "%");

    private final String descricao;
    private final int valor;

    UnidadeMedicamentosEnum(int valor, String descricao) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public static UnidadeMedicamentosEnum retornarValor(int valor){
        for(UnidadeMedicamentosEnum unidadeMedicamento: UnidadeMedicamentosEnum.values()){
            if(unidadeMedicamento.valor == valor){
                return unidadeMedicamento;
            }
        }
        throw new IllegalArgumentException("Valor inválido para enum");
    }
}

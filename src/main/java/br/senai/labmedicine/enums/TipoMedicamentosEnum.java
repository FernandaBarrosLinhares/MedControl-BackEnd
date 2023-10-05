package br.senai.labmedicine.enums;

import lombok.Getter;

@Getter
public enum TipoMedicamentosEnum {
    CÁPSULA(1, "Cápsula"),
    COMPRIMIDO(2, "Comprimido"),
    LÍQUIDO(3, "Líquido"),
    CREME(4, "Creme"),
    GEL(5, "Gel"),
    INALAÇÃO(5, "Inalação"),
    INJEÇÃO(6, "Injeção"),
    SPRAY(7, "Spray");

    private final String descricao;
    private final int valor;

    TipoMedicamentosEnum(int valor,String descricao) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public static TipoMedicamentosEnum retornarValor(int valor){
        for(TipoMedicamentosEnum tipoMedicamento: TipoMedicamentosEnum.values()){
            if(tipoMedicamento.valor == valor){
                return tipoMedicamento;
            }
        }
        throw new IllegalArgumentException("Valor inválido para enum");
    }
}

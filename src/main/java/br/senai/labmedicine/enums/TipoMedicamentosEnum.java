package br.senai.labmedicine.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum TipoMedicamentosEnum {
    CAPSULA("Cápsula"),
    COMPRIMIDO("Comprimido"),
    LIQUIDO("Líquido"),
    CREME("Creme"),
    GEL("Gel"),
    INALACAO("Inalação"),
    INJECAO("Injeção"),
    SPRAY("Spray");

    private final String descricao;

    TipoMedicamentosEnum(String descricao) {
        this.descricao = descricao;
    }

    @JsonCreator
    public static TipoMedicamentosEnum fromString(String value){
        for(TipoMedicamentosEnum tipo: TipoMedicamentosEnum.values()){
            try {
                if(tipo.name().equals(value)){
                    return tipo;
                }
            }catch (NumberFormatException erro){
                throw new NumberFormatException("Tipo de medicamento informado é inválido.");
            }
        };
        throw new IllegalArgumentException("Tipo de medicamento informado é inválido");
    }
}

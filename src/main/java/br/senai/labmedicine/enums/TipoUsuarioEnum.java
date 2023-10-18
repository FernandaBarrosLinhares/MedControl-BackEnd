package br.senai.labmedicine.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TipoUsuarioEnum {

    MEDICO("Médico"),
    ADMINISTRADOR("Administrador"),
    ENFERMEIRO("Enfermeiro");

    private final String descricao;

    TipoUsuarioEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {return descricao;}

    @JsonCreator
    public static TipoUsuarioEnum fromString(String value){
        for(TipoUsuarioEnum tipo: TipoUsuarioEnum.values()){
            try {
                if(tipo.name().equals(value)){
                    return tipo;
                }
            }catch (NumberFormatException erro){
                throw new NumberFormatException("Tipo de usuário inválido");
            }
        };
        throw new IllegalArgumentException("Tipo de usuário inválido");
    }
}

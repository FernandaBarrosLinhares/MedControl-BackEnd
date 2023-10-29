package br.senai.labmedicine.dtos.whiteLabel;

import br.senai.labmedicine.models.WhiteLabel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class WhiteLabelDTO {
    private Long id;
    private String nomeEmpresa;
    private String logoUrl;
    private String corFonte;
    private String corPrincipal;

    public WhiteLabelDTO(WhiteLabel config) {
        this.id = config.getId();
        this.nomeEmpresa = config.getNomeEmpresa();
        this.logoUrl = config.getLogoUrl();
        this.corFonte = config.getCorFonte();
        this.corPrincipal = config.getCorPrincipal();
    }
}

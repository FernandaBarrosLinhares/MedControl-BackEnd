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
    private String logo_url;
    private String cor_fonte;
    private String cor_principal;

    public WhiteLabelDTO(WhiteLabel config) {
        this.id = config.getId();
        this.logo_url = config.getLogo_url();
        this.cor_fonte = config.getCor_fonte();
        this.cor_principal = config.getCor_principal();
    }
}

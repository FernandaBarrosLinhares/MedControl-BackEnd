package br.senai.labmedicine.models;


import br.senai.labmedicine.dtos.whiteLabel.WhiteLabelDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WhiteLabel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeEmpresa;
    private String logoUrl;
    private String corFonte;
    private String corPrincipal;

    public WhiteLabel(WhiteLabelDTO config) {
        this.id = config.getId();
        this.nomeEmpresa = config.getNomeEmpresa();
        this.logoUrl = config.getLogoUrl();
        this.corFonte = config.getCorFonte();
        this.corPrincipal = config.getCorPrincipal();
    }
}

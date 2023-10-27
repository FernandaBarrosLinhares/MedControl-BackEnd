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
    private String logo_url;
    private String cor_fonte;
    private String cor_principal;

    public WhiteLabel(WhiteLabelDTO config) {
        this.id = config.getId();
        this.logo_url = config.getLogo_url();
        this.cor_fonte = config.getCor_fonte();
        this.cor_principal = config.getCor_principal();
    }
}

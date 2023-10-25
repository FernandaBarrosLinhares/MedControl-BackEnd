package br.senai.labmedicine.repositories;

import br.senai.labmedicine.models.WhiteLabel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WhiteLabelRepository extends JpaRepository<WhiteLabel,Long> {
}

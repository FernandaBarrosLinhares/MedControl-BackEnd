package br.senai.labmedicine.repositories;

import br.senai.labmedicine.models.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
}

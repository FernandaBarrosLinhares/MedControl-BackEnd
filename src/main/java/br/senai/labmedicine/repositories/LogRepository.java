package br.senai.labmedicine.repositories;

import br.senai.labmedicine.models.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log,Long> {
}

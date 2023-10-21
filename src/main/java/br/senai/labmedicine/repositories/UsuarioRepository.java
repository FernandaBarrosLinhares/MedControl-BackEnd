package br.senai.labmedicine.repositories;

import br.senai.labmedicine.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Usuario findByEmail(String email);

    Boolean existsByCpf(String cpf);
    Boolean existsByEmail(String email);
}

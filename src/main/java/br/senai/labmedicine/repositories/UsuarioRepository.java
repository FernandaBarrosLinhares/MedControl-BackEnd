package br.senai.labmedicine.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.senai.labmedicine.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Usuario findByEmail(String email);

    Boolean existsByCpf(String cpf);
    Boolean existsByEmail(String email);

    @Query(value = "SELECT * FROM usuario WHERE nome_completo ILIKE %:filtro% or email ILIKE %:filtro% or cpf ILIKE :filtro% or telefone ILIKE :filtro%",nativeQuery = true)
    List<Usuario> buscarComFiltro(String filtro);
}

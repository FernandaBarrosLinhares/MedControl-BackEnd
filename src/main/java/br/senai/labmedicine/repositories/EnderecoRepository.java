package br.senai.labmedicine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.senai.labmedicine.models.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}

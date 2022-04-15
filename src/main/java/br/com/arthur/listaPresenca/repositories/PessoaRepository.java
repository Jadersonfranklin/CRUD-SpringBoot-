package br.com.arthur.listaPresenca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.arthur.listaPresenca.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	
}

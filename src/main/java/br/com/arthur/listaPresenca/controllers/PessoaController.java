package br.com.arthur.listaPresenca.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.arthur.listaPresenca.entities.Pessoa;
import br.com.arthur.listaPresenca.repositories.PessoaRepository;

@RestController
@RequestMapping(value = "/convidado")
public class PessoaController {

	@Autowired
	private PessoaRepository repository;
	
	
	@RequestMapping(method = RequestMethod.GET)
    public List<Pessoa> Get() {
        return repository.findAll();
    }
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pessoa> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Pessoa> pessoa = repository.findById(id);
        if(pessoa.isPresent())
            return new ResponseEntity<Pessoa>(pessoa.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
	
	
	@RequestMapping(method =  RequestMethod.POST)
    public Pessoa Post(@RequestBody Pessoa pessoa)
    {
        return repository.save(pessoa);
    }
	
	
	@RequestMapping(value = "/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Pessoa> Put(@PathVariable(value = "id") long id, @RequestBody Pessoa newPessoa)
    {
        Optional<Pessoa> oldPessoa = repository.findById(id);
        if(oldPessoa.isPresent()){
            Pessoa pessoa = oldPessoa.get();
            pessoa.setNome(newPessoa.getNome());
            repository.save(pessoa);
            return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Pessoa> pessoa = repository.findById(id);
        if(pessoa.isPresent()){
            repository.delete(pessoa.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
	
}

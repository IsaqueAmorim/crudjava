package com.encontro.inscricao.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encontro.inscricao.models.Person;
import com.encontro.inscricao.repositories.PersonRepository;

@RestController
@RequestMapping(value = "/persons")
public class PersonResources {
	
	private PersonRepository personRepository;

	public PersonResources(PersonRepository personRepository) {
		super();
		this.personRepository = personRepository;
	}
	
	@PostMapping
	public ResponseEntity<Person> save(@RequestBody Person person){
		personRepository.save(person);
		return new ResponseEntity<>(person,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Person>> getAll(){
		List<Person> persons =	new ArrayList<>();
		persons = personRepository.findAll();
		return new ResponseEntity<>(persons,HttpStatus.OK);
		}
	@GetMapping(path="/{id}")
	public ResponseEntity<Optional<Person>> getById(@PathVariable Long id){
		Optional<Person> person;
		try {
			person = personRepository.findById(id);
			return new ResponseEntity<Optional<Person>>(person,HttpStatus.OK);
		}
		catch(NoSuchElementException nsee){
			return new ResponseEntity<Optional<Person>>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping(value = "/{id}")
	public ResponseEntity<Person> update(@PathVariable Long Id, @RequestBody Person newPerson){
		return 	personRepository.findById(Id)
				.map(person -> {
					person.setNome(newPerson.getNome());
					person.setSobrenome(newPerson.getSobrenome());
					person.setCpf(newPerson.getCpf());
					person.setEmail(newPerson.getEmail());
					person.setEstadoCivil(newPerson.getEstadoCivil());
					person.setGenero(newPerson.getGenero());
					person.setTelefone(newPerson.getTelefone());
					person.setNascimento(newPerson.getNascimento());
					Person personUpdated = personRepository.save(person);
					return ResponseEntity.ok().body(personUpdated);
				}).orElse(ResponseEntity.notFound().build());
		
	}
}

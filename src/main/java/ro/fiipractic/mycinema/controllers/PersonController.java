package ro.fiipractic.mycinema.controllers;

import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.fiipractic.mycinema.entity.Person;
import ro.fiipractic.mycinema.services.PersonService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

@RestController
@RequestMapping("/api/persons")
public class PersonController {
    private final PersonService personService;
    private final ModelMapper modelMapper;

    @Autowired
    public PersonController(PersonService personService, ModelMapper modelMapper) {
        this.personService = personService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<Person> savePerson(@RequestBody Person personToSave) throws URISyntaxException {
        Person person = personService.savePerson(modelMapper.map(personToSave, Person.class));

        return ResponseEntity.created(new URI("/api/persons/" + person.getId())).body(person);
    }

    @GetMapping(value = "/{personId}")
    public Person getPersonById(@PathVariable("personId") Long id) throws NotFoundException {
        return personService.getPersonById(id);
    }

    @GetMapping
    public Collection<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @PutMapping("/updatePerson/{id}")
    public Person updatePerson(@PathVariable("id") Long id, @RequestBody Person updatedPerson) throws NotFoundException {
        //should be thrown a custom BadRequestException if id and personToUpdate.getId() are not equal
        //will learn about it
        // should see what happens if personDB is null
        Person personDb = personService.getPersonById(id);

        modelMapper.map(updatedPerson, personDb);

        return personService.updatePerson(personDb);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/deletePerson/{id}")
    public void deletePerson(@PathVariable("id") Long id) {
        if(personService.personExists(id)) {
            personService.deletePersonById(id);
        } else {
            System.out.println("Entity does not exist!");
        }
    }
}

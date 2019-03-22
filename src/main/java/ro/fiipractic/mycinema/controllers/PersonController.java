package ro.fiipractic.mycinema.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.fiipractic.mycinema.entity.Person;
import ro.fiipractic.mycinema.services.PersonService;

import java.util.Collection;

@RestController
@RequestMapping("/api/persons")
public class PersonController {
    PersonService personService;
    ModelMapper modelMapper;

    @Autowired
    public PersonController(PersonService personService, ModelMapper modelMapper) {
        this.personService = personService;
        this.modelMapper = modelMapper;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/saveMyPerson")
    public Person saveMyPerson(@RequestBody Person personToSave) {
        return personService.savePerson(personToSave);
    }

    @GetMapping(value = "/{personId}")
    public Person getPersonById(@PathVariable("personId") Long id) {
        return personService.getPersonById(id);
    }

    @GetMapping
    public Collection<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/updatePerson/{id}")
    public Person saveMyPerson(@PathVariable("id") Long id, @RequestBody Person updatedPerson) {
        if (personService.personExists(id)) {
            Person personToUpdate = personService.getPersonById(id);
            modelMapper.map(updatedPerson, personToUpdate);
            return personService.updatePerson(personToUpdate);
        } else {
            return personService.updatePerson(updatedPerson);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/deletePerson/{id}")
    public void deletePerson(@PathVariable("id") Long id) {
        if(personService.personExists(id)) {
            personService.deletePerson(id);
        } else {
            System.out.println("Entity does not exist!");
        }
    }
}

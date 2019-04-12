package ro.fiipractic.mycinema.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.fiipractic.mycinema.entity.Person;
import ro.fiipractic.mycinema.exceptions.BadRequestException;
import ro.fiipractic.mycinema.exceptions.NotFoundException;
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

    @GetMapping(value = "/{id}")
    public Person getPersonById(@PathVariable("id") Long id) throws NotFoundException {
        return personService.getPersonById(id);
    }

    @GetMapping
    public Collection<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @PutMapping("/{id}")
    public Person updatePerson(@PathVariable("id") Long id, @RequestBody Person updatedPerson) throws BadRequestException, NotFoundException {
        if (!id.equals(updatedPerson.getId()))
            throw new BadRequestException("Different ids: " + id + " from PathVariable and " + updatedPerson.getId() + " from RequestBody");

        Person personDb = personService.getPersonById(id);
        modelMapper.map(updatedPerson, personDb);
        return personService.updatePerson(personDb);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable("id") Long id) throws NotFoundException {
        if (personService.personExists(id))
            throw new NotFoundException("Person does not exist!");

        personService.deletePersonById(id);
    }
}

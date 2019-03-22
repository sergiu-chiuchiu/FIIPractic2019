package ro.fiipractic.mycinema.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import ro.fiipractic.mycinema.entity.Person;
import ro.fiipractic.mycinema.repositories.PersonRepository;
import ro.fiipractic.mycinema.services.PersonService;

import java.util.Collection;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public Person savePerson(Person personToSave) {
        return personRepository.save(personToSave);
    }

    @Override
    public Collection<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person getPersonById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    @Override
    public Person updatePerson(Person personToUpdate) {
        return personRepository.save(personToUpdate);
    }

    @Override
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    @Override
    public Boolean personExists(Long id) {
        return personRepository.existsById(id);
    }

}

package ro.fiipractic.mycinema.services;

import org.springframework.context.annotation.Bean;
import ro.fiipractic.mycinema.entity.Person;

import java.util.Collection;

public interface PersonService {
    Person savePerson(Person person);
    Collection<Person> getAllPersons();
    Person getPersonById(Long id);
    Person updatePerson(Person personToUpdate);

    void deletePerson(Long id);

    Boolean personExists(Long id);
}

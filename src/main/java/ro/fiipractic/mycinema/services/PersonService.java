package ro.fiipractic.mycinema.services;

import javassist.NotFoundException;
import org.springframework.context.annotation.Bean;
import ro.fiipractic.mycinema.entity.Person;

import java.util.Collection;

public interface PersonService {
    Person savePerson(Person person);
    Collection<Person> getAllPersons();
    Person getPersonById(Long id) throws NotFoundException;
    Person updatePerson(Person personToUpdate);

    void deletePersonById(Long id);

    Boolean personExists(Long id);
}

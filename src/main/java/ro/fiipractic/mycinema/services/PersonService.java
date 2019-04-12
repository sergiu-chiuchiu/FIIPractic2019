package ro.fiipractic.mycinema.services;

import ro.fiipractic.mycinema.entity.Person;
import ro.fiipractic.mycinema.exceptions.NotFoundException;

import java.util.Collection;

public interface PersonService {
    Person savePerson(Person person);
    Collection<Person> getAllPersons();
    Person getPersonById(Long id) throws NotFoundException;
    Person updatePerson(Person personToUpdate);

    void deletePersonById(Long id);

    Boolean personExists(Long id);
}

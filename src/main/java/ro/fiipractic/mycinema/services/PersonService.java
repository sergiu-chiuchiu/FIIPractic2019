package ro.fiipractic.mycinema.services;

import org.springframework.context.annotation.Bean;
import ro.fiipractic.mycinema.entity.Person;

import java.util.Collection;

public interface PersonService {
    String helloFromService();
    Person savePerson(Person person);
    Collection<Person> getAllPersons();
    Person getPersonById(Long id);
}

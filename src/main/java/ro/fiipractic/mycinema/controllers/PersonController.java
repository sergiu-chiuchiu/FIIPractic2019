package ro.fiipractic.mycinema.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.fiipractic.mycinema.entity.Person;
import ro.fiipractic.mycinema.services.PersonService;

import java.util.Collection;

// pt acces la endpoint
@RestController
@RequestMapping("/api/persons")
public class PersonController {
    @Autowired
    PersonService personService;

    @GetMapping("/hello1")
    public String getPersons1() {
        return "Hello1, this is PersonController12";
    }

    @PostMapping("/post/{id}")
    public String getPersons2(@PathVariable Integer id) {
        return "Hello2, this is PersonController2: " + id;
    }

    @PostMapping("/post/")
    public String getPersons3(@RequestBody String nume) {
        return "Hello2, this is PersonController3: " + nume;
    }

    @GetMapping("/hello2")
    public String getPersons4(@RequestParam(name = "id") String nume, @RequestParam String address) {
        if (nume != null && address != null) {
            return "Hello2, this is PersonController3: " + nume + "and the person address is: " + address;
        } else {
            return "no params displayed";
        }
    }

//    @PostMapping("/post2/")
//    public String getPersons5(@RequestBody Person person) {
//        return "Hello2, this is PersonController3: " + person.getLastName()
//                + " with firstName " + person.getFirstName();
//    }

    @GetMapping("/helloService")
    public String helloWorldFromService() {
        return personService.helloFromService();
    }


    @PostMapping("/saveMyPerson")
    public Person saveMyPerson(@RequestBody Person person) {
        return personService.savePerson(person);
    }

    @GetMapping(value = "/getAllPersons")
    public Collection<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping(value = "/getPersonById/{id}")
    public Person getPersonById(@PathVariable Long id) {
        return personService.getPersonById(id);
    }

//    @PutMapping("/updatePerson/{id}")
//    public Person saveMyPerson(@RequestBody Person person, @PathVariable Long id) {
//        Person personToUpdate = getPersonById(id);
//        return personService.updatePerson(person);
//    }

}

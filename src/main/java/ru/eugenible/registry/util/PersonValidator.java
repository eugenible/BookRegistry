package ru.eugenible.registry.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import ru.eugenible.registry.dao.PersonDAO;
import ru.eugenible.registry.models.Person;

import java.util.Optional;

@Component
public class PersonValidator {
    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public boolean supports(Class c) {
        return Person.class.equals(c);
    }

    public void validate(Object o, Errors error) {
//        Person person = (Person) o;
////        Optional<Person> personDB = personDAO.show(person.getEmail());
//        if (personDB.isPresent() && personDB.get().getId() != person.getId()) {
//            error.rejectValue("email", "", "Email is not unique");
//        }
    }

}

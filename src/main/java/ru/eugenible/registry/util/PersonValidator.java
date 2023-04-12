package ru.eugenible.registry.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.eugenible.registry.dao.PersonDAO;
import ru.eugenible.registry.models.Person;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Person.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        Optional<Person> personFromDB = personDAO.findPersonByName(person.getName());
        if (personFromDB.isPresent() && personFromDB.get().getId() != person.getId()) {
            errors.rejectValue("name", "", "Имя неуникально");
        }
    }
}

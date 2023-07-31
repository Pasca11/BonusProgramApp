package ru.amir.bonusprogram.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.amir.bonusprogram.models.Person;
import ru.amir.bonusprogram.repositories.PeopleRepository;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {
    private final PeopleRepository peopleRepository;

    public PersonValidator(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Person.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person customer = (Person) target;
        Optional<Person> find = peopleRepository.findByName(customer.getName());
        if (find.isPresent()) {
            errors.rejectValue("name", "", "Cutstomer with name " +
                    customer.getName() + " already exist");
        }
    }
}

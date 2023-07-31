package ru.amir.bonusprogram.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.amir.bonusprogram.models.Person;
import ru.amir.bonusprogram.repositories.PeopleRepository;

@Service
public class RegisterService {
    private final PeopleRepository peopleRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterService(PeopleRepository peopleRepository, PasswordEncoder passwordEncoder) {
        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole("ROLE_USER");
        peopleRepository.save(person);
    }
}

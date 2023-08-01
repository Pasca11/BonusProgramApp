package ru.amir.bonusprogram.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.amir.bonusprogram.models.Person;
import ru.amir.bonusprogram.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegisterService {
    private final PeopleRepository peopleRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterService(PeopleRepository peopleRepository, PasswordEncoder passwordEncoder) {
        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<Person> findById(int id) {
        return peopleRepository.findById(id);
    }

    @Transactional
    public void register(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole("ROLE_USER");
        peopleRepository.save(person);
    }

    @Transactional(readOnly = true)
    public List<Person> getAllNonAdmin() {
        return peopleRepository.findNonAdmin();
    }

    @Transactional(readOnly = true)
    public List<Person> findByNameLike(String pattern) {
        List<Person> people = peopleRepository.findAllByNameLike(pattern + "%");
        for (Person person : people)
            person.setPhoneNumber(person.getPhoneNumber() == null ? "Номер не указан" : person.getPhoneNumber());
        return people;
    }
}

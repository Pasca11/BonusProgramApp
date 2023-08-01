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

    @Transactional(readOnly = true)
    public Optional<Person> findById(int id) {
        Optional<Person> person = peopleRepository.findById(id);
        person.get().setPhoneNumber(person.get().getPhoneNumber() == null ? "Номер не указан" : person.get().getPhoneNumber());
        return person;
    }

    @Transactional(readOnly = true)
    public Optional<Person> findByName(String name) {
        return peopleRepository.findByName(name);
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

    @Transactional
    public void changeRole(int id, Person person) {
        Optional<Person> found = peopleRepository.findById(id);
        found.get().setRole(person.getRole());
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    @Transactional
    public void update(int id, Person person) {
        Person found = peopleRepository.findById(id).get();
        found.setName(person.getName());
        found.setPhoneNumber(person.getPhoneNumber());
        found.setBirthday(person.getBirthday());
    }
}

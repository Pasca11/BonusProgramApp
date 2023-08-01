package ru.amir.bonusprogram.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.amir.bonusprogram.models.Person;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByName(String name);

    @Query("select p from Person p where p.role <> 'ROLE_ADMIN'")
    List<Person> findNonAdmin();

    List<Person> findAllByNameLike(String pattern);

}

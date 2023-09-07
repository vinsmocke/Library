package com.myapp.library.repositories;

import com.myapp.library.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PeopleRepository extends JpaRepository<Person, Integer> {
    Person findByEmail(String email);
}

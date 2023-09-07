package com.myapp.library.services;

import com.myapp.library.dto.PersonDto;
import com.myapp.library.models.Person;

import java.util.List;

public interface PersonService {
    void save (PersonDto person);
    Person findPersonByEmail(String email);
    List<PersonDto> findAllPeople();
}

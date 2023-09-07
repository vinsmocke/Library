package com.myapp.library.services;

import com.myapp.library.dto.PersonDto;
import com.myapp.library.models.Person;
import com.myapp.library.models.Role;
import com.myapp.library.repositories.PeopleRepository;
import com.myapp.library.repositories.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService{
    private PeopleRepository peopleRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public PersonServiceImpl(PeopleRepository peopleRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.peopleRepository = peopleRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void save(PersonDto personDto) {
        Person person = new Person();
        person.setName(personDto.getFirstName() + " " + personDto.getLastName());
        person.setEmail(personDto.getEmail());
        person.setPassword(passwordEncoder.encode(personDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_USER");
        if (role == null){
            role = checkRoleExist();
        }

        person.setRoles(List.of(role));
        peopleRepository.save(person);
    }

    @Override
    public Person findPersonByEmail(String email) {
        return peopleRepository.findByEmail(email);
    }

    @Override
    public List<PersonDto> findAllPeople() {
        List<Person> personList = peopleRepository.findAll();
        return personList.stream()
                .map((person) -> mapToPersonDto(person))
                .collect(Collectors.toList());
    }

    private PersonDto mapToPersonDto(Person person) {
        PersonDto personDto = new PersonDto();
        String[] str = person.getName().split(" ");
        personDto.setFirstName(str[0]);
        personDto.setLastName(str[1]);
        personDto.setEmail(person.getEmail());

        return personDto;
    }

    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_USER");
        return roleRepository.save(role);
    }
}

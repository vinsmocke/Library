package com.myapp.library.controllers;

import com.myapp.library.dto.PersonDto;
import com.myapp.library.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private PersonService personService;
    @GetMapping("/users")
    public String allUsers(Model model) {
        List<PersonDto> people = personService.findAllPeople();
        model.addAttribute("people", people);

        return "admin/users";
    }
}

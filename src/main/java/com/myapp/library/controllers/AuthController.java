package com.myapp.library.controllers;

import com.myapp.library.dto.PersonDto;
import com.myapp.library.models.Person;
import com.myapp.library.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private PersonService personService;

    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(Model model){
        PersonDto person = new PersonDto();
        model.addAttribute("person", person);
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("person") PersonDto person,
                               BindingResult bindingResult,
                               Model model){
        Person existingPerson = personService.findPersonByEmail(person.getEmail());

        if (existingPerson != null && existingPerson.getEmail() != null && !existingPerson.getEmail().isEmpty()){
            bindingResult.rejectValue("email", null, "Обліковий запис з такою поштою вже існує");
        }

        if (bindingResult.hasErrors()){
            model.addAttribute("person", person);
            return "auth/registration";
        }

        personService.save(person);
        return "redirect: /auth/registration?success";
    }
}

package ru.amir.bonusprogram.controllers;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.amir.bonusprogram.models.Person;
import ru.amir.bonusprogram.security.PersonDetails;
import ru.amir.bonusprogram.services.RegisterService;

@Controller
@RequestMapping("/account")
public class AccountController {
    private final RegisterService registerService;

    public AccountController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping
    public String account(Model model) {
        SecurityContext context = SecurityContextHolder.getContext();
        PersonDetails personDetails = (PersonDetails) context.getAuthentication().getPrincipal();
        Person person = registerService.findByName(personDetails.getUsername()).get();
        model.addAttribute("person", person);
        model.addAttribute("card", person.getBonusCard());
        return "user/show";
    }
}

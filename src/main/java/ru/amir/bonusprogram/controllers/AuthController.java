package ru.amir.bonusprogram.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.amir.bonusprogram.models.Person;
import ru.amir.bonusprogram.services.RegisterService;
import ru.amir.bonusprogram.util.PersonValidator;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final PersonValidator personValidator;
    private final RegisterService registerService;

    public AuthController(PersonValidator customersValidator, RegisterService registerService) {
        this.personValidator = customersValidator;
        this.registerService = registerService;
    }

    @GetMapping("/registration")
    public String register(@ModelAttribute("person") Person person) {
        return "/auth/registration";
    }

    @GetMapping("/login")
    public String login() {
        return "/auth/login";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/auth/registration";
        }
        registerService.register(person);
        return "redirect:/login";
    }
}

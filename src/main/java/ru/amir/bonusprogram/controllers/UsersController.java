package ru.amir.bonusprogram.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.amir.bonusprogram.models.Person;
import ru.amir.bonusprogram.services.RegisterService;
import ru.amir.bonusprogram.util.Roles;

import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final RegisterService registerService;

    public UsersController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping
    public String search(@RequestParam(value = "pattern", required = false) Optional<String> pattern,
                         Model model) {
        if (pattern.isPresent()) {
            model.addAttribute("hasPattern", true);
            model.addAttribute("people", registerService.findByNameLike(pattern.get()));
        } else {
            model.addAttribute("hasPattern", false);
        }
        return "/user/users";
    }

    @GetMapping("/{id}")
    public String showUser(@RequestParam("is") int id,
                           Model model) {
        Optional<Person> found = registerService.findById(id);
        if (found.isEmpty()) {
            model.addAttribute("errorMes", "No such user");
        } else {
            model.addAttribute("person", found.get());
            model.addAttribute("roles", Roles.values());
        }
        return "/user/show";
    }
}

package ru.amir.bonusprogram.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.amir.bonusprogram.models.Person;
import ru.amir.bonusprogram.services.RegisterService;
import ru.amir.bonusprogram.util.Roles;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public String showUser(@PathVariable("id") int id,
                           Model model) {
        Optional<Person> found = registerService.findById(id);
        if (found.isEmpty()) {
            model.addAttribute("errorMes", "No such user");
        } else {
            model.addAttribute("person", found.get());
            model.addAttribute("roles", Arrays.stream(Roles.values()).map(Enum::name).collect(Collectors.toList()));
        }
        return "/user/show";
    }

    @GetMapping("/{id}/edit")
    public String editUserPage(@PathVariable("id") int id,
                           Model model) {
        model.addAttribute("person", registerService.findById(id).get());
        return "user/edit";
    }

    @PatchMapping("/{id}")
    public String editUser(@PathVariable("id") int id,
                           @ModelAttribute("person") Person person,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/edit";
        }
        registerService.update(id, person);
        return "redirect:/users/" + id;
    }

    @PatchMapping("/{id}/changeRole")
    public String changeRole(@PathVariable("id") int id,
                             @ModelAttribute("person") Person person) {
        registerService.changeRole(id, person);
        return "redirect:/users/" + id;
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        registerService.delete(id);
        return "redirect:/users";
    }
}

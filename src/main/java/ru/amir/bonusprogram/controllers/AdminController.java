package ru.amir.bonusprogram.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.amir.bonusprogram.models.Person;
import ru.amir.bonusprogram.services.RegisterService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final RegisterService registerService;

    public AdminController(RegisterService registerService) {
        this.registerService = registerService;
    }


    @GetMapping
    public String adminPage() {
        return "admin/menu";
    }
}

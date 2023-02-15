package ru.eugenible.registry.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.eugenible.registry.dao.PersonDAO;

@Controller
@RequestMapping("/people")
public class PeopleController {


    private PersonDAO personDAO;

    @Autowired
    private PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String list(Model model) {
        System.out.println("In controller");
        model.addAttribute("people", personDAO.list());
        System.out.println(personDAO.list());
        return "people/list";
    }
}

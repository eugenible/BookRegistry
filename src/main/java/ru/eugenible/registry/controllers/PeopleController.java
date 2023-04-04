package ru.eugenible.registry.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.eugenible.registry.dao.PersonDAO;
import ru.eugenible.registry.models.Person;

import javax.validation.Valid;

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
        model.addAttribute("people", personDAO.list());
        return "people/list";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
//        Person person = personDAO.show(id);
//        if (person == null) return "redirect:/people";
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping
    public String save(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String update(Model model, @PathVariable int id) {
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute Person person, @PathVariable("id") int id) {
        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);

        return "redirect:/people";
    }
}

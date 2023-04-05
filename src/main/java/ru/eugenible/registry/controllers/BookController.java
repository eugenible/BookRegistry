package ru.eugenible.registry.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.eugenible.registry.dao.BookDAO;
import ru.eugenible.registry.dao.PersonDAO;
import ru.eugenible.registry.models.Book;
import ru.eugenible.registry.models.Person;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private BookDAO bookDAO;
    private PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("books", bookDAO.list());
        return "books/list";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model) {
        model.addAttribute("book", bookDAO.show(id));
        return "books/show";
    }

    @GetMapping("/new")
    public String create(@ModelAttribute Book book, Model model) {
        model.addAttribute("people", personDAO.list());
        return "books/new";
    }

    @PostMapping
    public String save(@ModelAttribute Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "books/new";
        bookDAO.save(book);
        return "redirect:/books";
    }





}

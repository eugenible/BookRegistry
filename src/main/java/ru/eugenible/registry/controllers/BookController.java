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
        model.addAttribute("people", personDAO.list());
        model.addAttribute("person", new Person());
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

    @DeleteMapping("/{id}/delete-owner")
    public String delete(@PathVariable int id) {
        Book book = bookDAO.show(id);
        book.setOwner(null);
        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String update(Model model, @PathVariable int id) {
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable int id, @ModelAttribute Book book, BindingResult bindingResult) {
        System.out.println("In update");
//        if (bindingResult.hasErrors()) return "books/edit";
        System.out.println("1");
        bookDAO.update(id, book);
        System.out.println("2");
        return "redirect:/books";
    }

    @PatchMapping("/{id}/delete-owner")
    public String deleteOwner(@PathVariable int id) {
        System.out.println("Зашли v delete");
        Book book = bookDAO.show(id);
        book.setOwner(null);
        bookDAO.update(id, book);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assignOwner(@ModelAttribute Person person, @PathVariable("id") int bookId) {
        Book book = bookDAO.show(bookId);
        book.setOwner(person);
        return "redirect:/books/" + bookId;
    }


}

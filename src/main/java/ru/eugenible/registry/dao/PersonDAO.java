package ru.eugenible.registry.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.eugenible.registry.models.Book;
import ru.eugenible.registry.models.Person;

import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    private PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> list() {
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        Person person = jdbcTemplate.query("SELECT * FROM person WHERE id = ?",
                new BeanPropertyRowMapper<>(Person.class), id).stream().findAny().orElse(null);
        if (person == null) return null;

        List<Book> books = jdbcTemplate.query(
                "SELECT * FROM person p INNER JOIN book b ON (p.id = b.person_id) WHERE p.id = ?", (rs, row) -> {
                    Book book = new Book();
                    book.setTitle(rs.getString("title"));
                    book.setAuthor(rs.getString("author"));
                    book.setYear(rs.getInt("year"));
                    return book;
                }, id);
        person.setBooks(books);
        return person;
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id = ?", id);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person(name, age) VALUES(?, ?)", person.getName(), person.getAge());
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE person SET name = ?, age = ? WHERE id = ?", person.getName(), person.getAge(), id);
    }
}

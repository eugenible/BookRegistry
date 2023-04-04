package ru.eugenible.registry.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.eugenible.registry.models.Person;

import java.util.List;

@Component
public class PersonDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> list() {
        List<Person> list = jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
        return list;
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE id = ?",
                new BeanPropertyRowMapper<>(Person.class), id).stream().findAny().orElse(null);
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

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
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM person WHERE id = ?",
                new BeanPropertyRowMapper<>(Person.class), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id = ?", id);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person(name, age) VALUES(?, ?)", person.getName(), person.getAge());
    }


}

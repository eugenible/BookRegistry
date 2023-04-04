package ru.eugenible.registry.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.eugenible.registry.models.Book;

import java.util.List;

@Component
public class BookDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> list() {
        return jdbcTemplate.query("SELECT * FROM book", (rs, row) -> {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setAuthor(rs.getString("author"));
            book.setTitle(rs.getString("title"));
            book.setYear(rs.getInt("year"));
            return book;
        });
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE ID = ?", new BeanPropertyRowMapper<>(Book.class),
                id).stream().findAny().orElse(null);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id = ?");
    }

    public void update(int it, Book book) {
        jdbcTemplate.update("UPDATE book SET title = ?, author = ?, year= ?, person_id = ? WHERE id = ?",
                book.getTitle(), book.getAuthor(), book.getYear());
    }


}

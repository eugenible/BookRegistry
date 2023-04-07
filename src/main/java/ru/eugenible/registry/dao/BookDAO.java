package ru.eugenible.registry.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.eugenible.registry.models.Book;
import ru.eugenible.registry.models.Person;

import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

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
        Book book = jdbcTemplate.query("SELECT * FROM book WHERE ID = ?", new BeanPropertyRowMapper<>(Book.class),
                id).stream().findAny().orElse(null);
        if (book == null) return null;

        // В человека не вставляем список книг, т.к. в данном контексте нам важны его личные характеристики
        Person owner = jdbcTemplate.query(
                "SELECT p.id, p.age, p.name FROM person p INNER JOIN book b ON b.person_id = p.id WHERE b.id = ?",
                new BeanPropertyRowMapper<>(Person.class), id).stream().findAny().orElse(null);
        book.setOwner(owner);

        return book;
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id = ?", id);
    }

    public void update(int id, Book book) {
        Object personId = book.getOwner() == null ? null : book.getOwner().getId();
        jdbcTemplate.update("UPDATE book SET title = ?, author = ?, year= ?, person_id = ? WHERE id = ?",
                book.getTitle(), book.getAuthor(), book.getYear(), personId, id);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book(title, author, year) VALUES(?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getYear());
    }

    public void assignOwner(int id, Person owner) {
        jdbcTemplate.update("UPDATE book SET person_id = ? WHERE id = ?", owner.getId(), id);
    }

    public void releaseOwner(int bookId) {
        jdbcTemplate.update("UPDATE book SET person_id = ? where id = ?", null, bookId);
    }
}

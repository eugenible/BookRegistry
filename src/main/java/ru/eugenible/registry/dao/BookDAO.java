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

    private JdbcTemplate jdbcTemplate;

    private PersonDAO personDAO;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate, PersonDAO personDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.personDAO = personDAO;
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
        Book book = jdbcTemplate.query("SELECT * FROM book WHERE ID = ?", (rs, row) -> {
                    Book mappedBook = new Book();
                    mappedBook.setId(id);
                    mappedBook.setAuthor(rs.getString("author"));
                    mappedBook.setTitle(rs.getString("title"));
                    mappedBook.setYear(rs.getInt("year"));

                    int ownerId = rs.getInt("person_id");
                    Person owner = personDAO.show(ownerId);
                    mappedBook.setPerson(owner);
                    return mappedBook;
                },
                id).stream().findAny().orElse(null);

        return book;
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id = ?");
    }

    public void update(int id, Book book) {
        jdbcTemplate.update("UPDATE book SET title = ?, author = ?, year= ?, person_id = ? WHERE id = ?",
                book.getTitle(), book.getAuthor(), book.getYear(), book.getPerson());
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book(title, author, year) VALUES(?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getYear());
    }


}

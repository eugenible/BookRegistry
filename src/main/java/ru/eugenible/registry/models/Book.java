package ru.eugenible.registry.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {

    private int id;

    @NotEmpty(message = "Название книги не может быть пустым")
    @Size(min = 2, max = 30, message = "Длина названия книги должна быть от 2 до 30 символов")
    private String title;

    @NotEmpty(message = "Имя автора не может быть пустым")
    @Size(min = 2, max = 40, message = "Длина имени автора должна быть в пределах от 2 до 40 символов")
    private String author;

    @Min(value = 1900, message = "Год выпуска книги не может быть быть ниже, чем 1900")
    @Max(value = 2023, message = "Год выпуска книги не должен превышать 2023 год")
    private int year;
    private Person owner;

    public Book(int id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Book() {
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}

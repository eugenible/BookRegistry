package ru.eugenible.registry.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class Person {

    private int id;

    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2, max = 40, message = "Длина имени читателя должна быть в пределах от 2 до 40 символов")
    private String name;

    @Min(value = 14, message = "Указанный возраст не может быть меньше минимального, равного 14 годам")
    private int age;

    private List<Book> books;


    public Person(int id, String name, int age, List<Book> books) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.books = books;
    }

    public Person() {
        this.books = new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

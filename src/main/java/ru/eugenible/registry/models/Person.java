package ru.eugenible.registry.models;

import java.util.ArrayList;
import java.util.List;

public class Person {

    private int id;
    private String name;
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

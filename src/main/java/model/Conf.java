package model;

import java.util.ArrayList;

public class Conf {

    private int books;
    private int libraries;
    private int days;

    public Conf(String[] conf) {
        books = Integer.parseInt(conf[0]);
        libraries = Integer.parseInt(conf[1]);
        days = Integer.parseInt(conf[2]);
    }

    public int getBooks() {
        return books;
    }

    public void setBooks(int books) {
        this.books = books;
    }

    public int getLibraries() {
        return libraries;
    }

    public void setLibraries(int libraries) {
        this.libraries = libraries;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}

package model;

import main.Launcher;

import java.util.ArrayList;
import java.util.TreeSet;

public class Library implements Comparable<Library> {

    private int id;
    private int numBooks;
    private int signUpTime;
    private int booksPerDay;

    private Double scorePerDay;

    private TreeSet<Book> books;

    public Library(String[] conf, int id) {
        this.id = id;
        numBooks = Integer.parseInt(conf[0]);
        signUpTime = Integer.parseInt(conf[1]);
        booksPerDay = Integer.parseInt(conf[2]);
        books = new TreeSet<>();
    }

    public int getNumBooks() {
        return numBooks;
    }

    public void setNumBooks(int numBooks) {
        this.numBooks = numBooks;
    }

    public int getSignUpTime() {
        return signUpTime;
    }

    public void setSignUpTime(int signUpTime) {
        this.signUpTime = signUpTime;
    }

    public int getBooksPerDay() {
        return booksPerDay;
    }

    public void setBooksPerDay(int booksPerDay) {
        this.booksPerDay = booksPerDay;
    }

    public TreeSet<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books.clear();
        this.books.addAll(books);
        numBooks = books.size();
    }

    public void addBook(Book b) {
        books.add(b);
    }

    public int getId() {
        return id;
    }

    public void addBooks(TreeSet<Book> books) {
        this.books.addAll(books);
    }

    public Double getScorePerDay() {
        return scorePerDay;
    }

    public void recalculateScore(int remainingDays) {
        int remainingMinusSignUp = Math.max(remainingDays - signUpTime, 0);
        int numRemainingBooks = remainingMinusSignUp / booksPerDay;
        long remainingBooksScore =
                books.stream().filter(book -> !book.isScanned() && book.getScore() > Launcher.maxScore / 2).limit(numRemainingBooks).mapToLong(Book::getScore).sum();
        scorePerDay = remainingMinusSignUp > 0 ? (double) remainingBooksScore / signUpTime : 0;
    }

    public int compareTo(Library o) {
        int cmp = o.getScorePerDay().compareTo(scorePerDay);
        return cmp == 0 ? Integer.compare(o.getId(), id) : cmp;
    }

    @Override
    public String toString() {

        StringBuilder out = new StringBuilder(String.format("%d %d\n", id, numBooks));

        for (Book b : books) {
            out.append(b.toString());
            out.append(" ");
        }

        out.append("\n");

        return out.toString();
    }
}

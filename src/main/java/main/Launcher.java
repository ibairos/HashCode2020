package main;

import model.Book;
import model.Conf;
import model.Library;
import util.DataParser;
import util.FileIO;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.TreeSet;

public class Launcher {

    public static Conf conf;
    public static TreeSet<Library> libraries;
    public static ArrayList<Book> books;
    public static ArrayList<Library> outputLibraries;
    public static Long maxScore = 0L;

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(args[0]);
        books = new ArrayList<>();
        libraries = new TreeSet<>();
        outputLibraries = new ArrayList<>();

        DataParser.parseData(FileIO.readFile(args[0]));

        //System.out.println(new Gson().toJson(libraries));
        //System.out.println(new Gson().toJson(books));

        boolean finished = false;
        int remainingDays = conf.getDays();

        while (!finished) {
            Library l = libraries.pollFirst();

            if (remainingDays <= 1 || l == null) {
                finished = true;
            } else if (remainingDays > l.getSignUpTime()) {
                int numOutputBooks = Math.min(l.getNumBooks(), remainingDays / l.getBooksPerDay());

                ArrayList<Book> outputBooks = new ArrayList<>();

                for (int i = 0; i < numOutputBooks; i++) {
                    Book b = l.getBooks().pollFirst();
                    assert b != null;
                    if (!b.isScanned() && b.getScore() > maxScore / 2) {
                        outputBooks.add(b);
                        b.setScanned(true);
                    }
                }

                if (outputBooks.size() == 0) {
                    continue;
                }

                l.setBooks(outputBooks);

                outputLibraries.add(l);

                remainingDays -= l.getSignUpTime();

                for (Library lib : libraries) {
                    lib.recalculateScore(remainingDays);
                }

                libraries = new TreeSet<>(libraries);

            }
        }

        FileIO.writeOutputFile(outputLibraries, args[1]);

    }
}

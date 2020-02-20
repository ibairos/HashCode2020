package util;

import main.Launcher;
import model.Book;
import model.Conf;
import model.Library;

import java.util.Scanner;

public class DataParser {

    public static void parseData(Scanner sc) {


        Launcher.conf = new Conf(sc.nextLine().split(" "));
        String[] books = sc.nextLine().split(" ");
        for (int i = 0; i < books.length; i++) {
            Long score = Long.parseLong(books[i]);
            Launcher.maxScore = Launcher.maxScore < score ? score : Launcher.maxScore;
            Launcher.books.add(new Book(i, score));
        }

        int libraryIndex = 0;

        while (sc.hasNext()) {
            Library l = new Library(sc.nextLine().split(" "), libraryIndex);
            String[] libraryBooks = sc.nextLine().split(" ");
            for (String libraryBook : libraryBooks) {
                l.addBook(Launcher.books.get(Integer.parseInt(libraryBook)));
            }
            l.recalculateScore(Launcher.conf.getDays());
            Launcher.libraries.add(l);

            libraryIndex++;
        }
    }
}

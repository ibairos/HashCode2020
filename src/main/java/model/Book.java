package model;

public class Book implements Comparable<Book> {

    private int id;
    private Long score;
    private boolean scanned;

    public Book(int id, Long score) {
        this.id = id;
        this.score = score;
        scanned = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public boolean isScanned() {
        return scanned;
    }

    public void setScanned(boolean scanned) {
        this.scanned = scanned;
    }

    @Override
    public int compareTo(Book o) {
        int cmp = o.getScore().compareTo(score);
        return cmp == 0 ? Integer.compare(o.getId(), id) : cmp;
    }

    @Override
    public String toString() {
        return "" + id;
    }
}

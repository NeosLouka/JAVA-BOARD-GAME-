package Model;

import java.util.List;

public class Square {

    private final int points;
    private Finding finding;
    private List<Pawn> pawnsInSquare;


    public Square( Finding finding ,  int points ) {
        this.finding = finding;
        this.points = points;
    }

    public List<Pawn> getPawnsInSquare() {
        return pawnsInSquare;
    }

    public int getPoints() {
        return points;
    }

    public Finding getFinding() {
        return finding;
    }

    public void setFinding(Finding finding) {
        this.finding = finding;
    }
}

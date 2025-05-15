import Model.*;
import View.*;
import Controller.*;


import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Deck deck = new Deck();
        Board board = new Board();
        View view = new View();
        Controller controller = new Controller(deck, board, view);


        view.setController(controller); // Σύνδεση του Controller με τη View
        controller.runGameLoop(); // Ξεκινά το παιχνίδι
    }
}


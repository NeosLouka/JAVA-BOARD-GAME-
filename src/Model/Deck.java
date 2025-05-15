package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();

        // Μονοπάτια
        String[] paths = {"Knossos", "Malia", "Phaistos", "Zakros"};

        // Δημιουργία καρτών για κάθε μονοπάτι
        for (String path : paths) {
            // Προσθήκη απλών καρτών (1-10, δύο φορές)
            for (int i = 1; i <= 10; i++) {
                cards.add(new SimpleCard(i, path)); // Πρώτη κάρτα
                cards.add(new SimpleCard(i, path)); // Δεύτερη κάρτα
            }

            // Προσθήκη 3 καρτών Μίτου Αριάδνης
            for (int i = 0; i < 3; i++) {
                cards.add(new MitosAriadnisCard(-1,path));
            }

            // Προσθήκη 2 καρτών Μινώταυρου
            for (int i = 0; i < 2; i++) {
                cards.add(new MinotaurCard(0, path));
            }
        }

        // Ανακάτεμα deck
        Collections.shuffle(cards);
    }

    public Card drawCard(){
        if(!cards.isEmpty()){
            return cards.removeFirst();
        }
        return null;
    }

    // Get remaining card count
    public int getRemainingCards() {
        return cards.size();
    }




}

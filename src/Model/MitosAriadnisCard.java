package Model;

public class MitosAriadnisCard extends Card {

    public MitosAriadnisCard(int number , String path) {
        super(number, "MitosAriadnis"); // This card doesn't rely on a number
        setPath(path);
    }

    @Override
    public void movePawn(Pawn pawn ) {
        int oldPosition = pawn.getPosition();
        int newPosition = oldPosition + 2;

        // Έλεγχος για να μην ξεπερνά το 9ο κουτάκι
        if (newPosition > 8) {
            newPosition = 8; // Περιορισμός στη μέγιστη θέση
        }

        // Ενημέρωση θέσης πιονιού
        pawn.setPosition(newPosition);
        System.out.println("Pawn moved to position: " + newPosition);
    }


}

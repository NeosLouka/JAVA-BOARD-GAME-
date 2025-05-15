package Model;

public class SimpleCard extends Card {

    public SimpleCard(int number , String path) {
        super(number, path);
        setPath(path);
    }

    @Override
    public void movePawn(Pawn pawn) {
        int oldPosition = pawn.getPosition();
        int newPosition = oldPosition + 1;

        // Έλεγχος για να μην ξεπερνά το 9ο κουτάκι
        if (newPosition > 8) {
            newPosition = 8; // Περιορισμός στη μέγιστη θέση
        }

        // Ενημέρωση θέσης πιονιού
        pawn.setPosition(newPosition);
        System.out.println(STR."Pawn moved to position: \{newPosition}");
        System.out.println("Pawn moved to position: " + newPosition);

    }

}

package Model;

import java.util.List;

public class MinotaurCard extends Card {

    public MinotaurCard(int number ,String path) {
        super(number, "MinotaurCard"); // MinotaurCard doesn't have a number
        setPath(path);
    }

    @Override
    public void movePawn(Pawn pawn  ) {
        Path path = pawn.getPath(); // Παίρνουμε το path του πιονιού
        List<Pawn> pawnsOnPath = path.getPawnsOnPath(); // Λαμβάνουμε όλα τα πιόνια στο path
        int currentPlayerId = pawn.getOwnerId(); // Παίρνουμε τον ιδιοκτήτη του πιονιού

        Pawn opponentPawn = null;

        // Βρες το πιόνι του αντιπάλου στο ίδιο path
        for (Pawn otherPawn : pawnsOnPath) {
            if (otherPawn.getOwnerId() != currentPlayerId) {
                opponentPawn = otherPawn;
                break;
            }
        }

        if (opponentPawn != null) {
            // Υπολογισμός της νέας θέσης του αντιπάλου πιονιού
            int newPosition = opponentPawn.getPosition() - 2;
            newPosition = Math.max(newPosition, 0); // Διασφάλιση ότι η θέση δεν είναι αρνητική

            // Μετακίνηση του πιονιού
            opponentPawn.setPosition(newPosition);
            System.out.println(STR."Moved opponent's pawn to position: \{newPosition}");
        } else {
            System.out.println("No opponent pawn found on the same path.");
        }


    }

}

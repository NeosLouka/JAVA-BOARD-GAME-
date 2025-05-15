package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
    private final int id;
    private final List<Pawn> pawns; // List of all pawns (archaeologists and Theseus)

    private final List<Card> handCards; // Οι 8 κάρτες στο χέρι
    private final List<Card> playedCards; // Οι τελευταίες κάρτες που έπαιξε
    private final List<Finding> collection; // Οι τοιχογραφίες που έχει συλλέξει
    private final List<Finding> rareFindings; // Τα σπάνια ευρήματα που έχει συλλέξει

    private int score; // Το σκορ του παίκτη
    private int statues; // Αριθμός αγαλμάτων
    private boolean checkpointsReached; // Σημεία ελέγχου
    private int archaeologistCount; // Πιόνια αρχαιολόγων
    private List<String> actionHistory; // Ιστορικό ενεργειών

    private boolean turn;

    /**
     * Constructs a new instance of Player with the specified name and turn status.
     *
     * @param id the id of the player
     * @param turn the initial turn status of the player, where true indicates it's the player's turn
     */
    public Player(int id, boolean turn) {

        this.id = id;
        pawns = new ArrayList<>();
        // Add Theseus
        pawns.add(new Pawn("Theseus" , id));
        pawns.getFirst().setOwnerId(id);
        // Add 3 Archaeologists
        for (int i = 0; i < 3; i++) {
            pawns.add(new Pawn("Archaeologist" , id));
            pawns.getLast().setOwnerId(id);
        }

        this.handCards = new ArrayList<>();
        playedCards = new ArrayList<>(Collections.nCopies(4, null)); // Δημιουργία λίστας 4 θέσεων με αρχικές τιμές null
        this.collection = new ArrayList<>();
        this.rareFindings = new ArrayList<>();
        this.checkpointsReached = false;
        this.archaeologistCount = 3; // The count starts at 3
    }


    public List<Pawn> getPawns() {
        return pawns;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public boolean placePawnOnPath(Pawn pawn, Path path) {
        if (!pawn.isPlaced()) {
            pawn.placeOnPath(path);
            return true; // Τοποθετήθηκε με επιτυχία
        }
        return false; // Το πιόνι είναι ήδη τοποθετημένο
    }

    public int getId() {
        return id;
    }

    public boolean allPawnsFinished() {
        for (Pawn pawn : pawns) {
            if (!pawn.isFinished()) {
                return false; // Αν ένα πιόνι δεν έχει τελειώσει, επιστρέφουμε false
            }
        }
        return true; // Αν όλα έχουν τελειώσει, επιστρέφουμε true
    }


    public int getScore() {
        return score;
    }
    public List<Card> getHandCards() {
        return handCards;
    }

    public void setHandCards(Card card) {
        handCards.add(card);
        System.out.println( card + " added to hand cards");
    }

    public boolean isHandFull(){
        return handCards.size() == 8;
    }


    // Μέσα στην κλάση Player
    public Pawn getPawnByType(String type) {
        for (Pawn pawn : pawns) {
            if (pawn.getType().equals(type) && !pawn.isPlaced()) {
                return pawn;
            }
        }
        return null; // Επιστρέφει null αν δεν βρεθεί πιόνι του συγκεκριμένου τύπου
    }

    public int getAvailablePawnCount(String type) {
        int count = 0;
        for (Pawn pawn : pawns) {
            if (pawn.getType().equals(type) && !pawn.isPlaced()) {
                count++;
            }
        }
        System.out.println(count +" Player:getAvailabeCount");
        return count;
    }

    public void removePawn(String pawnType) {
        this.pawns.remove(getPawnByType(pawnType));
    }

    public Pawn getSelectedPawn(int i ){
        return pawns.get(i);
    }

    public Pawn getAvailablePawnByType(String type) {
        for (Pawn pawn : pawns) {
            if (pawn.getType().equals(type) && !pawn.isPlaced()) {
                return pawn;
            }
        }
        System.out.println("Not found , class:players:getAvailablePawnByType");
        return null;
    }

    public boolean hasAlreadyPutPawn(int path){
        String pathName = "";
        switch (path){
            case 0: pathName = "Knossos"; break;
            case 1: pathName = "Malia"; break;
            case 2: pathName = "Phaistos"; break;
            case 3: pathName = "Zakros"; break;
        }
        for (Pawn pawn : pawns) {
            if (pawn.isPlaced() && pawn.getPath().getName().equals(pathName)) return true;
        }
        return false;
    }

    public List<Card> getPlayedCards() {
        return playedCards;
    }

    public void setPlayedCard(int pathIndex, Card card) {
        if (pathIndex >= 0 && pathIndex < playedCards.size()) {
            playedCards.set(pathIndex, card);
            System.out.println("player:setPlayedCard card was added: " + card.getName() + " to path index " + pathIndex);
        } else {
            System.out.println("Invalid path index: " + pathIndex);
        }
    }

    public Card getPlayedCardByPath(int pathIndex) {
        if(playedCards.isEmpty()) return null;
        return playedCards.get(pathIndex);
    }

    public void addToCollection(Finding finding) {
        collection.add(finding);
    }

    public void addFrescoPhoto(Finding finding) {
        System.out.println("Φωτογραφήθηκε η τοιχογραφία: " + finding.getName());
    }

    public List<Finding> getCollection() {
        return collection;
    }

    public void setScore(int score) {
        this.score = score;
    }

}

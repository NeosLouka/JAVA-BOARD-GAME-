package Model;

public abstract class Card {
    private final int number; // The number on the card (e.g., 1-10)
    private final String name; // The name of the card
    private String path;
    public Card(int number, String name) {
        this.number = number;
        this.name = name;
        this.path = "";
    }
    public String getPath(){
        return path;
    }
    public void setPath(String path){
        this.path = path;
    }


    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    /**
     * Abstract method to define how a pawn is moved when this card is played.
     *
     * @param pawn The player's pawn to be moved.
     */
    public abstract void movePawn(Pawn pawn );


    @Override
    public String toString() {
        return STR."\{name} (\{number})";
    }
}

package Model;

public class Theseus  extends Pawn{


    private boolean skipNextRound = false;
    /**
     * Constructor that sets the type of the pawn to Theseus
     */
    public Theseus() {
        super("Theseus" ,0);
    }

    public void skipNextRound() {
        this.skipNextRound = true;
    }

    public boolean shouldSkipTurn() {
        return skipNextRound;
    }

    public void resetSkipTurn() {
        this.skipNextRound = false;
    }

}

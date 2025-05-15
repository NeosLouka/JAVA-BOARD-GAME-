package Model;

public class Pawn {
    private int ownerId;
    private final String type; // Archaeologist ή Theseus
    private Path path; // Το μονοπάτι στο οποίο βρίσκεται το πιόνι
    private int position;
    private boolean checkPointReached;
    private boolean finished;
    private boolean placed; // Αν το πιόνι έχει τοποθετηθεί σε μονοπάτι
    private int theseusDestructionCount = 0;

    /**
     * Constructor that defines type , position and if has reached to the last square
     * @param type type of pawn
     */
    public Pawn(String type , int ownerId) {
        this.ownerId = ownerId;
        this.type = type;
        this.position = 0;
        this.finished = false;
        this.checkPointReached = false;
        this.placed = false;
    }
    public int getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public boolean isPlaced() {
        return placed;
    }

    public void placeOnPath(Path path) {
        this.path = path;
        this.placed = true;
    }

    public Path getPath() {
        return path;
    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String showType() {
        return type;
    }

    public void movePawn(int i) {
        this.position = position + i;

//        if(position > 9){
//            position = 9;
//            finished = true;
//            System.out.println(this.showType() + " reached to the last square!");
//        }else if(position == 9) {
//            finished = true;
//            System.out.println(this.showType() + " reached to the last square!");
//        } else if (position < 0) {
//            position = 0;
//        }
//        System.out.println(this.showType() + " moved " + i + "steps . New position = " + position );
//
//        if(position == 7){
//            checkPointReached = true;
//            System.out.println("Check Point Reached");
//        }
    }

    public boolean getCheckPointReached() {
        return checkPointReached;
    }

    public boolean isFinished() {
        return finished;
    }

    public String getType() {
        return type;
    }

    public void setPath(Path path) {
        this.path = path;
    }


    public void setPlaced() {
        System.out.println(this.showType() + " placed");
        this.placed = true;
    }


    public void incrementTheseusDestructionCount() {
        theseusDestructionCount++;
    }

    public int getTheseusDestructionCount() {
        return theseusDestructionCount;
    }

    public Square getCurrentSquare() {
        if (path != null && position >= 0 && position < path.getSquare().size()) {
            return path.getSquare().get(position);
        }
        return null; // Αν το position είναι εκτός ορίων ή το path είναι null
    }
}
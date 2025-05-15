package Model;

import java.util.*;

public class Path {

    private final String name; // Όνομα μονοπατιού
    private final List<Pawn> pawnsOnPath; // Πιόνια στο μονοπάτι
    private final List<Square> square; // Τα τετράγωνα του μονοπατιού
    private static final LinkedList<Finding> simpleFindingList = new LinkedList<>();
    private static boolean accessed = false; // Flag για να δημιουργούνται τα simple findings μόνο μία φορά

    public Path(String name) {
        this.name = name;
        this.pawnsOnPath = new ArrayList<>();
        this.square = new ArrayList<>();

        // Αν η λίστα από απλά ευρήματα είναι άδεια, την αρχικοποιούμε
        if (simpleFindingList.isEmpty() && !accessed) {
            initializeSimpleFindings();
            accessed = true;
        }

        // Δημιουργία ενός σπάνιου ευρήματος για το συγκεκριμένο path
        Finding rareFinding = createRareFinding(name);

        // Τοποθέτηση ευρημάτων σε ειδικές θέσεις (2, 4, 6, 8 , 9)
        Random random = new Random();
        int[] specialPositions = {1, 3, 5, 7 , 8}; // Θέσεις 2, 4, 6, 8 , 9(index)
        int rarePosition = specialPositions[random.nextInt(specialPositions.length)];

        // Δημιουργία τετραγώνων
        for (int i = 0; i < 9; i++) {
            int points = calculatePoints(i);

            Square newSquare;
            if (i == rarePosition) {
                // Αν είναι η θέση για το σπάνιο εύρημα
                newSquare = new Square(rareFinding, points);
            } else if (isSpecialPosition(i) && !simpleFindingList.isEmpty()) {
                // Αν είναι ειδική θέση και υπάρχουν απλά ευρήματα διαθέσιμα
                newSquare = new Square(simpleFindingList.removeFirst(), points);
            } else if (i == 8 && !simpleFindingList.isEmpty()) {
                // Αν είναι η τελευταία θέση και υπάρχει διαθέσιμο απλό εύρημα
                newSquare = new Square(simpleFindingList.removeFirst(), points);
            } else {
                // Κενό τετράγωνο
                newSquare = new Square(null, points);
            }

            square.add(newSquare);
        }

        logPathInitialization();
    }

    private void initializeSimpleFindings() {
        for (int i = 0; i < 10; i++) {
            Finding snakeGoddess = new SnakeGoddess("SnakeGoddess", 0, "C:\\hy252\\project_phaseB\\LostCities\\project_assets\\images\\findings\\snakes.jpg");
            simpleFindingList.add(snakeGoddess);
        }
        Finding fresco1 = new Frescoes("Fresco1" , 20 , "C:\\hy252\\project_phaseB\\LostCities\\project_assets\\images\\findings\\fresco1_20.jpg");
        simpleFindingList.add(fresco1);
        Finding fresco2 = new Frescoes("Fresco2" , 20 , "C:\\hy252\\project_phaseB\\LostCities\\project_assets\\images\\findings\\fresco2_20.jpg");
        simpleFindingList.add(fresco2);
        Finding fresco3 = new Frescoes("Fresco3" , 15 , "C:\\hy252\\project_phaseB\\LostCities\\project_assets\\images\\findings\\fresco3_15.jpg");
        simpleFindingList.add(fresco3);
        Finding fresco4 = new Frescoes("Fresco4" , 20 , "C:\\hy252\\project_phaseB\\LostCities\\project_assets\\images\\findings\\fresco4_20.jpg");
        simpleFindingList.add(fresco4);
        Finding fresco5 = new Frescoes("Fresco5" , 15 , "C:\\hy252\\project_phaseB\\LostCities\\project_assets\\images\\findings\\fresco5_15.jpg");
        simpleFindingList.add(fresco5);
        Finding fresco6 = new Frescoes("Fresco6" , 15 , "C:\\hy252\\project_phaseB\\LostCities\\project_assets\\images\\findings\\fresco6_15.jpg");
        simpleFindingList.add(fresco6);


        Collections.shuffle(simpleFindingList);
    }

    private Finding createRareFinding(String pathName) {
        switch (pathName.toLowerCase()) {
            case "knossos":
                return new RareFinding("Minotaur", 50, "C:\\hy252\\project_phaseB\\LostCities\\project_assets\\images\\findings\\ring.jpg");
            case "malia":
                return new RareFinding("Golden Bee", 45, "C:\\hy252\\project_phaseB\\LostCities\\project_assets\\images\\findings\\kosmima.jpg");
            case "phaistos":
                return new RareFinding("Disc of Phaistos", 40, "C:\\hy252\\project_phaseB\\LostCities\\project_assets\\images\\findings\\diskos.jpg");
            case "zakros":
                return new RareFinding("Rhyton", 35, "C:\\hy252\\project_phaseB\\LostCities\\project_assets\\images\\findings\\ruto.jpg");
            default:
                throw new IllegalArgumentException("Unknown path name: " + pathName);
        }
    }

    private boolean isSpecialPosition(int position) {
        return position == 1 || position == 3 || position == 5 || position == 7 || position == 8;
    }

    private int calculatePoints(int position) {
        switch (position) {
            case 0:
                return -20;
            case 1:
                return -15;
            case 2:
                return -10;
            case 3:
                return 5;
            case 4:
                return 10;
            case 5:
                return 15;
            case 6:
                return 30;
            case 7:
                return 35;
            case 8:
                return 50;
            default:
                return 0;
        }
    }

    private void logPathInitialization() {
        System.out.println("Path created: " + name);
        for (Square square : square) {
            if (square.getFinding() != null) {
                System.out.println("Square: " + square.getFinding().getName() + ")");
            } else {
                System.out.println("Square: Empty");
            }
        }
    }



    public String getName() {
        return name;
    }

    public List<Pawn> getPawnsOnPath() {
        return pawnsOnPath;
    }

    public void addPawn(Pawn pawn) {
        pawnsOnPath.add(pawn);
    }

    public List<Square> getSquare() {
        return square;
    }


    public int getRow() {
        String name = getName();
        switch (name){
            case "Knossos": return 0;
            case "Malia": return 1;
            case "Phaistos": return 2;
            case "Zakros": return 3;
        }
        System.out.println("BIG ERROR IN PATH:GEROW . Return -1");
        return -1;
    }

}

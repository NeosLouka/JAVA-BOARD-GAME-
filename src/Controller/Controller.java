package Controller;
import Model.*;
import View.View;
import Model.Pawn;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.List;


public class Controller {

    private final Board board;
    private final Deck deck;
    private final View view;

    private final Player player1;
    private final Player player2;
    private Player currentPlayer;

    private Clip currentClip; // Το τρέχον Clip που παίζει


    public Controller(Deck deck,Board board , View view){

        this.deck = deck;
        this.view = view;
        this.board = board; // Δημιουργία του Board

        this.player1 = new Player(1, true);
        this.player2 = new Player(2, false);

//        double randNumber = Math.random();
//        if(randNumber < 0.5){
//            this.currentPlayer = player1;
//            view.enablePlayer1UI(true);
//
//        }else if(randNumber < 0.75){
//            this.currentPlayer = player2;
//            view.enablePlayer2UI(true);
//        }

//        // Παίκτης τραβάει κάρτα
//        if (this.currentPlayer.isHandFull()) {
//            System.out.println("Deck is full , Cant draw more cards!");
//            view.disableDrawCardButton();
//        } else {
//            view.enableDrawCardButton();
//            handleDrawCard();
//
//        }
        this.currentPlayer = this.player1;
        for(int i =  0; i < 8 ; i++){
            handleDrawCard();
        }
        view.updatePlayerHand(currentPlayer.getHandCards() , currentPlayer.getId());
        this.currentPlayer = this.player2;
        for(int i =  0; i < 8 ; i++){
            handleDrawCard();
        }
        view.updatePlayerHand(currentPlayer.getHandCards() , currentPlayer.getId());



        double randNumber = Math.random();
        if(randNumber < 0.5){
            this.currentPlayer = player1;
            view.enablePlayer1UI(true);

        }else if(randNumber < 0.75){
            this.currentPlayer = player2;
            view.enablePlayer2UI(true);
        }


        initializeCardListeners(view.getPlayer1Cards());
        initializeCardListeners(view.getPlayer2Cards());
        initializeDrawCardListener(); // Προσθήκη του draw card listener
        initializeDiscardCardListener(); // Προσθήκη του discard listener

//        initializeCardListeners(view.getPlayer1Cards(), player1, view.getPlayerFrames());
//        initializeCardListeners(view.getPlayer2Cards(), player2, view.getPlayerFrames2());
        view.initializeClickableSquares(view.getBoardSquares());



    }


    private void updateUI() {
        boolean isPlayer1Turn = currentPlayer == player1;

        view.enablePlayer1UI(isPlayer1Turn);
        view.enablePlayer2UI(!isPlayer1Turn);
    }


    public void changeTurn() {
        updateScore(currentPlayer.getId());
        if (currentPlayer == player1) {
            currentPlayer = player2;
            player1.setTurn(false);
            player2.setTurn(true);
        } else {
            currentPlayer = player1;
            player1.setTurn(true);
            player2.setTurn(false);
        }
        view.enableBoardSquares();
        if(currentPlayer.getHandCards().size() <8){
            view.enableDrawCardButton();
        }else view.disableDrawCardButton();

        playMusicForPlayer(currentPlayer.getId()); // Παίζει μουσική ανάλογα με τη σειρά

        updateUI(); // Ενημέρωση UI για τη σειρά
    }


    // Handle card drawing
    private void handleDrawCard() {
        Card card = deck.drawCard();
        System.out.println(card.toString());
        if (card != null) {
            System.out.println("Selected card: " + card );
            this.currentPlayer.setHandCards(card);
            updateDeckCount();
        } else {
            view.showMessage("No more cards in the deck!");
        }

        // Ενημέρωση του View
        view.updatePlayerHand(currentPlayer.getHandCards(), currentPlayer.getId());

    }

    // Update the deck count in the view
    private void updateDeckCount() {
        view.updateDeckCount(deck.getRemainingCards());
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * A observer that check if the game has finished.
     * @return A boolean that represents if the game has finished or not.
     */

    public boolean checkIfGameFinished() {
        // Ελέγχει αν όλα τα πιόνια έχουν ολοκληρώσει τις διαδρομές τους
        boolean player1Finished = player1.allPawnsFinished();
        boolean player2Finished = player2.allPawnsFinished();

        // Ελέγχει αν η στοίβα καρτών έχει εξαντληθεί
        boolean deckEmpty = deck.getRemainingCards() == 0;

        return player1Finished || player2Finished || deckEmpty;
    }
    private void determineWinner() {
        int player1Score = player1.getScore();
        int player2Score = player2.getScore();

        if (player1Score > player2Score) {
            System.out.println("Ο Παίκτης 1 κερδίζει με σκορ: " + player1Score);
        } else if (player2Score > player1Score) {
            System.out.println("Ο Παίκτης 2 κερδίζει με σκορ: " + player2Score);
        } else {
            System.out.println("Ισοπαλία! Και οι δύο παίκτες έχουν σκορ: " + player1Score);
        }
    }


    public void runGameLoop() {
        System.out.println("Το παιχνίδι ξεκινά!");
        int start = 0;

        while (!checkIfGameFinished()) {
            System.out.println("Παίζει ο παίκτης: " + currentPlayer.getId());
            playMusicForPlayer(currentPlayer.getId()); // Παίζει μουσική ανάλογα με τη σειρά

            // Παίκτης τραβάει κάρτα
            if(this.currentPlayer.isHandFull()){
                System.out.println("Deck is full , Cant draw more cards!");
                view.disableDrawCardButton();
            }else {
                view.enableDrawCardButton();
                handleDrawCard();

            }
            if(start == 0){

                if (currentPlayer == player1) {
                    player2.setTurn(false);
                    player1.setTurn(true);
                } else {
                    currentPlayer = player2;
                    player2.setTurn(true);
                    player1.setTurn(false);
                }
                updateUI(); // Ενημέρωση UI για τη σειρά
                start--;
            }

            Scanner scanner = new Scanner(System.in);
            String a = scanner.nextLine();
            // Εναλλαγή σειράς
            changeTurn();
        }

        // Μετά τον βρόχο, το παιχνίδι έχει τελειώσει
        System.out.println("Το παιχνίδι έχει τελειώσει!");
        determineWinner(); // Κλήση για να υπολογιστεί ο νικητής
    }

    public boolean placePawnOnPath(Player player, String pawnType, int row, int col) {
        Path path = board.getPaths().get(row); // Παίρνουμε το μονοπάτι από το board
        if(player.hasAlreadyPutPawn(row)){
            view.showMessage(STR."O PLAYER \{player.getId()} εχει τοποθετησει ηδη πιονι στο path");
            return false;
        } else if (path.getPawnsOnPath().size() < 2 ) { // Έως 2 πιόνια ανά τετράγωνο

            Pawn pawn = player.getPawnByType(pawnType); // Παίρνουμε το πιόνι από τον παίκτη
            if (pawn != null) {
                pawn.setPath(path);
                pawn.setPlaced();
                path.addPawn(pawn); // Προσθέτουμε το πιόνι στο μονοπάτι

                view.updatePath( path ); // Ενημερώνουμε το UI
                updateAvailablePawns(player); // Ενημέρωση διαθέσιμων πιονιών
                view.disableBoardSquares();
                return true;
            } else {
                view.showMessage("Δεν έχετε διαθέσιμο πιόνι αυτού του τύπου.");
                return false;
            }
        } else {
            view.showMessage("Το κουτί αυτό είναι ήδη γεμάτο.");
            return false;
        }
    }


    public void updateAvailablePawns(Player player) {
        int archaeologists = player.getAvailablePawnCount("Archaeologist");
        int theseus = player.getAvailablePawnCount("Theseus");

        for(Path path : board.getPaths()){
            for (Pawn pawn : path.getPawnsOnPath()){
                System.out.println(pawn);
            }
        }
        view.updatePawnCount(player, archaeologists, theseus);
    }

    public boolean handlePawnPlacement(int row) {
        String[] options = {"Archaeologist", "Theseus"};
        int choice = JOptionPane.showOptionDialog(
                view,
                "Επιλέξτε τύπο πιονιού για τοποθέτηση στο μονοπάτι:",
                "Επιλογή Πιονιού",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        boolean result = false;
        if (choice == 0) {
            result = placePawnOnPath(getCurrentPlayer(), "Archaeologist", row, 0);
            if(!result) return false;

            return true;
        } else if (choice == 1) {
            placePawnOnPath(getCurrentPlayer(), "Theseus", row, 0);
            if(!result) return false;
            return true;
        }
        return false;
    }


    private void initializeCardListeners(JButton[] playerCards ) {


        for (int i = 0; i < playerCards.length; i++) {
            JButton cardButton = playerCards[i];
            int cardIndex = i; // Χρησιμοποιούμε το index του κουμπιού για τον listener

            cardButton.addActionListener(e -> {
                // Παίρνουμε την κάρτα από το χέρι του παίκτη
                if (currentPlayer.getHandCards().size() > cardIndex) {
                    Card card = currentPlayer.getHandCards().get(cardIndex);

                    // Εμφάνιση πληροφοριών στο τερματικό
                    System.out.println("Card Selected:");
                    System.out.println("  Name: " + card.getName());
                    System.out.println("  Number: " + card.getNumber());
                    System.out.println("  Path: " + card.getPath());

                    // Παίζουμε την κάρτα
                    playCard(cardIndex);
                } else {
                    System.out.println("No card available at index " + cardIndex);
                }
            });
        }
    }




    private int getPathIndexByName(String pathName) {
        switch (pathName) {
            case "Knossos":
                return 0;
            case "Malia":
                return 1;
            case "Phaistos":
                return 2;
            case "Zakros":
                return 3;
            default:
                return -1; // Άγνωστο μονοπάτι
        }
    }


    public boolean playCard(int cardIndex) {


        if (cardIndex < 0 || cardIndex >= currentPlayer.getHandCards().size()) {
            System.out.println("Invalid card index: " + cardIndex);
            return false;
        }

        // Επιλογη της κάρτας από το χέρι του παίκτη
        Card playedCard = currentPlayer.getHandCards().get(cardIndex);


// Εύρεση του μονοπατιού που σχετίζεται με την κάρτα
        String pathName = playedCard.getPath();
        Pawn selectedPawn = selectPawnById(pathName);

        if (selectedPawn != null) {
            // Μετακίνηση πιονιού

            if(canPlayTheCard(playedCard)){
                playedCard.movePawn(selectedPawn); // Υποθέτουμε ότι το movePawn ενημερώνει το πιόνι
                System.out.println("Moved pawn: " + selectedPawn.getType() + " by card: " + playedCard.getName());
            }else return false;

            // Ειδικές θέσεις: 2, 4, 6, 8, 9
            int possition = selectedPawn.getPosition();
            if (possition == 1 || possition == 3 || possition == 5 || possition == 7 || possition == 8) {
                Square square = board.getPathByName(pathName).getSquare().get(possition); //path.getSquare().get(possition);
                System.out.println("Prin apo thn handleExcavation pawntype:" + selectedPawn.getType());

                if (selectedPawn.getPath().getSquare().get(selectedPawn.getPosition()).getFinding() == null){
                    System.out.println("GIATI EINAI NULL ?????");
                }
                handleExcavation(selectedPawn); // Κλήση της μεθόδου ανασκαφής/καταστροφής
            }


            // Ενημέρωση του UI για την κίνηση πιονιού
            Path path = selectedPawn.getPath();//selects current players pawn in the path
            System.out.println(path.getName() +" , "+selectedPawn.getPosition());
            view.updatePath(path);

           // view.updateSquare(row, col, path.getPawnsOnPath() ,player.getId());
        } else {
            view.showMessage("No pawn found for the current player on path: " + pathName);
            return false;
        }

        // Καταγραφή της κάρτας ως τελευταία παιγμένη

        int pathIndex = getPathIndexByName(playedCard.getPath());
        currentPlayer.setPlayedCard(pathIndex, playedCard);

        // Αφαιρεση της κάρτας από το χέρι του παίκτη
        currentPlayer.getHandCards().remove(cardIndex);



        // Ενημέρωση του View
        view.updatePlayerHand(currentPlayer.getHandCards(), currentPlayer.getId());

        // Ενημέρωση των frames με τις κάρτες που έχουν παιχτεί
        updatePlayedCardsFrames();
        //Ενημέρωση των Findings dialogs
        updateFindingsDialogs(currentPlayer.getCollection(), currentPlayer.getId());

        // Debugging στο τερματικό
        System.out.println("Played Card: " + playedCard.getName() + " " + playedCard.getNumber());

        handleDrawCard();

        changeTurn();
        return true;
    }

    public Pawn selectPawnById(String pathName) {
        for (Pawn pawn : currentPlayer.getPawns()) {
            if (pawn.getPath() != null && pawn.getPath().getName().equals(pathName)) {
                if(pawn.getOwnerId() == currentPlayer.getId()){
                    System.out.println(pawn);
                    return pawn;
                }
                return null; // Επιστρέφει το πιόνι που ανήκει στο μονοπάτι
            }
        }

        return null; // Αν δεν βρεθεί πιόνι στο μονοπάτι, επιστρέφει null
    }




    public void updatePlayerFrames(Player player, JLabel[] playerFrames) {
        List<Card> playedCards = player.getPlayedCards(); // Παίρνουμε τις κάρτες που έχουν παιχτεί

        for (int i = 0; i < playerFrames.length; i++) {
            JLabel frame = playerFrames[i];
            frame.setPreferredSize(new Dimension(80, 120)); // Ορισμός διαστάσεων

            // Καθαρισμός του frame για αποφυγή επικαλύψεων
            frame.setIcon(null);
            frame.setText(""); // Επαναφορά κειμένου σε περίπτωση που υπάρχει

            Card card = i < playedCards.size() ? playedCards.get(i) : null; // Παίρνουμε την κάρτα αν υπάρχει
            if (card != null) {
                // Δημιουργία εικόνας για την κάρτα
                String imagePath = "C:\\hy252\\project_phaseB\\LostCities\\project_assets\\images\\cards\\"
                        + card.getName()
                        + card.getNumber()
                        + ".jpg";
                if(card.getNumber() == 0) imagePath = "C:\\hy252\\project_phaseB\\LostCities\\project_assets\\images\\cards\\"+card.getPath().toLowerCase()+"Min.jpg";
                if(card.getNumber() == -1) imagePath = "C:\\hy252\\project_phaseB\\LostCities\\project_assets\\images\\cards\\"+card.getPath().toLowerCase()+"Ari.jpg";

                ImageIcon cardIcon = new ImageIcon(imagePath);

                // Κλιμάκωση της εικόνας για να ταιριάζει στο μέγεθος
                Image scaledImage = cardIcon.getImage().getScaledInstance(80, 120, Image.SCALE_SMOOTH);
                frame.setIcon(new ImageIcon(scaledImage));
            } else {
                // Αν δεν υπάρχει κάρτα, εμφανίζεται ένα κενό frame
                frame.setIcon(null);
            }

            frame.revalidate(); // Ενημέρωση του layout
            frame.repaint(); // Επανασχεδίαση για οπτικές αλλαγές
        }
    }

    private void updatePlayedCardsFrames() {
        // Ενημέρωση των frames του Player 1
        updatePlayerFrames(player1, view.getPlayerFrames(player1.getId()));

        // Ενημέρωση των frames του Player 2
        updatePlayerFrames(player2, view.getPlayerFrames(player2.getId()));
    }


    private boolean canPlayTheCard(Card card) {
        if(card.getNumber() == -1 || card.getNumber() == 0) return true;
        if (currentPlayer.getPlayedCards() == null || currentPlayer.getPlayedCards().isEmpty()) {
            return true; // Αν η λίστα είναι null ή κενή, μπορεί να παιχτεί η κάρτα
        } else {
            int index = getPathIndexByName(card.getPath());
            Card lastPlayedCard = currentPlayer.getPlayedCards().get(index);

            // Έλεγχος αν το στοιχείο είναι null
            if (lastPlayedCard == null) {
                return true; // Αν δεν έχει παιχτεί καμία κάρτα για αυτό το μονοπάτι
            }

            // Σύγκριση με την τελευταία παιγμένη κάρτα
            return card.getNumber() >= lastPlayedCard.getNumber();
        }
    }


    private void initializeDrawCardListener() {
        JButton drawCardButton = view.getDrawCardButton(); // Παίρνουμε το κουμπί από το View

        drawCardButton.addActionListener(e -> {
            // Κλήση της handleDrawCard
            handleDrawCard();

            // Ενημέρωση του UI
            view.updatePlayerHand(currentPlayer.getHandCards(), currentPlayer.getId());

            changeTurn();
        });
    }


    private void discardCard(int cardIndex) {
        if (cardIndex < 0 || cardIndex >= currentPlayer.getHandCards().size()) {
            view.showMessage("Invalid card selection.");
            return;
        }

        // Αφαίρεση της κάρτας από το χέρι του παίκτη
        Card discardedCard = currentPlayer.getHandCards().remove(cardIndex);
        System.out.println("Discarded Card: " + discardedCard);

        // Ενημέρωση του View
        view.updatePlayerHand(currentPlayer.getHandCards(), currentPlayer.getId());
        view.enableDrawCardButton();
    }

    private void initializeDiscardCardListener() {
        JButton discardCardButton = view.getDiscardCardButton(); // Παίρνουμε το button από το View

        discardCardButton.addActionListener(e -> {
            // Ζήτησε από τον παίκτη να επιλέξει ποια κάρτα θέλει να απορρίψει
            String input = JOptionPane.showInputDialog(
                    null,
                    "Enter the index (1-" + currentPlayer.getHandCards().size() + ") of the card to discard:",
                    "Discard Card",
                    JOptionPane.PLAIN_MESSAGE
            );

            try {
                int cardIndex = Integer.parseInt(input) - 1; // Μετατροπή σε index
                discardCard(cardIndex);
            } catch (NumberFormatException | NullPointerException ex) {
                view.showMessage("Invalid input. Please enter a valid card index.");
            }
        });
    }


    public void handleExcavation(Pawn pawn) {
        Square square = pawn.getPath().getSquare().get(pawn.getPosition());

        if (square.getFinding() == null){
            System.out.println("GIATI EINAI NULL ?????");
        }

        if (square == null || square.getFinding() == null) {
            System.out.println("Το τετράγωνο δεν περιέχει εύρημα.");
            return;
        }

        Finding finding = square.getFinding();
        System.out.println("Βρέθηκε εύρημα: " + finding.getName());

        if (pawn.getType().equals("Archaeologist")) {
            int choice = JOptionPane.showConfirmDialog(null,
                    "Θέλεις να ανοίξεις το τετράγωνο;\nΕύρημα: " + finding.getName(),
                    "Ανασκαφή", JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {
                if (finding instanceof RareFinding || finding instanceof SnakeGoddess || finding instanceof Frescoes) {
                    currentPlayer.addToCollection(finding);
                    square.setFinding(null);
                    System.out.println("Το εύρημα προστέθηκε στη συλλογή.");
                } else if (finding instanceof Frescoes) {
//                    currentPlayer.addFrescoPhoto(finding);
//                    System.out.println("Η τοιχογραφία φωτογραφήθηκε.");
                }
            }
        } else if (pawn.getType().equals("Theseus")) {
            int choice = JOptionPane.showConfirmDialog(null,
                    "Θέλεις να καταστρέψεις το τετράγωνο;\nΕύρημα: " + finding.getName(),
                    "Καταστροφή", JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {
                if (pawn.getTheseusDestructionCount() < 3) {
                    pawn.incrementTheseusDestructionCount();
                    square.setFinding(null);
                    System.out.println("Το εύρημα καταστράφηκε.");
                } else {
                    System.out.println("Δεν μπορείς να καταστρέψεις άλλο τετράγωνο.");
                }
            }
        }
    }



    private JDialog player1CollectionDialog = null;
    private JDialog player2CollectionDialog = null;

    public void updateFindingsDialogs(List<Finding> collection, int playerId) {
        // Υπολογισμός του αριθμού των αγαλμάτων
        long statueCount = collection.stream()
                .filter(finding -> finding instanceof SnakeGoddess)
                .count();

        // Ενημέρωση της ετικέτας αγαλμάτων για τον αντίστοιχο παίκτη
        if (playerId == 1) {
            view.getPlayer1StatuesLabel().setText("Statues: " + statueCount);
        } else if (playerId == 2) {
            view.getPlayer2StatuesLabel().setText("Statues: " + statueCount);
        }

        // Επιλέγουμε τον διάλογο για τον παίκτη
        JDialog collectionDialog = null;

        // Δημιουργία του διαλόγου αν δεν υπάρχει ήδη
        if (collectionDialog == null) {
            collectionDialog = new JDialog(view, "Συλλογή Παίκτη " + playerId, true);
            collectionDialog.setSize(400, 300);
            collectionDialog.setLayout(new GridLayout(0, 3)); // Προσαρμόζεται δυναμικά
            if (playerId == 1) {
                player1CollectionDialog = collectionDialog;
            } else {
               player2CollectionDialog = collectionDialog;
            }
        } else {
            collectionDialog = (playerId == 1) ? player1CollectionDialog : player2CollectionDialog;
        }

        // Ενημέρωση του περιεχομένου του διαλόγου
        collectionDialog.getContentPane().removeAll();

        JDialog finalCollectionDialog = collectionDialog;
        collection.stream()
                .filter(finding -> !(finding instanceof SnakeGoddess)) // Εξαίρεση Snake Goddess
                .forEach(finding -> {
                    ImageIcon imageIcon = new ImageIcon(finding.getImagePath());
                    System.out.println(finding.getImagePath());
                    Image scaledImage = imageIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                    JLabel findingLabel = new JLabel(
                            "<html>" + finding.getName() + "<br>Points: " + finding.getPoints() + "</html>",
                            new ImageIcon(scaledImage),
                            SwingConstants.CENTER
                    );
                    findingLabel.setHorizontalTextPosition(SwingConstants.CENTER);
                    findingLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
                    findingLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    finalCollectionDialog.add(findingLabel);
                });

        collectionDialog.revalidate();
        collectionDialog.repaint();
    }

    public void showPlayerCollection(int playerId) {
        if (playerId == 1 && player1CollectionDialog != null) {
            player1CollectionDialog.setVisible(true);
        } else if (playerId == 2 && player2CollectionDialog != null) {
            player2CollectionDialog.setVisible(true);
        }
    }

    public void updateScore(int playerId) {
        Player player = currentPlayer;

        // Υπολογισμός πόντων από τα τετράγωνα όπου βρίσκονται τα πιόνια
        int squarePoints = player.getPawns().stream()
                .mapToInt(pawn -> {
                    Square currentSquare = pawn.getCurrentSquare();
                    return (currentSquare != null) ? currentSquare.getPoints() : 0;
                })
                .sum();

        // Υπολογισμός πόντων από τα εύρηματα που έχει συλλέξει
        int findingsPoints = player.getCollection().stream()
                .mapToInt(Finding::getPoints)
                .sum();

        // Υπολογισμός συνολικού σκορ
        int totalScore = squarePoints + findingsPoints;

        // Ενημέρωση της ετικέτας σκορ για τον παίκτη
        currentPlayer.setScore(totalScore);
        if (playerId == 1) {
            view.getPlayer1ScoreLabel().setText("Score: " + totalScore);
        } else if (playerId == 2) {
            view.getPlayer2ScoreLabel().setText("Score: " + totalScore);
        }

        System.out.println("Player " + playerId + " score updated: " + totalScore);
    }


    private void playMusicForPlayer(int playerId) {
        String musicFile = (playerId == 1)
                ? "C:\\hy252\\project_phaseB\\LostCities\\project_assets\\music\\Player1.wav"
                : "C:\\hy252\\project_phaseB\\LostCities\\project_assets\\music\\Player2.wav";

        try {
            // Αν υπάρχει ήδη ενεργός ήχος, σταμάτησέ τον
            if (currentClip != null && currentClip.isRunning()) {
                currentClip.stop();
                currentClip.close();
            }

            // Φόρτωση του νέου αρχείου ήχου
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(musicFile));
            currentClip = AudioSystem.getClip();
            currentClip.open(audioStream);
            currentClip.start(); // Έναρξη του νέου ήχου
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error playing music for player: " + playerId);
        }
    }
}

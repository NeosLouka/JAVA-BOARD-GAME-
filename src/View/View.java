package View;
import java.awt.Color;

import Model.*;
import Controller.*;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class View extends JFrame {
    private Controller controller; // Αναφορά στον Controller

    private JPanel player1Panel; // Panel for Player 1
    private JPanel player2Panel; // Panel for Player 2
    private BackgroundPanel boardPanel; // Το panel του ταμπλό με φόντο

    //First part of the window
    private JMenuBar menuBar;
    private JButton[] player1Cards;
    private JLabel[] playerFrames; // Frames for Knossos, Malia, Phaistos, Zakros
    private JButton frescoesButton; // Button to show collected frescoes
    private JLabel player1ScoreLabel;
    private JLabel player1StatuesLabel;
    private JLabel player1AvailablePawns;
    private JLabel player1PawnsLabel; // New label for pawns
    private JDialog frescoesDialog; // Dialog to show frescoes
    private JButton[] player1PlayedCards;

    //Midle part of the window !!

    private JLabel[][] boardSquares; // Labels for each cell of the board
    private JLabel[] pointLabels; // Labels for points above each column
    private JLabel availableCardsLabel; // Label for Available Cards
    private JLabel checkPointsLabel; // Label for Check Points
    private JLabel turnLabel; // Label for Turn
    private JButton drawCardButton; // Button for drawing a card


    private JButton[] player2Cards; // Player 2's cards
    private JLabel[] player2Frames; // Frames for Knossos, Malia, Phaistos, Zakros
    private JLabel player2ScoreLabel;
    private JLabel player2StatuesLabel;
    private JLabel player2PawnsLabel;
    private JButton frescoesButton2; // Button to show Player 2's frescoes
    private JDialog frescoesDialog2; // Dialog to show Player 2's frescoes

    private JButton rareFindingsButton; // Νέο κουμπί για τα σπάνια ευρήματα
    private JDialog rareFindingsDialog; // Παράθυρο για τα σπάνια ευρήματα

    private JButton rareFindingsButton2; // Νέο κουμπί για τα σπάνια ευρήματα 2
    private JDialog rareFindingsDialog2; // Παράθυρο για τα σπάνια ευρήματα 2


    private JButton discardCardButton;

    JButton viewCollectionButton1 = new JButton("Προβολή Συλλογής");

    JButton viewCollectionButton2 = new JButton("Προβολή Συλλογής");
    // Δισδιάστατος πίνακας για τις εικόνες όλων των μονοπατιών
    String[][] pathImages = {
            { // Knossos
                    "C:/hy252/project_phaseB/LostCities/project_assets/images/paths/knossos.jpg",
                    "C:\\hy252\\project_phaseB\\LostCities\\project_assets\\images\\paths\\knossos2.jpg",
                    "C:/hy252/project_phaseB/LostCities/project_assets/images/paths/knossos.jpg",
                    "C:\\hy252\\project_phaseB\\LostCities\\project_assets\\images\\paths\\knossos2.jpg",
                    "C:/hy252/project_phaseB/LostCities/project_assets/images/paths/knossos.jpg",
                    "C:\\hy252\\project_phaseB\\LostCities\\project_assets\\images\\paths\\knossos2.jpg",
                    "C:/hy252/project_phaseB/LostCities/project_assets/images/paths/knossos.jpg",
                    "C:\\hy252\\project_phaseB\\LostCities\\project_assets\\images\\paths\\knossos2.jpg",
                    "C:/hy252/project_phaseB/LostCities/project_assets/images/paths/knossosPalace.jpg"
            },
            { // Malia
                    "C:/hy252/project_phaseB/LostCities/project_assets/images/paths/malia.jpg",
                    "C:/hy252/project_phaseB/LostCities/project_assets/images/paths/malia2.jpg",
                    "C:/hy252/project_phaseB/LostCities/project_assets/images/paths/malia.jpg",
                    "C:/hy252/project_phaseB/LostCities/project_assets/images/paths/malia2.jpg",
                    "C:/hy252/project_phaseB/LostCities/project_assets/images/paths/malia.jpg",
                    "C:/hy252/project_phaseB/LostCities/project_assets/images/paths/malia2.jpg",
                    "C:/hy252/project_phaseB/LostCities/project_assets/images/paths/malia.jpg",
                    "C:/hy252/project_phaseB/LostCities/project_assets/images/paths/malia2.jpg",
                    "C:/hy252/project_phaseB/LostCities/project_assets/images/paths/maliaPalace.jpg"
            },
            { // Phaistos
                    "C:/hy252/project_phaseB/LostCities/project_assets/images/paths/phaistos.jpg",
                    "C:/hy252/project_phaseB/LostCities/project_assets/images/paths/phaistos2.jpg",
                    "C:/hy252/project_phaseB/LostCities/project_assets/images/paths/phaistos.jpg",
                    "C:/hy252/project_phaseB/LostCities/project_assets/images/paths/phaistos2.jpg",
                    "C:/hy252/project_phaseB/LostCities/project_assets/images/paths/phaistos.jpg",
                    "C:/hy252/project_phaseB/LostCities/project_assets/images/paths/phaistos2.jpg",
                    "C:/hy252/project_phaseB/LostCities/project_assets/images/paths/phaistos.jpg",
                    "C:/hy252/project_phaseB/LostCities/project_assets/images/paths/phaistos2.jpg",
                    "C:/hy252/project_phaseB/LostCities/project_assets/images/paths/phaistosPalace.jpg"
            },
            { // Zakros
                    "C:/hy252/project_phaseB/LostCities/project_assets/images/paths/zakros.jpg",
                    "C:/hy252/project_phaseB/LostCities/project_assets/images/paths/zakros2.jpg",
                    "C:/hy252/project_phaseB/LostCities/project_assets/images/paths/zakros.jpg",
                    "C:/hy252/project_phaseB/LostCities/project_assets/images/paths/zakros2.jpg",
                    "C:/hy252/project_phaseB/LostCities/project_assets/images/paths/zakros.jpg",
                    "C:/hy252/project_phaseB/LostCities/project_assets/images/paths/zakros2.jpg",
                    "C:/hy252/project_phaseB/LostCities/project_assets/images/paths/zakros.jpg",
                    "C:/hy252/project_phaseB/LostCities/project_assets/images/paths/zakros2.jpg",
                    "C:/hy252/project_phaseB/LostCities/project_assets/images/paths/zakrosPalace.jpg"
            }
    };

    int boxWidth = 60;  // Πλάτος
    int boxHeight = 60; // Ύψος

    public void setController(Controller controller) {
        this.controller = controller;
    }



    /**
     *
     */
    public View() {
        super(); // Call JFrame's constructor
        setTitle("Αναζητώντας τα Χαμένα Μινωικά Ανάκτορα");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLayout(new BorderLayout());

        initializeComponents();
        setLayoutAndAssemble();

        setVisible(true); // Display the window
    }

    /**
     * Initializes all visual and interactive components of the View class.
     * This method sets up the basic UI elements such as buttons, labels,
     * panels, and other components that are part of the game's interface.
     * It is intended to be called within the constructor of the View class
     * to ensure all components are properly instantiated before use.
     */
    private void initializeComponents() {
        // Menu Bar
        menuBar = new JMenuBar();
        menuBar.add(new JMenuItem("New Game"));
        menuBar.add(new JMenuItem("Save Game"));
        menuBar.add(new JMenuItem("Continue Saved Game"));
        menuBar.add(new JMenuItem("Exit Game"));

        // Player 1Cards
        player1Panel = new JPanel();
        player1Cards = new JButton[8];
        for (int i = 0; i < 8; i++) {
            player1Cards[i] = new JButton("Card " + (i + 1));
            player1Cards[i].setPreferredSize(new Dimension(80, 120)); // Card size
            player1Panel.add(player1Cards[i]);
        }

        // Player Frames (Knossos, Malia, Phaistos, Zakros)
        playerFrames = new JLabel[4];
        String[] frameNames = {"Κνωσός", "Μάλια", "Φαιστός", "Ζάκρος"};
        for (int i = 0; i < 4; i++) {
            playerFrames[i] = new JLabel(frameNames[i], SwingConstants.CENTER);
            playerFrames[i].setOpaque(true);
            playerFrames[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            playerFrames[i].setPreferredSize(new Dimension(100, 100));
        }

        // Score and Statues Labels
        player1ScoreLabel = new JLabel("Score: 0");
        player1StatuesLabel = new JLabel("Statues: 0");

        // Player Pawns Label
        player1PawnsLabel = new JLabel(STR." Διαθέσιμα Πιόνια : 3 Αρχαιολόγοι , 1 Θησέας");



        // Board Components
        boardPanel = new BackgroundPanel("C:\\hy252\\project_phaseB\\LostCities\\project_assets\\images\\background.jpg");
        boardSquares = new JLabel[4][9]; // 4 rows, 9 columns

        boardPanel.setLayout(null); // Χειροκίνητη τοποθέτηση στοιχείων
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 9; col++) {
                JLabel square = new JLabel();
                square.setBounds(col * 80, row * 80, 80, 80); // Ρυθμίστε τις θέσεις
                square.setOpaque(false); // Διαφανές για να φαίνεται το background
                square.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                boardPanel.add(square);
                boardSquares[row][col] = square;
            }
        }

        // Επαναληπτικός βρόχος για τα τετράγωνα του board
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 9; col++) {
                boardSquares[row][col] = new JLabel("", SwingConstants.CENTER);

                // Φόρτωση εικόνας για την τρέχουσα θέση από τον πίνακα pathImages
                String imagePath = pathImages[row][col];
                ImageIcon icon = new ImageIcon(imagePath);

                // Κλιμάκωση εικόνας για το τετράγωνο
                Image scaledImage = icon.getImage().getScaledInstance(boxWidth, boxHeight, Image.SCALE_SMOOTH);
                boardSquares[row][col].setIcon(new ImageIcon(scaledImage));

                // Προσθήκη border και μέγεθος για κάθε τετράγωνο
                boardSquares[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                boardSquares[row][col].setPreferredSize(new Dimension(boxWidth, boxHeight)); // Μεγαλύτερο κουτί
            }
        }


        // Points Labels (Above each column)
        pointLabels = new JLabel[9];
        int[] points = {-20, -15, -10, 5, 10, 15, 30, 35, 50};
        for (int i = 0; i < 9; i++) {
            pointLabels[i] = new JLabel(STR."\{points[i]} points", SwingConstants.CENTER);
            if (i == 5) {
                pointLabels[i].setText(STR."\{points[i]} CHECKP");
            }
            pointLabels[i].setPreferredSize(new Dimension(80, 20));
        }

        // Left Panel Components

        // Δημιουργία εικονιδίου για το κουμπί
        ImageIcon cardIcon = new ImageIcon("C:/hy252/project_phaseB/LostCities/project_assets/images/cards/backCard.jpg");

        // Κλιμάκωση της εικόνας ώστε να ταιριάζει στο κουμπί
        Image scaledImage = cardIcon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
        cardIcon = new ImageIcon(scaledImage);

        // Δημιουργία του κουμπιού με την εικόνα
        drawCardButton = new JButton(cardIcon);

        // Προσθήκη κειμένου στο κουμπί
        drawCardButton.setText("Draw Card");
        drawCardButton.setHorizontalTextPosition(SwingConstants.CENTER); // Τοποθέτηση κειμένου στο κέντρο
        drawCardButton.setVerticalTextPosition(SwingConstants.BOTTOM);   // Τοποθέτηση κειμένου κάτω από την εικόνα

        // Προσαρμογή Στυλ (προαιρετικά)
        drawCardButton.setBorderPainted(true);
        drawCardButton.setFocusPainted(false);

        // Άλλα Συστατικά του Left Panel
        availableCardsLabel = new JLabel("Available Cards: 84");
        checkPointsLabel = new JLabel("Check Points: 0");
        turnLabel = new JLabel("Turn: Player 1");

        // Δημιουργία του κουμπιού discard
        discardCardButton = new JButton("Discard Card");
        discardCardButton.setPreferredSize(new Dimension(150, 40)); // Προσαρμογή μεγέθους

        // Πρόσθεσε το κουμπί στο κατάλληλο panel (π.χ., δίπλα στο drawCardButton)
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(discardCardButton);
        buttonPanel.add(drawCardButton); // Ή οποιοδήποτε άλλο button
        add(buttonPanel, BorderLayout.WEST);


        // Player 2 Cards
        player2Panel = new JPanel();
        player2Cards = new JButton[8];
        for (int i = 0; i < 8; i++) {
            player2Cards[i] = new JButton("Card " + (i + 1));
            player2Cards[i].setPreferredSize(new Dimension(80, 120)); // Card size
            player2Panel.add(player2Cards[i]);
        }


// Player 2 Frames (Knossos, Malia, Phaistos, Zakros)
        player2Frames = new JLabel[4];
        String[] frameNames2 = {"Κνωσός", "Μάλια", "Φαιστός", "Ζάκρος"};
        for (int i = 0; i < 4; i++) {
            player2Frames[i] = new JLabel(frameNames2[i], SwingConstants.CENTER);
            player2Frames[i].setOpaque(true);
            player2Frames[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            player2Frames[i].setPreferredSize(new Dimension(100, 100));
        }

// Score and Statues Labels for Player 2
        player2ScoreLabel = new JLabel("Score: 0");
        player2StatuesLabel = new JLabel("Statues: 0");
        player2PawnsLabel = new JLabel(STR." Διαθέσιμα Πιόνια : 3 Αρχαιολόγοι , 1 Θησέας");


// Ενιαίο κουμπί για εμφάνιση συλλογών
        viewCollectionButton1.addActionListener(e -> controller.showPlayerCollection(1));


        viewCollectionButton2.addActionListener(e -> controller.showPlayerCollection(2));


    }

    /**
     * Arranges and assembles the layout for the visual components of the View class.
     * This method is responsible for organizing all initialized UI elements
     * and adding them to their respective containers to form the complete interface.
     * It is called within the constructor after all components have been initialized
     * to ensure a coherent and functional layout is presented to the user.
     */
    private void setLayoutAndAssemble() {
        // Set Menu Bar
        setJMenuBar(menuBar);

        // Top Section (Player Cards and Frames)
        JPanel topPanel = new JPanel(new BorderLayout());

        // Player Cards
        JPanel cardsPanel = new JPanel(new FlowLayout());
        for (JButton card : player1Cards) {
            cardsPanel.add(card);
        }

        // Player Frames
        JPanel framesPanel = new JPanel(new GridLayout(1, 4));
        for (JLabel frame : playerFrames) {
            framesPanel.add(frame);
        }

        // Combine Cards and Frames
        JPanel playerInfoPanel = new JPanel(new BorderLayout());
        playerInfoPanel.add(cardsPanel, BorderLayout.CENTER);
        playerInfoPanel.add(framesPanel, BorderLayout.EAST);
        playerInfoPanel.add(player1PawnsLabel, BorderLayout.SOUTH);

        // Score and Frescoes Button
        JPanel scorePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        scorePanel.add(player1ScoreLabel);
        scorePanel.add(viewCollectionButton1); // Προσθήκη νέου κουμπιού
        scorePanel.add(player1StatuesLabel);

        topPanel.add(playerInfoPanel, BorderLayout.CENTER);
        topPanel.add(scorePanel, BorderLayout.SOUTH);



        topPanel.add(playerInfoPanel, BorderLayout.CENTER);
        topPanel.add(scorePanel, BorderLayout.SOUTH);

        topPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
        // Add top panel to the main layout
        add(topPanel, BorderLayout.NORTH);

        // LEFT PANEL: Card Stack and Game Stats
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(150, 0));

        // Card Stack Button
        drawCardButton = new JButton(new ImageIcon("C:\\hy252\\project_phaseB\\LostCities\\project_assets\\images\\cards\\backCard.jpg")); // Replace with the path to your vase image
        drawCardButton.setPreferredSize(new Dimension(80, 180));
        leftPanel.add(drawCardButton, BorderLayout.SOUTH);
        leftPanel.add(discardCardButton , BorderLayout.NORTH); // Add the discard card button
        // Status Panel for Game Stats
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
        statusPanel.add(availableCardsLabel);
        statusPanel.add(Box.createVerticalStrut(10));
        statusPanel.add(checkPointsLabel);
        statusPanel.add(Box.createVerticalStrut(10));
        statusPanel.add(turnLabel);
        leftPanel.add(statusPanel, BorderLayout.CENTER);

        add(leftPanel, BorderLayout.WEST);



        // CENTER PANEL: Game Board with Background
        BackgroundPanel boardBackground = new BackgroundPanel("C:\\hy252\\project_phaseB\\LostCities\\project_assets\\images\\background.jpg");
        boardBackground.setLayout(new GridBagLayout()); // Use GridBagLayout for alignment

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add gaps between cells (top, left, bottom, right)
        gbc.fill = GridBagConstraints.NONE; // Prevent cells from stretching

        // Add Point Labels (Above the Columns)
        for (int col = 0; col < 9; col++) {
            gbc.gridx = col; // Column position
            gbc.gridy = 0; // Row position for labels
            pointLabels[col].setHorizontalAlignment(SwingConstants.CENTER);
            pointLabels[col].setFont(new Font("Arial", Font.BOLD, 14)); // Make the score labels bold
            boardBackground.add(pointLabels[col], gbc);
        }

        // Add Board Grid (Below the Labels)
        for (int row = 1; row <= 4; row++) { // Start at row 1 to leave space for the labels
            for (int col = 0; col < 9; col++) {
                gbc.gridx = col; // Column position
                gbc.gridy = row; // Row position for cells

                // Reduce the size of each cell
                boardSquares[row - 1][col].setPreferredSize(new Dimension(boxWidth, boxHeight)); // Smaller cells
                boardSquares[row - 1][col].setOpaque(true); // Set to opaque to allow color if needed
                boardSquares[row - 1][col].setBackground(new Color(255, 255, 255, 150)); // Semi-transparent white background

                boardBackground.add(boardSquares[row - 1][col], gbc); // Add cell to the layout
            }
        }

        add(boardBackground, BorderLayout.CENTER); // Add the board background to the center panel



        // Bottom Section (Player 2 Info)
        JPanel bottomPanel = new JPanel(new BorderLayout());

        // Player 2 Cards
        JPanel cardsPanel2 = new JPanel(new FlowLayout());
        for (JButton card : player2Cards) {
            cardsPanel2.add(card);
        }

        // Player 2 Frames
        JPanel framesPanel2 = new JPanel(new GridLayout(1, 4));
        for (JLabel frame : player2Frames) {
            framesPanel2.add(frame);
        }

        // Combine Cards and Frames for Player 2
        JPanel playerInfoPanel2 = new JPanel(new BorderLayout());
        playerInfoPanel2.add(cardsPanel2, BorderLayout.CENTER);
        playerInfoPanel2.add(framesPanel2, BorderLayout.EAST);
        playerInfoPanel2.add(player2PawnsLabel, BorderLayout.SOUTH);

        // Score and Frescoes Button for Player 2
        JPanel scorePanel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        scorePanel2.add(player2ScoreLabel);
        scorePanel2.add(viewCollectionButton2); // Προσθήκη νέου κουμπιού
        scorePanel2.add(player2StatuesLabel);

        bottomPanel.add(playerInfoPanel2, BorderLayout.CENTER);
        bottomPanel.add(scorePanel2, BorderLayout.SOUTH);
        bottomPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));

        // Add Bottom Panel to Main Layout
        add(bottomPanel, BorderLayout.SOUTH);

        // Ensure UI is updated
        revalidate();
        repaint();

    }






    /**
     * Displays the frescoes dialog window.
     */
    private void showFrescoesDialog() {
        frescoesDialog.setVisible(true); // Show the frescoes dialog
    }
    private void showFrescoesDialog2() {
        frescoesDialog2.setVisible(true); // Show Player 2's frescoes dialog
    }


    public JButton getDrawCardButton() {
        return drawCardButton;
    }

    public void updateDeckCount(int count) {
        availableCardsLabel.setText("Available Cards: " + count);
    }

//    // Display the last drawn card
//    public void displayDrawnCard(String card) {
//        if (drawnCardLabel == null) {
//            drawnCardLabel = new JLabel();
//            add(drawnCardLabel, BorderLayout.SOUTH); // Add it to the layout
//        }
//        drawnCardLabel.setText("Last Drawn Card: " + card);
//    }

    // Show a message to the user
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private void showRareFindingsDialog(int a ) {
        if(a == 0) {
            rareFindingsDialog.getContentPane().removeAll(); // Καθαρισμός παλιών δεδομένων
            rareFindingsDialog.setVisible(true); // Εμφάνιση διαλόγου
        }else {
            rareFindingsDialog2.getContentPane().removeAll(); // Καθαρισμός παλιών δεδομένων
            rareFindingsDialog2.setVisible(true); // Εμφάνιση διαλόγου
        }
    }

    public void enablePlayer1UI(boolean enable) {
        player1Panel.setEnabled(enable);
        for (JButton button : player1Cards) {
            button.setEnabled(enable);
        }
    }

    public void enablePlayer2UI(boolean enable) {
        player2Panel.setEnabled(enable);
        for (JButton button : player2Cards) {
            button.setEnabled(enable);
        }
    }

    // Method to enable the draw card button
    public void enableDrawCardButton() {
        drawCardButton.setEnabled(true); // Enable the button
    }

    // Method to disable the draw card button
    public void disableDrawCardButton() {
        drawCardButton.setEnabled(false); // Disable the button
    }


    public void updatePlayerHand(List<Card> handCards, int playerId) {
        // Επιλογή κουμπιών ανάλογα με το playerId
        JButton[] playerCards = (playerId == 1) ? player1Cards : player2Cards;

        // Βεβαιώσου ότι δεν ξεπερνάς τον αριθμό κουμπιών
        for (int i = 0; i < playerCards.length; i++) {
            if (i < handCards.size()) {
                Card card = handCards.get(i); // Παίρνουμε την κάρτα
                ImageIcon imageIcon;

                // Επιλογή εικόνας ανάλογα με την κάρτα
                if (card.getNumber() == 0) {
                    imageIcon = new ImageIcon("C:\\hy252\\project_phaseB\\LostCities\\project_assets\\images\\cards\\"+card.getPath().toLowerCase()+"Min.jpg");
                } else if (card.getNumber() == -1) {
                    imageIcon = new ImageIcon("C:\\hy252\\project_phaseB\\LostCities\\project_assets\\images\\cards\\"+card.getPath().toLowerCase()+"Ari.jpg");
                } else {
                    imageIcon = new ImageIcon("C:\\hy252\\project_phaseB\\LostCities\\project_assets\\images\\cards\\" + card.getName() + card.getNumber() + ".jpg");
                }

                // Κλιμάκωση εικόνας
                Image scaledImage = imageIcon.getImage().getScaledInstance(80, 120, Image.SCALE_SMOOTH);
                playerCards[i].setIcon(new ImageIcon(scaledImage));
                playerCards[i].setText(""); // Αφαίρεση κειμένου
            } else {
                playerCards[i].setIcon(null);
                playerCards[i].setText("Empty"); // Επαναφορά κενού κουμπιού
            }
        }

    }


    public void initializeClickableSquares(JLabel[][] boardSquares) {
        for (int row = 0; row < boardSquares.length; row++) {
            JLabel square = boardSquares[row][0]; // Πρώτο κουτί κάθε μονοπατιού
            square.setBorder(BorderFactory.createLineBorder(Color.GREEN));

            int finalRow = row;
            square.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Check if the square is enabled
                    if (!square.isEnabled()) {
                        System.out.println("Square is disabled. Ignoring click.");
                        return; // Do nothing if the square is disabled
                    }

                    // If enabled, allow pawn placement
                    boolean result = controller.handlePawnPlacement(finalRow);
                    if(result) disableBoardSquares(); // Disable squares after placement
                }
            });
        }
    }


    public void updatePath(Path path) {
        List<Pawn> pawnsOnPath = path.getPawnsOnPath(); // Όλα τα πιονιά στο μονοπάτι
        int pathRow = path.getRow(); // Γραμμή του μονοπατιού στο board

        // Καθαρισμός όλων των τετραγώνων του μονοπατιού
        for (int col = 0; col < boardSquares[pathRow].length; col++) {
            JLabel square = boardSquares[pathRow][col];

            // Επαναφορά background
            String imagePath = pathImages[pathRow][col];
            ImageIcon backgroundIcon = new ImageIcon(imagePath);
            Image scaledBackground = backgroundIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            square.setIcon(new ImageIcon(scaledBackground));

            // Καθαρισμός παλιών στοιχείων
            square.removeAll();
        }

        // Ομαδοποίηση πιονιών ανά θέση
        Map<Integer, List<Pawn>> pawnsByPosition = new HashMap<>();
        for (Pawn pawn : pawnsOnPath) {
            int position = pawn.getPosition();
            pawnsByPosition.putIfAbsent(position, new ArrayList<>());
            pawnsByPosition.get(position).add(pawn);
        }

        // Ενημέρωση τετραγώνων με βάση τις θέσεις των πιονιών
        for (Map.Entry<Integer, List<Pawn>> entry : pawnsByPosition.entrySet()) {
            int col = entry.getKey();
            List<Pawn> pawnsAtPosition = entry.getValue();
            JLabel square = boardSquares[pathRow][col];

            // Δημιουργία overlay panel για πολλαπλά πιονιά
            JPanel overlayPanel = new JPanel();
            overlayPanel.setOpaque(false); // Διαφανές panel
            overlayPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

            for (Pawn pawn : pawnsAtPosition) {
                String pawnType = pawn.getType();
                ImageIcon pawnIcon;

                if (pawnType.equals("Archaeologist")) {
                    pawnIcon = new ImageIcon("C:\\hy252\\project_phaseB\\LostCities\\project_assets\\images\\pionia\\arch.jpg");
                } else if (pawnType.equals("Theseus")) {
                    pawnIcon = new ImageIcon("C:\\hy252\\project_phaseB\\LostCities\\project_assets\\images\\pionia\\theseus.jpg");
                } else {
                    continue;
                }

                // Κλιμάκωση εικόνας πιονιού
                Image scaledPawn = pawnIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                JLabel pawnLabel = new JLabel(new ImageIcon(scaledPawn));

                // Προσθήκη περιγράμματος ανάλογα με τον ιδιοκτήτη του πιονιού
                if (pawn.getOwnerId() == 1) {
                    pawnLabel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2)); // Green για Player 1
                } else if (pawn.getOwnerId() == 2) {
                    pawnLabel.setBorder(BorderFactory.createLineBorder(Color.RED, 2)); // Red για Player 2
                }

                overlayPanel.add(pawnLabel);
            }

            // Ενημέρωση του τετραγώνου με τα πιονιά
            square.setLayout(new BorderLayout());
            square.add(overlayPanel, BorderLayout.CENTER);
            square.revalidate();
            square.repaint();
        }
    }






    public void updatePawnCount(Player player, int archaeologists, int theseus) {
        if (player.getId() == 1) {
            player1PawnsLabel.setText("Archaeologists: " + archaeologists + ", Theseus: " + theseus);
        } else {
            player2PawnsLabel.setText("Archaeologists: " + archaeologists + ", Theseus: " + theseus);
        }
    }


    public JLabel[][] getBoardSquares() {
        return boardSquares;
    }

    // Disable all board squares
    public void disableBoardSquares() {
        for (int row = 0; row < boardSquares.length; row++) {
            JLabel square = boardSquares[row][0];
            square.setEnabled(false);
            square.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Visual indicator of disabled state
        }
    }

    // Enable only the starting squares of the board
    public void enableBoardSquares() {
        for (int row = 0; row < boardSquares.length; row++) {
            JLabel square = boardSquares[row][0]; // Enable the first column of each path
            square.setEnabled(true);
            square.setBorder(BorderFactory.createLineBorder(Color.GREEN)); // Highlight enabled state
        }

    }
    public JButton[] getPlayer1Cards() {
        return player1Cards;
    }

    public JButton[] getPlayer2Cards() {
        return player2Cards;
    }


    public JLabel[] getPlayerFrames(int id) {
        if(id == 1){
            return playerFrames;
        }else if(id == 2){
            return player2Frames;
        }
        return null;
    }


    public JButton getDiscardCardButton() {
        return discardCardButton;
    }


    public JLabel getPlayer1StatuesLabel() {
        return player1StatuesLabel;
    }

    public JLabel getPlayer2StatuesLabel() {
        return player2StatuesLabel;
    }


    public JLabel getPlayer1ScoreLabel() {
        return player1ScoreLabel;
    }
    public JLabel getPlayer2ScoreLabel() {
        return player2ScoreLabel;
    }
}



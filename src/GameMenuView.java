    import javax.sound.sampled.*;
    import javax.swing.*;
    import javax.swing.border.LineBorder;
    import java.awt.*;
    import java.io.File;
    import java.io.IOException;

    // Finestra del menu di gioco
    public class GameMenuView extends JFrame implements ViewInterface {

        private JLabel jlTitle, jlMiranda, jlDaiPre; // Etichetta del titolo
        private JButton startButton, exitButton; // Pulsanti di avvio e uscita
        private JLayeredPane layeredPane; // Pannello stratificato
        private Player p1, p2; // Giocatori
        private Model model; // Modello del gioco

        private Clip ost; // Clip audio per la musica di sottofondo

        // Costruttore della finestra del menu di gioco
        public GameMenuView(Player p1, Player p2, Model model) {
            // Avvia la musica di sottofondo del menu
            ost = Model.playOST("BG_Menu_Ost.wav");
            ost.start();

            this.p1 = p1;
            this.p2 = p2;
            this.model = model;

            initialize(); // Inizializza i componenti della finestra
            createLayout(); // Crea il layout della finestra

            setSize(800, 600); // Imposta le dimensioni della finestra
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Chiudi l'applicazione quando si chiude la finestra
            setLocationRelativeTo(null); // Posiziona la finestra al centro dello schermo
            setVisible(true); // Rendi la finestra visibile
            setResizable(false); // Impedisci il ridimensionamento della finestra
            setTitle("SwingRace"); // Imposta il titolo della finestra
        }

        // Inizializza i componenti della finestra
        private void initialize() {
            layeredPane = getLayeredPane(); // Ottiene il pannello stratificato della finestra

            jlTitle = new JLabel("SWINGRACE", SwingConstants.CENTER); // Crea l'etichetta del titolo
            jlTitle.setFont(Model.getFont("Filled.ttf", 70)); // Imposta il font del titolo
            jlTitle.setForeground(Color.WHITE); // Imposta il colore del testo del titolo

            jlMiranda = new JLabel("Miranda");
            jlMiranda.setFont(Model.getFont("Filled.ttf", 20));
            jlMiranda.setForeground(Color.WHITE);

            jlDaiPre = new JLabel("Dai Pre'");
            jlDaiPre.setFont(Model.getFont("Filled.ttf", 20));
            jlDaiPre.setForeground(Color.WHITE);

            // Crea il pulsante di avvio
            startButton = new JButton("Start");
            startButton.setFont(Model.getFont("Filled.ttf", 20)); // Imposta il font del pulsante
            startButton.addActionListener(e -> {
                Model.playClip("Select.wav"); // Riproduce l'effetto sonoro del pulsante

                try {
                    Thread.sleep(2000); // Aggiungi un ritardo di 2 secondi
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }

                GameView gameView = null;
                try {
                    gameView = new GameView(); // Crea la vista del gioco
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                // Crea e imposta i controller per i giocatori
                Controller c1 = new Controller(p1, new int[] {87, 65, 83, 68}); // W - A - S - D
                c1.setModel(model);
                model.c1 = c1;
                Controller c2 = new Controller(p2, new int[] {38, 37, 40, 39}); // SU - SX - GIU - DX
                c2.setModel(model);
                model.c2 = c2;

                // Aggiunge i controller alla vista del gioco
                gameView.addKeyListener(c1);
                gameView.addKeyListener(c2);

                // Imposta il modello e la vista del gioco
                gameView.setModel(model);
                model.gameView = gameView;

                // Aggiorna la schermata di gioco con i giocatori
                gameView.refreshMatrice(p1);
                gameView.refreshMatrice(p2);

                ost.close(); // Chiudi la clip audio del menu
                this.dispose(); // Chiudi la finestra del menu
            });

            // Crea il pulsante di uscita
            exitButton = new JButton("Exit Game");
            exitButton.setFont(Model.getFont("Filled.ttf", 20)); // Imposta il font del pulsante
            exitButton.addActionListener(e -> {
                Model.playClip("Select.wav"); // Riproduce l'effetto sonoro del pulsante

                try {
                    Thread.sleep(2000); // Aggiungi un ritardo di 2 secondi
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }

                System.exit(0); // Chiude l'applicazione
            });
        }

    // Metodo per creare il layout della finestra del menu di gioco
    private void createLayout() {
        jlTitle.setBounds(0, 50, 800, 100); // Imposta le dimensioni e la posizione dell'etichetta del titolo
        layeredPane.add(jlTitle, JLayeredPane.PALETTE_LAYER); // Aggiunge l'etichetta del titolo al pannello stratificato

        startButton.setBounds(300, 250, 200, 100); // Imposta le dimensioni e la posizione del pulsante di avvio
        layeredPane.add(startButton, JLayeredPane.PALETTE_LAYER); // Aggiunge il pulsante di avvio al pannello stratificato

        exitButton.setBounds(300, 400, 200, 50); // Imposta le dimensioni e la posizione del pulsante di uscita
        layeredPane.add(exitButton, JLayeredPane.PALETTE_LAYER); // Aggiunge il pulsante di uscita al pannello stratificato

        // Imposta lo sfondo della finestra
        JLabel background = new JLabel(new ImageIcon(IMAGES_PATH + "MainBG.png")); // Crea un'etichetta con un'immagine di sfondo
        background.setBounds(0, 0, 800, 600); // Imposta le dimensioni e la posizione dello sfondo
        layeredPane.add(background, JLayeredPane.DEFAULT_LAYER); // Aggiunge lo sfondo al pannello stratificato

        jlMiranda.setOpaque(false);
        jlDaiPre.setOpaque(false);

        // Imposta le posizioni delle JLabel
        jlMiranda.setBounds(640, 450, 150, 50);
        jlDaiPre.setBounds(640, 500, 150, 50);

        // Aggiunta delle JLabel al pannello stratificato
        layeredPane.add(jlMiranda, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(jlDaiPre, JLayeredPane.PALETTE_LAYER);
    }

}
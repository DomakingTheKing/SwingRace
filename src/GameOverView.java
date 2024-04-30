import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameOverView extends JFrame implements ViewInterface{

    private JLabel jlGameOver, jlPlayer1, jlPlayer2, jlWinnerName, jlLooserName, jlWinnerHops, jlLooserHops;
    private JLayeredPane layeredPane;
    private JButton exitButton;

    private Player p1, p2;

    Model model;

    // Costruttore della finestra di gioco terminato
    public GameOverView(Player p1, Player p2){
        this.p1 = p1;
        this.p2 = p2;

        initialize(); // Inizializza i componenti della finestra
        createLayout(); // Crea il layout della finestra

        setSize(WINDOW_WIDTH, WINDOW_HEIGHT); // Imposta le dimensioni della finestra
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Imposta la chiusura dell'applicazione quando la finestra viene chiusa
        setLocationRelativeTo(null); // Imposta la posizione della finestra al centro dello schermo
        setVisible(true); // Imposta la visibilità della finestra su true
        setResizable(false); // Impedisce il ridimensionamento della finestra
        setTitle("SwingRace"); // Imposta il titolo della finestra
    }

    // Metodo per inizializzare i componenti della finestra
    private void initialize(){
        layeredPane = getLayeredPane(); // Pannello per gestire componenti su più livelli
        // Inizializza l'etichetta per lo sfondo del game over
        jlGameOver = new JLabel(new ImageIcon(IMAGES_PATH + "GameOverBG.png"));

        // Inizializza le etichette per i giocatori
        jlPlayer1 = new JLabel();
        jlPlayer2 = new JLabel();

        // Inizializza le etichette per i nomi del vincitore e del perdente
        jlWinnerName = new JLabel();
        jlLooserName = new JLabel();

        // Inizializza le etichette per il numero di salti del vincitore e del perdente
        jlWinnerHops = new JLabel();
        jlLooserHops = new JLabel();

        // Inizializza il pulsante di uscita
        exitButton = new JButton();
    }

    // Metodo per creare il layout della finestra
    private void createLayout() {
        jlGameOver.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT); // Imposta le dimensioni e la posizione dello sfondo del game over
        layeredPane.add(jlGameOver, JLayeredPane.DEFAULT_LAYER); // Aggiunge lo sfondo al pannello stratificato

        // Imposta le dimensioni e la posizione del pulsante di uscita
        exitButton.setBounds(WINDOW_WIDTH - 415, WINDOW_HEIGHT - 240, 400, 200);
        exitButton.setText("EXIT"); // Imposta il testo del pulsante
        exitButton.setFont(Model.getFont("Filled.ttf", 80)); // Imposta il font del testo del pulsante
        exitButton.setForeground(new Color(220, 14, 14)); // Imposta il colore del testo del pulsante
        exitButton.addActionListener(e -> { // Aggiunge un'azione al clic del pulsante
            Model.playClip("Select.wav"); // Riproduce un suono quando viene premuto il pulsante

            try {
                Thread.sleep(2000); // Mette in pausa l'esecuzione per 2 secondi
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }

            dispose(); // Chiude la finestra
        });
        exitButton.setBackground(Color.WHITE); // Imposta il colore di sfondo del pulsante
        exitButton.setBorderPainted(false); // Imposta il bordo del pulsante come non visibile
        layeredPane.add(exitButton, JLayeredPane.PALETTE_LAYER); // Aggiunge il pulsante al pannello stratificato

        // Imposta le dimensioni e la posizione dell'etichetta del nome del vincitore
        jlWinnerName.setBounds(325, 70, 300, 240);
        jlWinnerName.setFont(Model.getFont("Filled.ttf", 120)); // Imposta il font del testo
        jlWinnerName.setForeground(new Color(241, 241, 241)); // Imposta il colore del testo
        layeredPane.add(jlWinnerName, JLayeredPane.PALETTE_LAYER); // Aggiunge l'etichetta al pannello stratificato

        // Imposta le dimensioni e la posizione dell'etichetta del nome del perdente
        jlLooserName.setBounds(860, 285, 400, 140);
        jlLooserName.setFont(Model.getFont("Filled.ttf", 80)); // Imposta il font del testo
        layeredPane.add(jlLooserName, JLayeredPane.PALETTE_LAYER); // Aggiunge l'etichetta al pannello stratificato

        jlWinnerHops.setBounds(jlWinnerName.getX()-65, 750, 400, 140);
        jlWinnerHops.setFont(Model.getFont("Filled.ttf", 50));
        jlWinnerHops.setForeground(Color.WHITE);
        layeredPane.add(jlWinnerHops, JLayeredPane.PALETTE_LAYER);

        jlLooserHops.setBounds(jlLooserName.getX()-75, 580, 400, 140);
        jlLooserHops.setFont(Model.getFont("Filled.ttf", 50));
        jlLooserHops.setForeground(Color.WHITE);
        layeredPane.add(jlLooserHops, JLayeredPane.PALETTE_LAYER);
    }

    // Get the image of the player
    private ImageIcon getPlayerImage(String imgName, int width, int height){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(IMAGES_PATH + imgName));
        } catch (IOException e) {
            System.err.println("Failed to load image: " + imgName);
            return null;
        }
        Image scaled_img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled_img);
    }

    // Metodo per impostare l'immagine del giocatore
    private void setPlayerImage(String playerName, int x, int y, int width, int height){
        // Determina quale etichetta del giocatore utilizzare in base al nome del giocatore
        JLabel playerLabel = playerName.equals("p1") ? jlPlayer1 : jlPlayer2;
        // Determina il nome del file dell'immagine del giocatore in base al nome del giocatore
        String imgName = playerName.equals("p1") ? "Player1" : "Player2";
        // Ottiene l'immagine del giocatore con il nome del file, le dimensioni specificate e la imposta sull'etichetta del giocatore
        playerLabel.setIcon(getPlayerImage(imgName + ".png", width, height));
        // Imposta le dimensioni e la posizione dell'etichetta del giocatore
        playerLabel.setBounds(x, y, width, height);
        // Aggiunge l'etichetta del giocatore al pannello stratificato
        layeredPane.add(playerLabel, JLayeredPane.PALETTE_LAYER);
    }

    // Metodo per impostare il vincitore e il perdente del gioco
    public void setWinner(String winner) {
        // Imposta l'immagine e il nome del perdente
        setPlayerImage(winner.equals("p1") ? "p2" : "p1", 830, 425, 192, 192);
        jlLooserName.setText(winner.equals("p1") ? "P2" : "P1");
        jlLooserHops.setText((winner.equals("p1") ? p2.getHops() : p1.getHops()) + " HOPS");
        // Imposta l'immagine e il nome del vincitore
        setPlayerImage(winner, 240, 415, 384, 384);
        jlWinnerName.setText(winner.equals("p1") ? "P1" : "P2");
        jlWinnerHops.setText((winner.equals("p1") ? p1.getHops() : p2.getHops()) + " HOPS");
        // Imposta la corona sul vincitore
        setCrown();
    }

    // Metodo per impostare la corona sul vincitore
    private void setCrown(){
        // Crea un'etichetta per la corona e imposta l'immagine della corona
        JLabel jlCrown = new JLabel();
        jlCrown.setIcon(getPlayerImage("Crown.png", 320, 320));
        // Imposta le dimensioni e la posizione dell'etichetta della corona
        jlCrown.setBounds(240+32, 415-(320/2)+10, 320, 320);
        // Aggiunge l'etichetta della corona al pannello stratificato
        layeredPane.add(jlCrown, JLayeredPane.MODAL_LAYER);
    }

}
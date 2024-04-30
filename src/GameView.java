import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameView extends JFrame implements ViewInterface{

    // Costanti
    private static final int MAX_ROWS = 4;
    private static final int MIN_ROWS = 3;
    private static final int ROW_HEIGHT = 128;
    private static final int ROW_GAP = 50;

    // Variabili
    private JPanel jpLeftColumn, jpRightColumn, jpTopBar, jpBottomBar;
    private JLabel jlPlayer1, jlPlayer2, jlSalvaGenteP1, jlSalvaGenteP2;

    private Clip ost;

    public JPanel jpOstacoliP1, jpOstacoliP2, jpHeartsP1, jpHeartsP2;

    Model model;

    // Costruttore della classe GameView
    public GameView() throws IOException {
        // Riproduce la colonna sonora di sfondo del gioco
        ost = Model.playOST("BG_Game_Ost.wav"); 
        ost.start(); // Avvia la riproduzione della colonna sonora

        // Inizializza la vista del gioco
        initialize();
        // Crea il layout della finestra
        createLayout();

        // Imposta le dimensioni della finestra
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        // Chiude l'applicazione quando la finestra viene chiusa
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Centra la finestra nello schermo
        setLocationRelativeTo(null);
        // Rende la finestra visibile
        setVisible(true);
        // Impedisce il ridimensionamento della finestra
        setResizable(false);
        // Imposta il titolo della finestra
        setTitle("SwingRace");
    }


    // Metodo per inizializzare la schermata iniziale di start
    private void initialize() throws IOException {
        // Imposta il layout della finestra su BorderLayout
        setLayout(new BorderLayout());

        // Carica l'immagine di sfondo della schermata principale
        BufferedImage MainBG = ImageIO.read(new File(IMAGES_PATH + "MainBG.png"));

        // Pannello sinistro della schermata
        jpLeftColumn = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Disegna l'immagine di sfondo
                g.drawImage(MainBG, 0, 0, null);
            }
        };

        // Pannello destro della schermata
        jpRightColumn = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Disegna l'immagine di sfondo
                g.drawImage(MainBG, 0, 0, null);
            }
        };

        // Pannello della barra superiore
        jpTopBar = new JPanel(null);
        JLabel jlTopBarIMG = new JLabel(new ImageIcon(IMAGES_PATH + "TopBarBG.png"));
        jlTopBarIMG.setBounds(0, 0, WINDOW_WIDTH, 100);
        jpTopBar.add(jlTopBarIMG);

        // Pannello della barra inferiore
        jpBottomBar = new JPanel(null);
        JLabel jpBottomBarIMG = new JLabel(new ImageIcon(IMAGES_PATH + "BottomBarBG.png"));
        jpBottomBarIMG.setBounds(0, 0, WINDOW_WIDTH, 100);
        jpBottomBar.add(jpBottomBarIMG);

        // Pannelli per gli ostacoli dei giocatori
        jpOstacoliP1 = new JPanel();
        jpOstacoliP2 = new JPanel();

        // Pannelli per i cuori dei giocatori
        jpHeartsP1 = new JPanel(new GridLayout(1, 3));
        jpHeartsP2 = new JPanel(new GridLayout(1, 3));

        // le immagini dei giocatori e i Salva Gente
        jlPlayer1 = new JLabel(new ImageIcon(IMAGES_PATH + "Player1.png"));
        jlPlayer2 = new JLabel(new ImageIcon(IMAGES_PATH + "Player2.png"));
        jlSalvaGenteP1 = new JLabel(new ImageIcon(IMAGES_PATH + "SalvaGente.png"));
        jlSalvaGenteP2 = new JLabel(new ImageIcon(IMAGES_PATH + "SalvaGente.png"));
    }


    // Metodo per creare il layout della finestra del gioco
    private void createLayout() {
        // Imposta i bordi dei pannelli sinistro e destro
        jpLeftColumn.setBorder(BorderFactory.createLineBorder(new Color(101,48,25), 10));
        jpRightColumn.setBorder(BorderFactory.createLineBorder(new Color(101,48,25), 10));

        // Imposta le dimensioni e aggiunge la barra superiore al layout
        jpTopBar.setPreferredSize(new Dimension(WINDOW_WIDTH, 100));
        topBar();
        add(jpTopBar, BorderLayout.NORTH);

        // Imposta le dimensioni e aggiunge la barra inferiore al layout
        jpBottomBar.setPreferredSize(new Dimension(WINDOW_WIDTH, 100));
        add(jpBottomBar, BorderLayout.SOUTH);

        // Imposta le dimensioni e aggiunge il pannello sinistro al layout
        jpLeftColumn.setPreferredSize(new Dimension(WINDOW_WIDTH/2, WINDOW_HEIGHT - jpTopBar.getHeight()*2));
        LeftColumn();
        add(jpLeftColumn, BorderLayout.WEST);

        // Imposta le dimensioni e aggiunge il pannello destro al layout
        jpRightColumn.setPreferredSize(new Dimension(WINDOW_WIDTH/2, WINDOW_HEIGHT - jpTopBar.getHeight()*2));
        RightColumn();
        add(jpRightColumn, BorderLayout.EAST);

        // Imposta il layout dei pannelli degli ostacoli dei giocatori
        jpOstacoliP1.setLayout(null);
        jpOstacoliP2.setLayout(null);
    }


    // Metodo per configurare il layout del primo giocatore che si trova a sinistra
    private void LeftColumn(){
        // Imposta le posizioni delle immagini del giocatore 1 e del salva gente
        jlPlayer1.setBounds(320-32,600,64,64);
        jlSalvaGenteP1.setBounds(320-64,600-32,128,128);

        // Imposta le dimensioni e la posizione del pannello degli ostacoli del giocatore 1
        jpOstacoliP1.setBounds(20,20,(WINDOW_WIDTH/2)-40, (int) (WINDOW_HEIGHT/1.4));
        jpOstacoliP1.setOpaque(false); // Imposta il pannello come trasparente

        // Aggiunge il pannello degli ostacoli, l'immagine del giocatore 1 e il salva gente
        jpLeftColumn.add(jpOstacoliP1);
        jpLeftColumn.add(jlPlayer1);
        jpLeftColumn.add(jlSalvaGenteP1);
    }

    // Metodo per configurare il layout del secondo giocatore che si trova a destra
    private void RightColumn(){
        // Imposta le posizioni delle immagini del giocatore 2 e del salva gente
        jlPlayer2.setBounds(320-32,600,64,64);
        jlSalvaGenteP2.setBounds(320-64,600-32,128,128);

        // Imposta le dimensioni e la posizione del pannello degli ostacoli del giocatore 2
        jpOstacoliP2.setBounds(20,20,(WINDOW_WIDTH/2)-40,(int) (WINDOW_HEIGHT/1.4));
        jpOstacoliP2.setOpaque(false);

        // Aggiunge il pannello degli ostacoli, l'immagine del giocatore 2 e il salva gente
        jpRightColumn.add(jpOstacoliP2);
        jpRightColumn.add(jlPlayer2);
        jpRightColumn.add(jlSalvaGenteP2);
    }

    // Metodo per configurare la barra superiore dei cuori
    private void topBar(){
        // Imposta le dimensioni e la posizione del pannello dei cuori del giocatore 1 e 2
        jpHeartsP1.setBounds(170,0,300,100);
        jpHeartsP2.setBounds(150 + (WINDOW_WIDTH/2),0,300,100);

        // Imposta il pannello dei cuori del giocatore 1 e 2 come trasparente
        jpHeartsP1.setOpaque(false);
        jpHeartsP2.setOpaque(false);

        // Aggiunge i pannelli dei cuori dei giocatori alla barra superiore
        jpTopBar.add(jpHeartsP1);
        jpTopBar.add(jpHeartsP2);

        // Sposta i pannelli dei cuori sopra la barra
        jpTopBar.setComponentZOrder(jpHeartsP1, 0);
        jpTopBar.setComponentZOrder(jpHeartsP2, 0);
    }


    // Metodo per impostare il modello
    public void setModel(Model model){
        this.model = model;
    }

    // Metodo per bloccare il KeyListener dopo la sconfitta
    public void removeListener(KeyListener keyListener){
        this.removeKeyListener(keyListener);
    }

    // Metodo per rimuovere il salvagente quando viene eseguita la prima mossa
    public void removeSalvagente(Player player){
        if(player.getName().equals("p1")){
            jpLeftColumn.remove(2);
        } else {
            jpRightColumn.remove(2);
        }
    }


    // Metodo per aggiornare la matrice degli ostacoli
    public void refreshMatrice(Player p){
        // Determina il numero di righe della matrice degli ostacoli in base allo stato dell'attacco del giocatore
        int v = (p.getAttacco().getExpanded()) ? MAX_ROWS : MIN_ROWS;

        // Ottiene il pannello degli ostacoli del giocatore
        JPanel jpOstacoli = getOstacoliPanel(p);
        // Rimuove tutti gli ostacoli presenti nel pannello degli ostacoli
        jpOstacoli.removeAll();

        // Crea la matrice degli ostacoli
        for (int i = 0; i < v; i++) {
            // Crea una riga della matrice
            JPanel row = createRow(i);

            // Aggiunge gli ostacoli alla riga
            for (int j = 0; j < 4; j++) {
                JLabel ostacolo = createOstacolo(i, j, p);
                row.add(ostacolo);
            }

            // Aggiunge la riga al pannello degli ostacoli
            jpOstacoli.add(row);

            // Sposta il giocatore nell'ultima riga della matrice
            if(i == v-1){
                movePlayer(p, row);
            }
        }

        // Aggiorna i cuori del giocatore
        refreshHearts(p);

        // Ridisegna e ristruttura la vista
        repaint();
        revalidate();
    }

    // Metodo per ottenere il pannello degli ostacoli
    private JPanel getOstacoliPanel(Player p) {
        return p.getName().equals("p1") ? jpOstacoliP1 : jpOstacoliP2;
    }


    // Metodo per creare una riga della matrice degli ostacoli
    private JPanel createRow(int i) {
        // Crea un nuovo pannello per rappresentare una riga
        JPanel row = new JPanel();
        // Imposta il layout del pannello su null per consentire il posizionamento assoluto degli elementi
        row.setLayout(null);
        // Imposta il nome del pannello utilizzando l'indice della riga
        row.setName("row" + i);
        // Imposta le dimensioni e la posizione del pannello all'interno del pannello degli ostacoli del giocatore 1
        row.setBounds(10, (i * ROW_HEIGHT) + (i * ROW_GAP), jpOstacoliP1.getWidth(), ROW_HEIGHT);
        // Imposta il pannello come trasparente per visualizzare lo sfondo
        row.setOpaque(false);
        return row;
    }


    // Metodo per creare un'etichetta che rappresenta un singolo ostacolo nella matrice degli ostacoli
    private JLabel createOstacolo(int i, int j, Player p) {
        // Crea una nuova etichetta per rappresentare l'ostacolo
        JLabel ostacolo = new JLabel();
        // Imposta il nome dell'ostacolo utilizzando l'indice della riga e della colonna
        ostacolo.setName("ostacoloRow" + i + "box" + j);
        // Imposta le dimensioni e la posizione dell'etichetta all'interno della riga
        ostacolo.setBounds((ROW_HEIGHT * j) + (j * 25), 0, ROW_HEIGHT, ROW_HEIGHT);

        // Verifica lo stato dell'ostacolo nella posizione specificata nella matrice degli attacchi del giocatore
        if (p.getAttacco().getKilledObstacles().contains(new Point(i, j))) {
            // Se l'ostacolo è stato distrutto, imposta l'icona corrispondente
            ostacolo.setIcon(new ImageIcon(IMAGES_PATH + "KilledCroco.png"));
        } else if (p.getAttacco().getAttacchi()[i][j] == 0) {
            // Se l'ostacolo è presente e non è stato distrutto, imposta l'icona corrispondente
            ostacolo.setIcon(new ImageIcon(IMAGES_PATH + "Croco.png"));
        } else {
            // Se non ci sono ostacoli nella posizione specificata, imposta un'icona di default
            ostacolo.setIcon(new ImageIcon(IMAGES_PATH + "Crate.png"));
        }

        // Imposta l'etichetta come trasparente per visualizzare lo sfondo
        ostacolo.setOpaque(false);
        return ostacolo;
    }


    // Metodo per spostare l'icona del giocatore sulla riga corrispondente nella matrice degli ostacoli
    private void movePlayer(Player p, JPanel row){
        JLabel playerLabel;
        JPanel playerColumn;

        // Determina l'icona del giocatore e il pannello a cui appartiene in base al nome del giocatore
        if(p.getName().equals("p1")){
            playerLabel = jlPlayer1; // Icona del giocatore 1
            playerColumn = jpLeftColumn; // Pannello sinistro
        } else {
            playerLabel = jlPlayer2; // Icona del giocatore 2
            playerColumn = jpRightColumn; // Pannello destro
        }

        // Verifica se il giocatore si trova sulla matrice degli ostacoli
        if(p.getPos() != -1){
            // Calcola la posizione corretta per l'icona del giocatore sulla riga
            int xPos = row.getX() + ((row.getComponent(p.getPos()).getWidth())/2)-12 + row.getComponent(p.getPos()).getX();
            int yPos = row.getY() + ((row.getComponent(p.getPos()).getWidth())/2)-12;
            // Imposta la posizione dell'icona del giocatore
            playerLabel.setLocation(xPos, yPos);
        }
        // Sposta l'icona del giocatore in cima al pannello
        playerColumn.setComponentZOrder(playerLabel, 0);
    }


    // Metodo per aggiornare la visualizzazione dei cuori del giocatore nella vista
    public void refreshHearts(Player p){
        // Determina il pannello dei cuori del giocatore in base al suo nome
        JPanel jpHearts = p.getName().equals("p1") ? jpHeartsP1 : jpHeartsP2;
        // Rimuove tutti i cuori presenti nel pannello
        jpHearts.removeAll();

        // Ottiene lo stato attuale della salute del giocatore
        int health = p.getHealth();
        if (health != 0) { // Se il giocatore ha ancora punti vita
            // Aggiunge un'icona di cuore per ogni punto vita rimasto
            for (int i = 0; i < health; i++) {
                JLabel heart = new JLabel(new ImageIcon(IMAGES_PATH + "Heart.png"));
                jpHearts.add(heart); // Aggiunge il cuore al pannello dei cuori
            }
        }

        // Ridisegna e ristruttura il pannello dei cuori
        jpHearts.repaint();
        jpHearts.revalidate();
    }

    // Metodo per visualizzare una X quando un giocatore perde
    public void gameOverScreen(Player p){
        
        JLabel playerLabel;
        JPanel playerColumn;

        // Determina l'icona del giocatore e il pannello a cui appartiene in base al nome del giocatore
        if(p.getName().equals("p1")){
            playerLabel = jlPlayer1; // Icona del giocatore 1
            playerColumn = jpLeftColumn; // Pannello sinistro
        } else {
            playerLabel = jlPlayer2; // Icona del giocatore 2
            playerColumn = jpRightColumn; // Pannello destro
        }

        // Crea un'etichetta per visualizzare l'immagine di "Game Over"
        JLabel jlGameOver = new JLabel();
        jlGameOver.setLocation(0,0);
        jlGameOver.setSize(playerColumn.getWidth(), playerColumn.getHeight());
        jlGameOver.setIcon(new ImageIcon(IMAGES_PATH + "DiedFG.png"));

        // Aggiunge l'etichetta al pannello del giocatore e la sposta in cima al pannello
        playerColumn.add(jlGameOver);
        playerColumn.setComponentZOrder(jlGameOver, 0);
        playerColumn.setComponentZOrder(playerLabel, 1);

        // Ridisegna il pannello del giocatore per visualizzare i cambiamenti
        playerColumn.repaint();
    }

    //restituisce l'Ost
    public Clip getOST(){
        return ost;
    }

}

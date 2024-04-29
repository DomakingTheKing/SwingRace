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

    public GameView() throws IOException {
        initialize();
        createLayout();

        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        playOST("BG_Ost.wav");
    }

    private void initialize() throws IOException {
        setLayout(new BorderLayout());

        BufferedImage MainBG = ImageIO.read(new File(IMAGES_PATH + "MainBG.png"));

        jpLeftColumn = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(MainBG, 0, 0, null);
            }
        };

        jpRightColumn = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(MainBG, 0, 0, null);
            }
        };

        jpTopBar = new JPanel(null);
        JLabel jlTopBarIMG = new JLabel(new ImageIcon(IMAGES_PATH + "TopBarBG.png"));
        jlTopBarIMG.setBounds(0,0,WINDOW_WIDTH,100);
        jpTopBar.add(jlTopBarIMG);

        jpBottomBar = new JPanel(null);
        JLabel jpBottomBarIMG = new JLabel(new ImageIcon(IMAGES_PATH + "BottomBarBG.png"));
        jpBottomBarIMG.setBounds(0,0,WINDOW_WIDTH,100);
        jpBottomBar.add(jpBottomBarIMG);

        jpOstacoliP1 = new JPanel();
        jpOstacoliP2 = new JPanel();
        jpHeartsP1 = new JPanel(new GridLayout(1,3));
        jpHeartsP2 = new JPanel(new GridLayout(1,3));

        jlPlayer1 = new JLabel(new ImageIcon(IMAGES_PATH + "Player1.png"));
        jlPlayer2 = new JLabel(new ImageIcon(IMAGES_PATH + "Player2.png"));
        jlSalvaGenteP1 = new JLabel(new ImageIcon(IMAGES_PATH + "SalvaGente.png"));
        jlSalvaGenteP2 = new JLabel(new ImageIcon(IMAGES_PATH + "SalvaGente.png"));

    }

    private void createLayout() {
        jpLeftColumn.setBorder(BorderFactory.createLineBorder(new Color(101,48,25), 10));
        jpRightColumn.setBorder(BorderFactory.createLineBorder(new Color(101,48,25), 10));

        jpTopBar.setPreferredSize(new Dimension(WINDOW_WIDTH, 100));
        topBar();
        add(jpTopBar, BorderLayout.NORTH);

        jpBottomBar.setPreferredSize(new Dimension(WINDOW_WIDTH, 100));
        add(jpBottomBar, BorderLayout.SOUTH);

        jpLeftColumn.setPreferredSize(new Dimension(WINDOW_WIDTH/2, WINDOW_HEIGHT - jpTopBar.getHeight()*2));
        LeftColumn();
        add(jpLeftColumn, BorderLayout.WEST);

        jpRightColumn.setPreferredSize(new Dimension(WINDOW_WIDTH/2, WINDOW_HEIGHT - jpTopBar.getHeight()*2));
        RightColumn();
        add(jpRightColumn, BorderLayout.EAST);

        jpOstacoliP1.setLayout(null);
        jpOstacoliP2.setLayout(null);
    }

    private void LeftColumn(){
        jlPlayer1.setBounds(320-32,600,64,64);
        jlSalvaGenteP1.setBounds(320-64,600-32,128,128);

        jpOstacoliP1.setBounds(20,20,(WINDOW_WIDTH/2)-40, (int) (WINDOW_HEIGHT/1.4));
        //jpOstacoliP1.setBorder(new LineBorder(Color.YELLOW,10));
        jpOstacoliP1.setOpaque(false);

        jpLeftColumn.add(jpOstacoliP1);
        jpLeftColumn.add(jlPlayer1);
        jpLeftColumn.add(jlSalvaGenteP1);
    }

    private void RightColumn(){
        jlPlayer2.setBounds(320-32,600,64,64);
        jlSalvaGenteP2.setBounds(320-64,600-32,128,128);

        jpOstacoliP2.setBounds(20,20,(WINDOW_WIDTH/2)-40,(int) (WINDOW_HEIGHT/1.4));
        //jpOstacoliP2.setBorder(new LineBorder(Color.YELLOW,10));
        jpOstacoliP2.setOpaque(false);

        jpRightColumn.add(jpOstacoliP2);
        jpRightColumn.add(jlPlayer2);
        jpRightColumn.add(jlSalvaGenteP2);
    }

    private void topBar(){
        jpHeartsP1.setBounds(170,0,300,100);
        jpHeartsP2.setBounds(150 + (WINDOW_WIDTH/2),0,300,100);

        jpHeartsP1.setOpaque(false);
        jpHeartsP2.setOpaque(false);

        jpTopBar.add(jpHeartsP1);
        jpTopBar.add(jpHeartsP2);

        // Move jpHeartsP1 and jpHeartsP2 to the top
        jpTopBar.setComponentZOrder(jpHeartsP1, 0);
        jpTopBar.setComponentZOrder(jpHeartsP2, 0);
    }


    public void setModel(Model model){
        this.model = model;
    }

    public void removeListener(KeyListener keyListener){
        this.removeKeyListener(keyListener);
    }

    public void removeSalvagente(Player player){
        if(player.getName().equals("p1")){
            jpLeftColumn.remove(2);
        } else {
            jpRightColumn.remove(2);
        }
    }

    public void refreshMatrice(Player p){
        int v = (p.getAttacco().getExpanded()) ? MAX_ROWS : MIN_ROWS;

        JPanel jpOstacoli = getOstacoliPanel(p);
        jpOstacoli.removeAll();

        // crea matrice
        for (int i = 0; i < v; i++) {
            JPanel row = createRow(i);

            // row assembler
            for (int j = 0; j < 4; j++) {
                JLabel ostacolo = createOstacolo(i, j, p);
                row.add(ostacolo);
            }

            jpOstacoli.add(row);
            if(i == v-1){
                movePlayer(p, row);
            }
        }

        refreshHearts(p);

        repaint();
        revalidate();
    }

    private JPanel getOstacoliPanel(Player p) {
        return p.getName().equals("p1") ? jpOstacoliP1 : jpOstacoliP2;
    }

    private JPanel createRow(int i) {
        JPanel row = new JPanel();
        row.setLayout(null);
        //row.setBorder(new LineBorder(Color.RED, 5));
        row.setName("row" + i);
        row.setBounds(10, (i*ROW_HEIGHT)+(i*ROW_GAP), jpOstacoliP1.getWidth(), ROW_HEIGHT);
        row.setOpaque(false);
        return row;
    }

    private JLabel createOstacolo(int i, int j, Player p) {
        JLabel ostacolo = new JLabel();
        ostacolo.setName("ostacoloRow" + i + "box" + j);
        ostacolo.setBounds((ROW_HEIGHT*j)+(j*25), 0, ROW_HEIGHT, ROW_HEIGHT);

        if (p.getAttacco().getKilledObstacles().contains(new Point(i, j))) {
            ostacolo.setIcon(new ImageIcon(IMAGES_PATH + "KilledCroco.png"));
        } else if (p.getAttacco().getAttacchi()[i][j] == 0) {
            ostacolo.setIcon(new ImageIcon(IMAGES_PATH + "Croco.png"));
        } else {
            ostacolo.setIcon(new ImageIcon(IMAGES_PATH + "Crate.png"));
        }

        ostacolo.setOpaque(false);
        return ostacolo;
    }

    private void movePlayer(Player p, JPanel row){
        JLabel playerLabel;
        JPanel playerColumn;

        if(p.getName().equals("p1")){
            playerLabel = jlPlayer1;
            playerColumn = jpLeftColumn;
        } else {
            playerLabel = jlPlayer2;
            playerColumn = jpRightColumn;
        }

        if(p.getPos() != -1){
            playerLabel.setLocation(row.getX() + ((row.getComponent(p.getPos()).getWidth())/2)-12 + row.getComponent(p.getPos()).getX(),
                                    row.getY() + ((row.getComponent(p.getPos()).getWidth())/2)-12);
        }
        playerColumn.setComponentZOrder(playerLabel, 0);
    }

    public void refreshHearts(Player p){
        JPanel jpHearts = p.getName().equals("p1") ? jpHeartsP1 : jpHeartsP2;
        jpHearts.removeAll();

        int health = p.getHealth();
        if (health != 0) {
            for (int i = 0; i < health; i++) {
                JLabel heart = new JLabel(new ImageIcon(IMAGES_PATH + "Heart.png"));
                jpHearts.add(heart);
            }
        }

        jpHearts.repaint();
        jpHearts.revalidate();
    }

    public void gameOverScreen(Player p){
        JLabel playerLabel;
        JPanel playerColumn;

        if(p.getName().equals("p1")){
            playerLabel = jlPlayer1;
            playerColumn = jpLeftColumn;
        } else {
            playerLabel = jlPlayer2;
            playerColumn = jpRightColumn;
        }

        JLabel jlGameOver = new JLabel();
        jlGameOver.setLocation(0,0);
        jlGameOver.setSize(playerColumn.getWidth(), playerColumn.getHeight());
        jlGameOver.setIcon(new ImageIcon(IMAGES_PATH + "DiedFG.png"));

        playerColumn.add(jlGameOver);
        playerColumn.setComponentZOrder(jlGameOver, 0);
        playerColumn.setComponentZOrder(playerLabel, 1);
        playerColumn.repaint();
    }

    private void playOST(String fileName) {
        new Thread(() -> {
            try {
                File audioFile = new File(MUSIC_PATH + fileName);
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                ost = AudioSystem.getClip();
                ost.open(audioStream);
                ost.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public Clip getOST(){
        return ost;
    }

}

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Game extends JFrame {

    // Costanti
    private final int WIDTH = 1280;
    private final int HEIGHT = 960;

    // Variabili
    private JPanel jpLeftColumn, jpRightColumn, jpTopBar, jpBottomBar;
    private JLabel jlPlayer0, jlPlayer1, jlSalvaGenteP0, jlSalvaGenteP1;

    public Game() throws IOException {
        initialize();
        createLayout();
        addListeners();

        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);

        startGame();
    }

    private void startGame() {
        Thread TP0 = new Thread(new Player(jlPlayer0, jlSalvaGenteP0));
        TP0.start();

        // Thread TP1 = new Thread(new Player(jlPlayer1, jlSalvaGenteP1));
    }

    private void initialize() throws IOException {
        setLayout(new BorderLayout());

        BufferedImage MainBG = ImageIO.read(new File("Assets/Images/MainBG.png"));

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

        BufferedImage TopBarBG = ImageIO.read(new File("Assets/Images/TopBarBG.png"));

        jpTopBar = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(TopBarBG, 0, 0, null);
            }
        };

        BufferedImage BottomBarBG = ImageIO.read(new File("Assets/Images/BottomBarBG.png"));

        jpBottomBar = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(BottomBarBG, 0, 0, null);
            }
        };

        jlPlayer0 = new JLabel(new ImageIcon("Assets/Images/Player0.png"));
        jlPlayer1 = new JLabel(new ImageIcon("Assets/Images/Player1.png"));
        jlSalvaGenteP0 = new JLabel(new ImageIcon("Assets/Images/SalvaGente.png"));
        jlSalvaGenteP1 = new JLabel(new ImageIcon("Assets/Images/SalvaGente.png"));
    }

    private void createLayout() {
        jpLeftColumn.setBorder(BorderFactory.createLineBorder(new Color(101,48,25), 10));
        jpRightColumn.setBorder(BorderFactory.createLineBorder(new Color(101,48,25), 10));
        //jpTopBar.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 10));
        //jpBottomBar.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 10));

        jpTopBar.setPreferredSize(new Dimension(WIDTH, 100));
        add(jpTopBar, BorderLayout.NORTH);

        jpBottomBar.setPreferredSize(new Dimension(WIDTH, 100));
        add(jpBottomBar, BorderLayout.SOUTH);

        jpLeftColumn.setPreferredSize(new Dimension(WIDTH/2, HEIGHT - jpTopBar.getHeight()*2));
        LeftColumn();
        add(jpLeftColumn, BorderLayout.WEST);

        jpRightColumn.setPreferredSize(new Dimension(WIDTH/2, HEIGHT - jpTopBar.getHeight()*2));
        RightColumn();
        add(jpRightColumn, BorderLayout.EAST);
    }

    private void addListeners() {

    }

    private void LeftColumn(){
        jlPlayer0.setBounds(320-32,600,64,64);
        jlSalvaGenteP0.setBounds(320-64,600-32,128,128);

        jpLeftColumn.add(jlPlayer0);
        jpLeftColumn.add(jlSalvaGenteP0);
    }

    private void RightColumn(){
        jlPlayer1.setBounds(320-32,600,64,64);
        jlSalvaGenteP1.setBounds(320-64,600-32,128,128);

        jpRightColumn.add(jlPlayer1);
        jpRightColumn.add(jlSalvaGenteP1);
    }

}

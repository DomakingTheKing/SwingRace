import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Game extends JFrame {

    // Costanti
    private final int WIDTH = 1280;
    private final int HEIGHT = 960;

    // Variabili
    private JPanel jpLeftColumn, jpRightColumn, jpTopBar, jpBottomBar;
    private JLabel jlPlayer0, jlPlayer1, jlSalvaGenteP0, jlSalvaGenteP1;

    private Thread Tplayer0;

    Model model;

    public Game() throws IOException, InterruptedException {
        initialize();
        createLayout();
        addListeners();

        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
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

        jpTopBar = new JPanel(null);
        JLabel jlTopBarIMG = new JLabel(new ImageIcon("Assets/Images/TopBarBG.png"));
        jlTopBarIMG.setBounds(0,0,WIDTH,100);
        jpTopBar.add(jlTopBarIMG);

        jpBottomBar = new JPanel(null);
        JLabel jpBottomBarIMG = new JLabel(new ImageIcon("Assets/Images/BottomBarBG.png"));
        jpBottomBarIMG.setBounds(0,0,WIDTH,100);
        jpBottomBar.add(jpBottomBarIMG);

        jlPlayer0 = new JLabel(new ImageIcon("Assets/Images/Player0.png"));
        jlPlayer1 = new JLabel(new ImageIcon("Assets/Images/Player1.png"));
        jlSalvaGenteP0 = new JLabel(new ImageIcon("Assets/Images/SalvaGente.png"));
        jlSalvaGenteP1 = new JLabel(new ImageIcon("Assets/Images/SalvaGente.png"));

        Model model = new Model();

        Player p0 = new Player(this,0);
        Player p1 = new Player(this,1);

    }

    private void createLayout() {
        jpLeftColumn.setBorder(BorderFactory.createLineBorder(new Color(101,48,25), 10));
        jpRightColumn.setBorder(BorderFactory.createLineBorder(new Color(101,48,25), 10));

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

    public void close(){
        dispose();
    }

}

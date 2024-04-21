import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Game extends JFrame {

    // Costanti
    private final int WIDTH = 1280;
    private final int HEIGHT = 960;

    // Variabili
    private JPanel jpLeftColumn, jpRightColumn, jpTopBar, jpBottomBar;
    private JLabel jlPlayer1, jlPlayer2, jlSalvaGenteP1, jlSalvaGenteP2;

    public JPanel jpOstacoliP1, jpOstacoliP2, jpHeartsP1, jpHeartsP2;

    private Thread Tplayer0;

    Model model;

    public Game() throws IOException {
        initialize();
        createLayout();

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

        jpOstacoliP1 = new JPanel();
        jpOstacoliP2 = new JPanel();
        jpHeartsP1 = new JPanel();
        jpHeartsP2 = new JPanel();

        jlPlayer1 = new JLabel(new ImageIcon("Assets/Images/Player1.png"));
        jlPlayer2 = new JLabel(new ImageIcon("Assets/Images/Player2.png"));
        jlSalvaGenteP1 = new JLabel(new ImageIcon("Assets/Images/SalvaGente.png"));
        jlSalvaGenteP2 = new JLabel(new ImageIcon("Assets/Images/SalvaGente.png"));

    }

    private void createLayout() {
        jpLeftColumn.setBorder(BorderFactory.createLineBorder(new Color(101,48,25), 10));
        jpRightColumn.setBorder(BorderFactory.createLineBorder(new Color(101,48,25), 10));

        jpTopBar.setPreferredSize(new Dimension(WIDTH, 100));
        topBar();
        add(jpTopBar, BorderLayout.NORTH);

        jpBottomBar.setPreferredSize(new Dimension(WIDTH, 100));
        add(jpBottomBar, BorderLayout.SOUTH);

        jpLeftColumn.setPreferredSize(new Dimension(WIDTH/2, HEIGHT - jpTopBar.getHeight()*2));
        LeftColumn();
        add(jpLeftColumn, BorderLayout.WEST);

        jpRightColumn.setPreferredSize(new Dimension(WIDTH/2, HEIGHT - jpTopBar.getHeight()*2));
        RightColumn();
        add(jpRightColumn, BorderLayout.EAST);

        jpOstacoliP1.setLayout(new BoxLayout(jpOstacoliP1,BoxLayout.Y_AXIS));
        jpOstacoliP2.setLayout(new BoxLayout(jpOstacoliP2,BoxLayout.Y_AXIS));
    }

    private void LeftColumn(){
        jlPlayer1.setBounds(320-32,600,64,64);
        jlSalvaGenteP1.setBounds(320-64,600-32,128,128);

        jpOstacoliP1.setBounds(30,20,(WIDTH/2)-40,HEIGHT/2);
        //jpOstacoliP1.setBorder(new LineBorder(Color.YELLOW,10));
        jpOstacoliP1.setOpaque(false);

        jpLeftColumn.add(jpOstacoliP1);
        jpLeftColumn.add(jlPlayer1);
        jpLeftColumn.add(jlSalvaGenteP1);
    }

    private void RightColumn(){
        jlPlayer2.setBounds(320-32,600,64,64);
        jlSalvaGenteP2.setBounds(320-64,600-32,128,128);

        jpOstacoliP2.setBounds(35,20,(WIDTH/2)-40,HEIGHT/2);
        //jpOstacoliP2.setBorder(new LineBorder(Color.YELLOW,10));
        jpOstacoliP2.setOpaque(false);

        jpRightColumn.add(jpOstacoliP2);
        jpRightColumn.add(jlPlayer2);
        jpRightColumn.add(jlSalvaGenteP2);
    }

    private void topBar(){
        jpHeartsP1.setBounds(150,0,300,100);
        jpHeartsP2.setBounds(150 + (WIDTH/2),0,300,100);

        jpHeartsP1.setBorder(new LineBorder(Color.RED, 5));
        jpHeartsP2.setBorder(new LineBorder(Color.RED, 5));

        jpTopBar.add(jpHeartsP1);
        jpTopBar.add(jpHeartsP2);
    }


    public void setModel(Model model){
        this.model = model;
    }

    public void addListener(KeyListener keyListener){
        this.addKeyListener(keyListener);
    }

    public void removeListener(KeyListener keyListener){
        this.removeKeyListener(keyListener);
    }

    public void removeSalvagente(int i){
        if(i == 1){
            jpLeftColumn.remove(2);
        } else {
            jpRightColumn.remove(2);
        }
    }

}

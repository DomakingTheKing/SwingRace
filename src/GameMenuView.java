import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameMenuView extends JFrame implements ViewInterface{

    private JLabel jlTitle;
    private JButton startButton, exitButton;
    private JLayeredPane layeredPane;
    private Player p1, p2;
    private Model model;

    private Clip ost;

    public GameMenuView(Player p1, Player p2, Model model) {
        ost = Model.playOST("BG_Menu_Ost.wav");
        ost.start();

        this.p1 = p1;
        this.p2 = p2;
        this.model = model;

        initialize();
        createLayout();

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setTitle("SwingRace");
    }

    private void initialize() {
        layeredPane = getLayeredPane();

        jlTitle = new JLabel("SWINGRACE", SwingConstants.CENTER);
        jlTitle.setFont(Model.getFont("Filled.ttf", 70));
        jlTitle.setForeground(Color.WHITE);

        startButton = new JButton("Start");
        startButton.setFont(Model.getFont("Filled.ttf", 20));
        startButton.addActionListener(e -> {
            Model.playClip("Select.wav");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }

            GameView gameView = null;
            try {
                gameView = new GameView();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            Controller c1 = new Controller(p1, new int[] {87, 65, 83, 68}); // W - A - S - D
            c1.setModel(model);
            model.c1 = c1;
            Controller c2 = new Controller(p2, new int[] {38, 37, 40, 39}); // SU - SX - GIU - DX
            c2.setModel(model);
            model.c2 = c2;

            gameView.addKeyListener(c1);
            gameView.addKeyListener(c2);

            gameView.setModel(model);
            model.gameView = gameView;

            gameView.refreshMatrice(p1);
            gameView.refreshMatrice(p2);

            ost.close();
            this.dispose();
        });

        exitButton = new JButton("Exit Game");
        exitButton.setFont(Model.getFont("Filled.ttf", 20));
        exitButton.addActionListener(e -> {
            Model.playClip("Select.wav");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }

            System.exit(0);
        });
    }

    private void createLayout() {
        jlTitle.setBounds(0, 50, 800, 100);
        layeredPane.add(jlTitle, JLayeredPane.PALETTE_LAYER);

        startButton.setBounds(300, 250, 200, 100);
        layeredPane.add(startButton, JLayeredPane.PALETTE_LAYER);

        exitButton.setBounds(300, 400, 200, 50);
        layeredPane.add(exitButton, JLayeredPane.PALETTE_LAYER);

        // Set background
        JLabel background = new JLabel(new ImageIcon(IMAGES_PATH + "MainBG.png"));
        background.setBounds(0, 0, 800, 600);
        layeredPane.add(background, JLayeredPane.DEFAULT_LAYER);
    }

}
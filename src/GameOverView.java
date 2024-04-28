import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameOverView extends JFrame implements ViewInterface{

    private JLabel jlGameOver, jlPlayer1, jlPlayer2;
    private JLayeredPane layeredPane;
    private JButton exitButton;

    public GameOverView(){
        initialize();
        createLayout();

        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // In your initialize method
    private void initialize(){
        layeredPane = getLayeredPane();
        jlGameOver = new JLabel(new ImageIcon(IMAGES_PATH + "GameOverBG.png"));
        jlPlayer1 = new JLabel();
        jlPlayer2 = new JLabel();

        exitButton = new JButton("ESCI");
    }

    private void createLayout() {
        jlGameOver.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        layeredPane.add(jlGameOver, JLayeredPane.DEFAULT_LAYER);

        exitButton.setBounds(WINDOW_WIDTH - 215, WINDOW_HEIGHT - 125, 200, 100);
        exitButton.setBackground(Color.WHITE);
        exitButton.addActionListener(e -> dispose());
        layeredPane.add(exitButton, JLayeredPane.PALETTE_LAYER);
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

    // Set the image of the player
    private void setPlayerImage(String playerName, int x, int y, int width, int height){
        JLabel playerLabel = playerName.equals("p1") ? jlPlayer1 : jlPlayer2;
        String imgName = playerName.equals("p1") ? "Player1" : "Player2";
        playerLabel.setIcon(getPlayerImage(imgName + ".png", width, height));
        playerLabel.setBounds(x, y, width, height);
        layeredPane.add(playerLabel, JLayeredPane.PALETTE_LAYER);
    }

    // Set the winner and looser of the game
    public void setWinner(String winner) {
        // Looser
        setPlayerImage(winner.equals("p1") ? "p2" : "p1", 830, 425, 192, 192);
        // Winner
        setPlayerImage(winner, 240, 415, 384, 384);
        // Crown
        setCrown();
    }

    private void setCrown(){
        JLabel jlCrown = new JLabel();
        jlCrown.setIcon(getPlayerImage("Crown.png", 320, 320));
        jlCrown.setBounds(240+32, 415-(320/2)+10, 320, 320);
        layeredPane.add(jlCrown, JLayeredPane.MODAL_LAYER);
    }
}
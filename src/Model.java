import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Model {

    private Player p1;
    private Player p2;
    private boolean gameON = false;
    private int posto = 0;

    private Thread p1t;
    private Thread p2t;

    private Game game;
    private ControllerP1 c1;
    private ControllerP2 c2;

    public void print(Player p) {
        System.out.println(p.getName() + ":");
        for (int i = p.getAttacchiON().size() - 1; i >= 0; i--) {
            int[] barili = p.getAttacchiON().get(i).getBarili();
            if (gameON) {
                barili[p.getPos()] = 2;
            }
            System.out.println(Arrays.toString(barili));
        }
        System.out.println("----------------------------------");
    }

    public void inizMatrice(Player p) {
        for (int i = 0; i < 3; i++) {
            Attacco att = new Attacco();
            Thread thread = new Thread(att);
            thread.start();
            p.getThreadON().add(thread);
            p.getAttacchiON().add(att);
        }
        System.out.println("----------------------------------");
    }

    public void addRiga(Player p) {
        Attacco att = new Attacco();
        Thread thread = new Thread(att);
        thread.start();
        p.getThreadON().add(thread);
        if (p.getAttacchiON().size() == 4) {
            p.getThreadON().getFirst().interrupt();
            p.getThreadON().removeFirst();
            p.getAttacchiON().removeFirst();
        }
        p.getAttacchiON().add(att);
        p.incrementHops();
        checkDanno(p);
        print(p);
    }

    public void showMatrice(Player p) {
        for (int i = p.getAttacchiON().size() - 1; i >= 0; i--) {
            JPanel row = new JPanel();
            row.setLayout(new GridLayout(1, 4));
            //row.setBorder(new LineBorder(Color.YELLOW, 5));
            row.setName("row" + i);
            row.setBounds(10, ((p.getAttacchiON().size() - i) * 10) + 128, 512, 128);
            row.setOpaque(false);
            for (int j = 0; j < 4; j++) {
                JLabel ostacolo = getOstacolo(i, j, p);
                row.add(ostacolo);
            }
            if(p.getName().equals("p1")){
                game.jpOstacoliP1.add(row);
            } else {
                game.jpOstacoliP2.add(row);
            }
        }
    }

    public void showHearts(Player p){

    }

    private JLabel getOstacolo(int i, int j, Player p) {
        JLabel ostacolo = new JLabel();
        //ostacolo.setBorder(new LineBorder(Color.CYAN, 5));
        ostacolo.setName("ostacoloRow" + i + "box" + j);
        ostacolo.setSize(128, 128);
        if (p.getAttacchiON().get(i).getBarili()[j] == 0) {
            ostacolo.setIcon(new ImageIcon("Assets/Images/Croco.png"));
        } else {
            ostacolo.setIcon(new ImageIcon("Assets/Images/Crate.png"));
        }
        ostacolo.setOpaque(false);
        return ostacolo;
    }

    public void checkDanno(Player p) {
        int[] barili = p.getAttacchiON().getFirst().getBarili();
        if (barili[p.getPos()] == 0) {
            danno(p);
        }
    }

    public void danno(Player p) {
        int health = p.getHealth();
        if (health - 1 == 0) {
            stop(p);
        } else {
            p.decreaseHealth();
            System.out.println("danno: " + p.getHealth());
        }
    }

    private void stop(Player p) {
        System.out.println(posto + ". [" + p.getName() + "] | Salti -> " + p.getHops());
        posto--;
        if (p.getName().equals("p1")) {
            p1t.interrupt();
            game.removeListener(c1);
        } else {
            p2t.interrupt();
            game.removeListener(c2);
        }
        if (posto == 0) {
            System.exit(0);
        }
    }

    public void setPlayer1(Player p1) {
        this.p1 = p1;
        p1t = new Thread(p1);
        posto++;
    }

    public void setPlayer2(Player p2) {
        this.p2 = p2;
        p2t = new Thread(p2);
        posto++;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setControllerP1(ControllerP1 c1){
        this.c1 = c1;
    }

    public void setControllerP2(ControllerP2 c2){
        this.c2 = c2;
    }

    public void setGameON() {
        gameON = true;
    }

    public void close() {
        game.dispose();
    }
}

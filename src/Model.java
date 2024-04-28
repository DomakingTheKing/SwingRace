import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Model implements ViewInterface{

    private int posto;

    public GameView gameView;
    public Controller c1, c2;

    public Model(){
        posto = 0;
    }

    public void print(Player p) {
        System.out.println(p.getName() + ":");
        System.out.println(p.getAttacco().print());
        System.out.println("----------------------------------");
    }

    public void checkDanno(Player p) {

        playClip("jump.wav");

        int index = (p.getAttacco().getExpanded()) ? p.getAttacco().getAttacchi().length-2 : p.getAttacco().getAttacchi().length-1;

        int[] barili = p.getAttacco().getAttacchi()[index];

        if (barili[p.getPos()] == 0) {
            danno(p);

            if (p.getAttacco().getExpanded()){
                p.getAttacco().killCroco(index, p.getPos());
            } else{
                p.getAttacco().killCroco(index+1, p.getPos());
            }
        }
    }

    public void danno(Player p) {
        playClip("Damage.wav");

        int health = p.getHealth();
        if (health == 0) {
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
            gameView.removeListener(c1);
            gameView.gameOverScreen(p);
        } else {
            gameView.removeListener(c2);
            gameView.gameOverScreen(p);
        }
        if (posto == 0) {
            close(p);
        }
    }

    public void setNPlayer(int i) {
        posto += i;
    }

    public void close(Player p) {
        gameView.dispose();
        GameOverView gameOverView = new GameOverView();
        gameOverView.setWinner(p.getName());
    }

    public void playClip(String fileName) {
        new Thread(() -> {
            try {
                File audioFile = new File(SOUNDS_PATH + fileName);
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start();
                Thread.sleep(1000);
                clip.close();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
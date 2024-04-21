import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControllerP2 implements KeyListener {

    private Player player;
    private Model model;
    private Game game;
    private boolean started = false;

    public ControllerP2(Player player) {
        this.player = player;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (!started && (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_LEFT ||
                keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_RIGHT)) {
            started = true;
            game.removeSalvagente(2);
            game.repaint();
        }

        if (started) {
            switch (keyCode) {
                case KeyEvent.VK_UP:
                    player.setPos(0);
                    break;
                case KeyEvent.VK_LEFT:
                    player.setPos(1);
                    break;
                case KeyEvent.VK_DOWN:
                    player.setPos(2);
                    break;
                case KeyEvent.VK_RIGHT:
                    player.setPos(3);
                    break;
                default:
                    break;
            }

            model.addRiga(player);
            model.checkDanno(player);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}

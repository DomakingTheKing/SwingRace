import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {

    private final Player player;
    private Model model;

    private boolean started; // Flag per indicare se il gioco è iniziato o meno
    private boolean expanded;

    private final int[] keys; // Array contenente i codici dei tasti validi

    public Controller(Player player, int[] keys) {
        this.player = player;
        this.keys = keys;
        this.started = false;
        this.expanded = false;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (!started && isKeyValid(keyCode)) {
            started = true;

            model.view.removeSalvagente(player);
            model.view.repaint();
        }

        if (started && isKeyValid(keyCode)) {
            int pos = getPositionForKey(keyCode);
            if (pos != -1) {
                player.setPos(pos);

                player.incrementHops();
                model.checkDanno(player);
                player.getAttacco().updateMatrice();

                if (!expanded){
                    player.getAttacco().expandMatrice();
                    expanded = true;
                }

                model.view.refreshMatrice(player);
            } else {
                System.out.println("Invalid key code: " + keyCode);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    // Metodo per verificare se il codice del tasto è valido
    private boolean isKeyValid(int keyCode) {
        for (int key : keys) {
            if (keyCode == key) {
                return true;
            }
        }
        return false;
    }

    // Metodo per ottenere la posizione del tasto nell'array keys
    private int getPositionForKey(int keyCode) {
        for (int i = 0; i < keys.length; i++) {
            if (keyCode == keys[i]) {
                return i;
            }
        }
        return -1;
    }
}

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Controller che gestisce l'input del giocatore
public class Controller implements KeyListener {

    private final Player player; // Giocatore associato al controller
    private Model model; // Modello del gioco

    private boolean started; // Indica se il gioco è iniziato
    private boolean expanded; // Indica se la matrice degli attacchi è stata espansa

    private final int[] keys; // Array contenente i codici dei tasti validi

    // Costruttore del controller
    public Controller(Player player, int[] keys) {
        this.player = player;
        this.keys = keys;
        this.started = false;
        this.expanded = false;
    }

    // Imposta il modello del gioco
    public void setModel(Model model) {
        this.model = model;
    }

    // Gestisce l'evento di pressione di un tasto
    @Override
    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();

        // Se il gioco non è ancora iniziato e il tasto premuto è valido, "avvia" il gioco
        if (!started && isKeyValid(keyCode)) {
            started = true;

            model.gameView.removeSalvagente(player); // Rimuove il salvagente dalla schermata di gioco
            model.gameView.repaint();
        }

        // Se il gioco è iniziato e il tasto premuto è valido
        if (started && isKeyValid(keyCode)) {
            int pos = getPositionForKey(keyCode);
            if (pos != -1) {
                player.setPos(pos); // Imposta la posizione del giocatore in base al tasto premuto

                player.incrementHops(); // Incrementa il numero di salti effettuati dal giocatore
                model.checkDanno(player); // Controlla se il giocatore ha subito danni

                // Se la matrice degli attacchi non è stata espansa, la espande
                if (!expanded){
                    player.getAttacco().expandMatrice();
                    expanded = true;
                }else{
                    player.getAttacco().updateMatrice(); // Altrimenti, aggiorna la matrice degli attacchi
                }

                model.gameView.refreshMatrice(player); // Aggiorna la schermata di gioco con la nuova posizione e matrice
            } else {
                System.out.println("Invalid key code: " + keyCode); // Se il tasto premuto non è valido, stampa un messaggio di errore
            }

        }
    }

    // Gestisce l'evento di pressione di un tasto
    @Override
    public void keyTyped(KeyEvent e) {}

    // Gestisce l'evento di rilascio di un tasto
    @Override
    public void keyReleased(KeyEvent e) {}

    // Verifica se il codice del tasto è valido
    private boolean isKeyValid(int keyCode) {
        for (int key : keys) {
            if (keyCode == key) {
                return true;
            }
        }
        return false;
    }

    // Restituisce la posizione del tasto nell'array keys
    private int getPositionForKey(int keyCode) {
        for (int i = 0; i < keys.length; i++) {
            if (keyCode == keys[i]) {
                return i;
            }
        }
        return -1; // Se il tasto non è stato trovato, restituisce -1
    }

    public Player getPlayer() {
        return player;
    }
}

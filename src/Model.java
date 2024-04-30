import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Model implements ViewInterface{

    private int posto; // Variabile per tenere traccia del posizionamento del giocatore

    public GameView gameView; // Vista del gioco
    public Controller c1, c2; // Controller per i giocatori

    public Model(){
        posto = 0; // Inizializza il posto a 0
    }

    // Metodo per stampare le informazioni del giocatore
    public void print(Player p) {
        System.out.println(p.getName() + ":");
        System.out.println(p.getAttacco().print());
        System.out.println("----------------------------------");
    }

    // Metodo per controllare se il giocatore ha subito danni
    public void checkDanno(Player p) {
        // Riproduce l'effetto sonoro del salto
        playClip("jump.wav");

        // Determina l'indice dell'attacco del giocatore
        int index = (p.getAttacco().getExpanded()) ? p.getAttacco().getAttacchi().length-2 : p.getAttacco().getAttacchi().length-1;

        // Ottiene l'array degli attacchi del giocatore
        int[] barili = p.getAttacco().getAttacchi()[index];

        // Controlla se il giocatore ha colpito un barile
        if (barili[p.getPos()] == 0) {
            danno(p); // Il giocatore subisce danni

            if (p.getAttacco().getExpanded()){
                p.getAttacco().killCroco(index, p.getPos());
            } else{
                p.getAttacco().killCroco(index+1, p.getPos());
            }
        }
    }

    // Metodo per gestire il danno al giocatore
    public void danno(Player p) {
        // Riproduce l'effetto sonoro del danno
        playClip("Damage.wav");
        p.decreaseHealth(); // Decrementa la salute del giocatore

        int health = p.getHealth();
        // Se la salute del giocatore è zero, ferma il gioco e aggiorna la vista
        if (health == 0) {
            stop(p);
            gameView.refreshHearts(p);
        } else {
            System.out.println("danno: " + p.getHealth()); // Altrimenti stampa la salute del giocatore
        }
    }

    // Metodo per fermare un giocatore
    private void stop(Player p) {
        // Stampa il posto del giocatore e altre informazioni
        System.out.println(posto + ". [" + p.getName() + "] | Salti -> " + p.getHops());
        posto--;

        // Rimuove il controller del giocatore e visualizza la schermata di fine gioco
        if (p.getName().equals("p1")) {
            gameView.removeListener(c1);
            gameView.gameOverScreen(p);
        } else {
            gameView.removeListener(c2);
            gameView.gameOverScreen(p);
        }

        // Chiude il gioco se il posto è 0
        if (posto == 0) {
            close(p);
        }
    }

    // Metodo per impostare il numero di giocatori
    public void setNPlayer(int i) {
        posto += i; // Incrementa il posto
    }

    // Metodo per chiudere il gioco
    public void close(Player p) {
        gameView.getOST().close(); // Chiude la musica di sottofondo
        playClip("Win.wav"); // Riproduce l'effetto sonoro della vittoria
        gameView.dispose(); // Chiude la vista del gioco
        GameOverView gameOverView = new GameOverView(c1.getPlayer(), c2.getPlayer()); // Visualizza la schermata di fine gioco
        gameOverView.setWinner(p.getName()); // Imposta il vincitore
    }

    // Metodo per riprodurre un effetto sonoro
    public static void playClip(String fileName) {
        new Thread(() -> {
            try {
                File audioFile = new File(SOUNDS_PATH + fileName); // Carica il file audio
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile); // Ottiene lo stream audio
                Clip clip = AudioSystem.getClip(); // Crea un oggetto Clip
                clip.open(audioStream); // Apre lo stream audio
                clip.start(); // Avvia la riproduzione dell'effetto sonoro
                Thread.sleep(clip.getMicrosecondLength() / 1000); // Attende che l'effetto sonoro sia completato
                clip.stop(); // Ferma la riproduzione dell'effetto sonoro
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
                e.printStackTrace(); // Gestisce le eccezioni
            }
        }).start(); // Avvia il thread per riprodurre l'effetto sonoro
    }
    
    // Metodo per caricare un tipo di carattere da file
    public static Font getFont(String fontName, int fontSize){
        try {
            // Crea un nuovo tipo di carattere
            return Font.createFont(Font.TRUETYPE_FONT, new File(FONTS_PATH + fontName)).deriveFont(Font.PLAIN, fontSize);
        } catch (FontFormatException | IOException e) {
            // Gestisce le eccezioni se il caricamento del tipo di carattere fallisce
            System.err.println("Failed to load font: " + fontName);
            return null; // Restituisce null in caso di errore
        }
    }

    // Metodo per riprodurre un file audio come musica di sottofondo
    public static Clip playOST(String fileName) {
        Clip ost = null;
        try {
            // Carica il file audio dalla directory specificata
            File audioFile = new File(MUSIC_PATH + fileName);
            // Ottiene lo stream audio dal file caricato
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            // Crea un oggetto Clip per la riproduzione audio
            ost = AudioSystem.getClip();
            // Apre lo stream audio con l'oggetto Clip
            ost.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            // Gestisce le eccezioni se la riproduzione del file audio fallisce
            e.printStackTrace();
        }

        return ost; // Restituisce l'oggetto Clip per il controllo della riproduzione
    }
}

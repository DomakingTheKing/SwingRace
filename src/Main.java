import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Crea un nuovo modello per il gioco
        Model model = new Model();

        // Crea due giocatori
        Player p1 = new Player(1);
        Player p2 = new Player(2);

        // Imposta il numero di giocatori nel modello
        model.setNPlayer(2);

        // Crea i controller per i due giocatori
        Controller c1 = new Controller(p1, new int[] {87, 65, 83, 68}); // W - A - S - D
        c1.setModel(model);
        Controller c2 = new Controller(p2, new int[] {38, 37, 40, 39}); // SU - SX - GIU - DX
        c2.setModel(model);

        // Stampa le matrici contenenti gli ostacoli dei giocatori
        model.print(p1);
        model.print(p2);

        // Imposta i controller nel modello
        model.c1 = c1;
        model.c2 = c2;

        // Crea e mostra una nuova finestra per il menu del gioco
        GameMenuView gameMenuView = new GameMenuView(p1, p2, model);
    }
}

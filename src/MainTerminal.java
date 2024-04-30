import java.io.IOException;
import java.util.Scanner;

public class MainTerminal {
    public static void main(String[] args) throws IOException, InterruptedException {

        // Crea un nuovo modello per il gioco
        Model model = new Model();

        // Crea un nuovo giocatore
        Player p1 = new Player(1);

        // Imposta il numero di giocatori nel modello
        model.setNPlayer(1);

        // Stampa le informazioni sul giocatore
        model.print(p1);

        // Crea un oggetto Scanner per leggere l'input dall'utente
        Scanner scn = new Scanner(System.in);

        // Loop infinito per consentire al giocatore di inserire mosse
        while(true){
            System.out.println("Inserisci mossa: "); // Richiede all'utente di inserire una mossa
            int i = scn.nextInt(); // Legge l'input dell'utente
            p1.setPos(i); // Imposta la posizione del giocatore
            p1.incrementHops(); // Incrementa il conteggio dei salti del giocatore
            model.checkDanno(p1); // Controlla se il giocatore ha subito danni
            p1.getAttacco().updateMatrice(); // Aggiorna la matrice degli attacchi del giocatore
            model.print(p1);
       }
    }
}

import java.awt.*;
import java.util.ArrayList;

// Rappresenta la classe che gestisce gli attacchi nel gioco
public class Attacco {
    private int[][] attacchi; // Matrice che rappresenta gli attacchi (scatola = 1, coccodrillo = 0)
    private boolean expanded; // Indica se la matrice degli attacchi è stata espansa
    private ArrayList<Point> killedObstacles = new ArrayList<>(); // Lista degli ostacoli eliminati

    // Costruttore della classe Attacco
    public Attacco() {
        attacchi = new int[3][4]; // Inizializza la matrice degli attacchi
        expanded = false; // Inizializza la variabile expanded a false
        generateAttacchi(); // Genera gli attacchi iniziali
    }

    // Genera gli attacchi iniziali
    private void generateAttacchi() {
        for (int i = 0; i < attacchi.length; i++) {
            generateRow(i); // Genera una riga di attacchi
        }
    }

    // Genera una riga di attacchi
    private void generateRow(int i) {
        boolean scatolaCheck = false;

        for (int j = 0; j < attacchi[i].length; j++) {
            int rand = (int) (Math.random()*2);
            if(rand == 1){
                scatolaCheck = true;
            }
            attacchi[i][j] = rand;
        }

        // Se non è presente alcuna scatola nella riga, ne viene generata una casualmente
        if(!scatolaCheck){
            int rand = (int) (Math.random()*4);
            attacchi[i][rand] = 1;
        }
    }

    // Espande la matrice degli attacchi aggiungendo una riga in cima
    public void expandMatrice() {
        expanded = true;
        int[][] newAttacchi = new int[4][4];
        // Sposta le righe verso il basso
        for (int i = 0; i < attacchi.length; i++) {
            System.arraycopy(attacchi[i], 0, newAttacchi[i + 1], 0, attacchi[i].length);
        }

        attacchi = newAttacchi;

        // Genera una nuova riga in cima
        generateRow(0);

    }

    // Aggiorna la matrice degli attacchi spostando le righe verso il basso
    public void updateMatrice() {
        for (int i = attacchi.length-2; i >= 0 ; i--) {
            System.arraycopy(attacchi[i], 0, attacchi[i + 1], 0, attacchi[i].length);
        }

        generateRow(0);

        // Aggiorna le coordinate degli ostacoli eliminati
        for (Point point : killedObstacles) {
            point.x++;
        }

        // Rimuove gli ostacoli eliminati che escono dal bordo inferiore della matrice
        killedObstacles.removeIf(point -> point.x >= attacchi.length);
    }

    // Restituisce la matrice degli attacchi
    public int[][] getAttacchi(){
        return attacchi;
    }

    // Restituisce true se la matrice è stata espansa, altrimenti false
    public boolean getExpanded(){
        return expanded;
    }

    // Restituisce la lista degli ostacoli eliminati
    public ArrayList<Point> getKilledObstacles() {
        return killedObstacles;
    }

    // Aggiunge un'istanza di ostacolo eliminato alla lista
    public void killCroco(int x, int y) {
        killedObstacles.add(new Point(x, y));
    }

    // Restituisce una rappresentazione in stringa della matrice degli attacchi
    public String print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < attacchi.length; i++) {
            for (int j = 0; j < attacchi[i].length; j++) {
                sb.append(attacchi[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
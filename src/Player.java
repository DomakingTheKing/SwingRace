public class Player{

    // Attributi del giocatore
    private final String name; // Nome del giocatore
    private int health; // Salute del giocatore
    private int pos; // Posizione del giocatore sulla griglia
    private int hops; // Numero di salti effettuati dal giocatore
    private Attacco attacco; // Tipo di attacco del giocatore

    // Costruttore della classe Player
    public Player(int player){
        // Imposta i valori iniziali della salute, della posizione e dei salti
        health = 3;
        pos = -1;
        hops = 0;

        // Assegna un nome al giocatore in base al suo numero (1 per il giocatore 1, 2 per il giocatore 2)
        if(player == 1){
            this.name = "p1";
        } else {
            this.name = "p2";
        }

        // Inizializza il tipo di attacco del giocatore
        this.attacco = new Attacco();
    }

    // Metodo per impostare la posizione del giocatore sulla griglia
    public void setPos(int pos){
        this.pos = pos;
    }

    // Metodo per ottenere la posizione del giocatore sulla griglia
    public int getPos(){
        return pos;
    }

    // Metodo per ottenere la salute attuale del giocatore
    public int getHealth(){
        return health;
    }

    // Metodo per ottenere il nome del giocatore
    public String getName(){
        return name;
    }

    // Metodo per decrementare la salute del giocatore
    public void decreaseHealth(){
        health--;
    }

    // Metodo per ottenere il numero di salti effettuati dal giocatore
    public int getHops() {
        return hops;
    }

    // Metodo per incrementare il numero di salti effettuati dal giocatore
    public void incrementHops() {
        hops++;
    }

    // Metodo per ottenere il tipo di attacco del giocatore
    public Attacco getAttacco(){
        return attacco;
    }

}
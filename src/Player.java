public class Player{

    private final String name;
    private int health;
    private int pos;
    private int hops;
    private Attacco attacco;

    public Player(int player){
        health = 3;
        pos = -1;
        hops = 0;

        if(player == 1){
            this.name = "p1";
        } else {
            this.name = "p2";
        }

        this.attacco = new Attacco();
    }

    public void setPos(int pos){
        this.pos = pos;
    }

    public int getPos(){
        return pos;
    }

    public int getHealth(){
        return health;
    }

    public String getName(){
        return name;
    }

    public void decreaseHealth(){
        health--;
    }

    public int getHops() {
        return hops;
    }

    public void incrementHops() {
        hops++;
    }

    public Attacco getAttacco(){
        return attacco;
    }

}

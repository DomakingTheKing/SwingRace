import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player implements Runnable {

    private final String name;
    private int health = 3;
    private int pos = 0;
    private int hops = 0;
    private List<Attacco> attacchiON;
    private List<Thread> threadON;
    private ControllerP1 c1;
    private ControllerP2 c2;
    private Model model;

    public Player(JFrame JFMain, int player){
        ControllerP1 keyListener0 = new ControllerP1(this);
        JFMain.addKeyListener(keyListener0);
        model.inizMatrice(this);
        this.name = "p1";

        threadON = Collections.synchronizedList(new ArrayList<Thread>());
        attacchiON = Collections.synchronizedList(new ArrayList<Attacco>());
    }

    public Player(int player){
        if(player == 1){
            this.name = "p1";
        } else {
            this.name = "p2";
        }

        threadON = Collections.synchronizedList(new ArrayList<Thread>());
        attacchiON = Collections.synchronizedList(new ArrayList<Attacco>());
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

    public void setModel(Model model){
        this.model = model;
    }

    public void startP(){
        model.inizMatrice(this);
    }


    public List<Attacco> getAttacchiON(){
        return attacchiON;
    }

    public List<Thread> getThreadON(){
        return threadON;
    }


    @Override
    public void run() {

    }

}

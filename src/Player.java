import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player{

    private final String name;
    private int health = 3;
    private int pos = 0;
    private int hops = 0;
    private Model model;
    private Attacco attacco;

    public Player(int player, Model model){
        if(player == 1){
            this.name = "p1";
        } else {
            this.name = "p2";
        }

        this.model = model;
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

import javax.swing.*;

public class Player{

    private final String name;
    private JLabel jlPlayer, jlSalvaGente;
    private ControllerP0 keyListener0;
    private ControllerP1 keyListener1;
    private int player;
    private int health = 10;
    private int pos = 0;

    public Player(JFrame JFMain, int player){
        if(player == 0){
            keyListener0 = new ControllerP0(this);
            JFMain.addKeyListener(keyListener0);
            Model.inizMatricep1();
            this.name = "p1";
        } else {
            keyListener1 = new ControllerP1(this);
            JFMain.addKeyListener(keyListener1);
            Model.inizMatricep2();
            this.name = "p2";
        }
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


}

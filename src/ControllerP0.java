import jdk.swing.interop.SwingInterOpUtils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControllerP0 implements KeyListener {

    private boolean gameRunning = false;

    Player player;

    public ControllerP0(Player p){
        this.player = p;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case(KeyEvent.VK_W):
                player.setPos(0);
                Model.addRigap1();
                Model.checkDannop1(player);
                Model.print(player);
                break;

            case(KeyEvent.VK_A):
                player.setPos(1);
                Model.addRigap1();
                Model.checkDannop1(player);
                Model.print(player);
                break;

            case(KeyEvent.VK_S):
                player.setPos(2);
                Model.addRigap1();
                Model.checkDannop1(player);
                Model.print(player);
                break;

            case(KeyEvent.VK_D):
                player.setPos(3);
                Model.addRigap1();
                Model.checkDannop1(player);
                Model.print(player);
                break;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}

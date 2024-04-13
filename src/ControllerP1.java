import jdk.swing.interop.SwingInterOpUtils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControllerP1 implements KeyListener {

    private boolean gameRunning = false;

    Player player;

    public ControllerP1(Player p){
        this.player = p;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case(KeyEvent.VK_UP):
                player.setPos(0);
                Model.addRigap2();
                Model.checkDannop2(player);
                Model.print(player);
                break;

            case(KeyEvent.VK_LEFT):
                player.setPos(1);
                Model.addRigap2();
                Model.checkDannop2(player);
                Model.print(player);
                break;

            case(KeyEvent.VK_DOWN):
                player.setPos(2);
                Model.addRigap2();
                Model.checkDannop2(player);
                Model.print(player);
                break;

            case(KeyEvent.VK_RIGHT):
                player.setPos(3);
                Model.addRigap2();
                Model.checkDannop2(player);
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

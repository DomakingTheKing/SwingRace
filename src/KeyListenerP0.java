import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListenerP0 implements KeyListener {

    private boolean partito = false;
    private static JLabel jlPlayer;
    private JLabel jlSalvaGente;

    public KeyListenerP0(JLabel jlPlayer, JLabel jlSalvaGente){
        this.jlPlayer = jlPlayer;
        this.jlSalvaGente = jlSalvaGente;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(!partito){
            if(e.getKeyCode() == KeyEvent.VK_W){
                System.out.println("Partito!");
                jlSalvaGente.isOpaque();
                partito = true;
            } else {
                System.out.println("Non partito!");
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_W){
            aggiornaMovimento();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void aggiornaMovimento(){
        System.out.println("Aggiorno");
        jlPlayer.setLocation(jlPlayer.getX(),jlPlayer.getY()-10);
    }
}

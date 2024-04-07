import javax.swing.*;

public class Player implements Runnable{

    private JLabel jlPlayer, jlSalvaGente;
    private KeyListenerP0 keyListener;

    public Player(JLabel jlPlayer, JLabel jlSalvaGente){
        this.jlPlayer = jlPlayer;
        this.jlSalvaGente = jlSalvaGente;

        keyListener = new KeyListenerP0(jlPlayer, jlSalvaGente);
        jlPlayer.addKeyListener(keyListener);
    }

    @Override
    public void run() {
    }


}

import java.io.IOException;
import java.util.Scanner;

public class MainTerminal {
    public static void main(String[] args) throws IOException, InterruptedException {
        Player p1 = new Player(1);

        Model model = new Model();
        p1.setModel(model);

        model.setPlayer1(p1);

        p1.startP();

        ControllerP1 c1 = new ControllerP1(p1);
        c1.setModel(model);

        model.print(p1);
        model.setGameON();

        Scanner scn = new Scanner(System.in);

        while(true){
            System.out.println("Insersci mossa: ");
            int i = scn.nextInt();
            p1.setPos(i);
            model.addRiga(p1);
       }

    }
}
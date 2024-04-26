import java.io.IOException;
import java.util.Scanner;

public class MainTerminal {
    public static void main(String[] args) throws IOException, InterruptedException {
        Model model = new Model();

        Player p1 = new Player(1);

        model.setNPlayer(1);

        //model.refreshMatrice(p1);

        model.print(p1);

        Scanner scn = new Scanner(System.in);

        while(true){
            System.out.println("Insersci mossa: ");
            int i = scn.nextInt();
            p1.setPos(i);
            p1.incrementHops();
            //model.refreshMatrice(p1);
            model.checkDanno(p1);
       }

    }
}
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Model model = new Model();

        Player p1 = new Player(1);
        Player p2 = new Player(2);

        model.setNPlayer(2);

        Controller c1 = new Controller(p1, new int[] {87, 65, 83, 68}); // W - A - S - D
        c1.setModel(model);
        Controller c2 = new Controller(p2, new int[] {38, 37, 40, 39}); // SU - SX - GIU - DX
        c2.setModel(model);

        model.print(p1);
        model.print(p2);

        model.c1 = c1;
        model.c2 = c2;

        View view = new View();

        view.addKeyListener(c1);
        view.addKeyListener(c2);

        view.setModel(model);
        model.view = view;

        view.refreshMatrice(p1);
        view.refreshMatrice(p2);
    }
}
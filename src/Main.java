import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Player p1 = new Player(1);
        Player p2 = new Player(2);

        Model model = new Model();
        p1.setModel(model);
        p2.setModel(model);

        model.setPlayer1(p1);
        model.setPlayer2(p2);

        p1.startP();
        p2.startP();

        ControllerP1 c1 = new ControllerP1(p1);
        c1.setModel(model);
        ControllerP2 c2 = new ControllerP2(p2);
        c2.setModel(model);

        model.print(p1);
        model.print(p2);
        model.setGameON();

        model.setControllerP1(c1);
        model.setControllerP2(c2);

        Game game = new Game();

        c1.setGame(game);
        c2.setGame(game);

        game.addKeyListener(c1);
        game.addKeyListener(c2);

        game.setModel(model);
        model.setGame(game);

        model.showMatrice(p1);
        model.showMatrice(p2);
    }
}
import java.awt.*;
import java.util.ArrayList;

public class Attacco {
    private int[][] attacchi; // scatola = 1, coccodrillo = 0
    private boolean expanded;
    private ArrayList<Point> killedObstacles = new ArrayList<>();


    public Attacco() {
        attacchi = new int[3][4];
        expanded = false;
        generateAttacchi();
    }

    private void generateAttacchi() {
        for (int i = 0; i < attacchi.length; i++) {
            generateRow(i);
        }
    }

    private void generateRow(int i) {
        boolean scatolaCheck = false;

        for (int j = 0; j < attacchi[i].length; j++) {
            int rand = (int) (Math.random()*2);
            if(rand == 1){
                scatolaCheck = true;
            }
            attacchi[i][j] = rand;
        }

        if(!scatolaCheck){
            int rand = (int) (Math.random()*4);
            attacchi[i][rand] = 1;
        }
    }

    public void expandMatrice() {
        expanded = true;
        int[][] newAttacchi = new int[4][4];

        // Shift rows down
        for (int i = 0; i < attacchi.length; i++) {
            System.arraycopy(attacchi[i], 0, newAttacchi[i + 1], 0, attacchi[i].length);
        }

        // Generate new row at the top
        generateRow(0);

        // Update attacchi
        attacchi = newAttacchi;
    }

    public void updateMatrice() {
        int[][] newAttacchi = new int[4][4];

        // Shift rows down
        for (int i = 0; i < attacchi.length; i++) {
            System.arraycopy(attacchi[i], 0, newAttacchi[i + 1], 0, attacchi[i].length);
        }

        // Generate new row at the top
        generateRow(0);

        // Update the original matrix
        attacchi = newAttacchi;

        for (Point point : killedObstacles) {
            if (point.x < attacchi.length - 1) {
                point.x++;
            }
        }

        killedObstacles.removeIf(point -> point.x >= attacchi.length);
    }

    public int[][] getAttacchi(){
        return attacchi;
    }

    public boolean getExpanded(){
        return expanded;
    }

    public ArrayList<Point> getKilledObstacles() {
        return killedObstacles;
    }

    public void killCroco(int x, int y) {
        killedObstacles.add(new Point(x, y));
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < attacchi.length; i++) {
            for (int j = 0; j < attacchi[i].length; j++) {
                sb.append(attacchi[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}


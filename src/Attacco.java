public class Attacco {
    private int[][] attacchi; // scatola = 1, coccodrillo = 0
    private boolean expanded;

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
        for (int i = 1; i < newAttacchi.length; i++) {
            newAttacchi[i] = attacchi[i - 1];
        }

        // Generate new row
        generateRow(0);

        // Update attacchi
        attacchi = newAttacchi;
    }

    public void updateMatrice() {
        int[][] newAttacchi = new int[4][4];

        // Shift rows down
        if(!expanded){
            for (int i = 1; i <= attacchi.length; i++) {
                newAttacchi[i] = attacchi[i - 1];
            }
        } else {
            for (int i = 1; i < attacchi.length; i++) {
                newAttacchi[i] = attacchi[i - 1];
            }
        }


        // Generate new row
        boolean scatolaCheck = false;
        for (int j = 0; j < 4; j++) {
            int randOst = (int) (Math.random()*2);
            int randPos = (int) (Math.random()*4);
            if(randOst == 1){
                scatolaCheck = true;
            }
            newAttacchi[0][randPos] = randOst;
        }

        if(!scatolaCheck){
            int rand = (int) (Math.random()*4);
            newAttacchi[0][rand] = 1;
        }

        // Update the original matrix
        attacchi = newAttacchi;

        System.out.println(print());
    }

    public int[][] getAttacchi(){
        return attacchi;
    }

    public boolean getExpanded(){
        return expanded;
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


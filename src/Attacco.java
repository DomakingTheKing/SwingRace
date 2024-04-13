public class Attacco implements Runnable{

    private int[] barili; // scatola = 1, coccodrillo = 0

    public Attacco(){
        barili = new int[4];
    }

    @Override
    public void run() {
        boolean scatolaCheck = false;

        for (int i = 0; i < barili.length; i++) {
            int rand = (int) (Math.random()*2);
            if(rand == 1){
                scatolaCheck = true;
            }
            barili[i] = rand;
        }

        if(!scatolaCheck){
            int rand = (int) (Math.random()*4);
            barili[rand] = 1;
        }

    }

    public int[] getBarili(){
        return barili;
    }
}

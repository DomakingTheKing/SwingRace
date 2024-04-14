import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class Model {

    private static ArrayList<Attacco> attacchiONp1;
    private static ArrayList<Attacco> attacchiONp2;
    private static ArrayList<Thread> threadONp1;
    private static ArrayList<Thread> threadONp2;

    public Model(){
        attacchiONp1 = new ArrayList<Attacco>();
        attacchiONp2 = new ArrayList<Attacco>();
        threadONp1 = new ArrayList<Thread>();
        threadONp2 = new ArrayList<Thread>();
    }

    public static void print(Player p) {

        System.out.println(p.getName() + ":");

        if (p.getName().equals("p1")) {
            for (int i = attacchiONp1.size() - 1; i > -1; i--) {
                if (i == 0) {
                    int[] barili = attacchiONp1.get(i).getBarili();
                    barili[p.getPos()] = 2;
                    System.out.println(Arrays.toString(barili));
                } else {
                    System.out.println(Arrays.toString(attacchiONp1.get(i).getBarili()));
                }
            }

            System.out.println("----------------------------------");
        } else {
            for (int i = attacchiONp2.size() - 1; i > -1; i--) {
                if (i == 0) {
                    int[] barili = attacchiONp2.get(i).getBarili();
                    barili[p.getPos()] = 2;
                    System.out.println(Arrays.toString(barili));
                } else {
                    System.out.println(Arrays.toString(attacchiONp2.get(i).getBarili()));
                }
            }

            System.out.println("----------------------------------");
        }
    }

    public static void inizMatricep1(){
        for (int i = 0; i < 3; i++) {
            Attacco att = new Attacco();
            Thread thread = new Thread(att);
            thread.start();
            threadONp1.add(thread);
            attacchiONp1.add(att);
            System.out.println(Arrays.toString(attacchiONp1.get(0).getBarili()));
        }
        System.out.println("----------------------------------");
    }

    public static void inizMatricep2(){
        for (int i = 0; i < 3; i++) {
            Attacco att = new Attacco();
            Thread thread = new Thread(att);
            thread.start();
            threadONp2.add(thread);
            attacchiONp2.add(att);
            System.out.println(Arrays.toString(attacchiONp2.get(0).getBarili()));
        }
        System.out.println("----------------------------------");
    }

    public static void addRigap1(){
        Attacco att = new Attacco();
        Thread thread = new Thread(att);
        thread.start();
        threadONp1.add(thread);

        if(attacchiONp1.size() == 4){
            threadONp1.get(0).interrupt();
            threadONp1.remove(0);
            attacchiONp1.remove(0);
        }

        attacchiONp1.add(att);

    }

    public static void addRigap2(){
        Attacco att = new Attacco();
        Thread thread = new Thread(att);
        thread.start();
        threadONp2.add(thread);

        if(attacchiONp2.size() == 4){
            threadONp2.get(0).interrupt();
            threadONp2.remove(0);
            attacchiONp2.remove(0);
        }

        attacchiONp2.add(att);

    }

    public static void checkDannop1(Player p){
        int[] barili = attacchiONp1.get(0).getBarili();
        if(barili[p.getPos()] == 0){
            danno(p);
        }
    }

    public static void checkDannop2(Player p){
        int[] barili = attacchiONp2.get(0).getBarili();
        if(barili[p.getPos()] == 0){
            danno(p);
        }
    }

    public static void danno(Player p){
        int health = p.getHealth();

        if(health == 1){
            stop(p);
        } else {
            p.decreaseHealth();
            System.out.println("danno: " + p.getHealth());
        }
    }

    private static void stop(Player p) {
        System.out.println(p.getName() + " Hai perso :(");
        System.exit(0);
    }

}



public class Model {

    private int posto;

    public View view;
    public Controller c1, c2;

    public Model(){
        posto = 0;
    }

    public void print(Player p) {
        System.out.println(p.getName() + ":");
        System.out.println(p.getAttacco().print());
        System.out.println("----------------------------------");
    }

    public void checkDanno(Player p) {
        int index = (p.getAttacco().getExpanded()) ? p.getAttacco().getAttacchi().length-2 : p.getAttacco().getAttacchi().length-1;
        int[] barili = p.getAttacco().getAttacchi()[index];
        if (barili[p.getPos()] == 0) {
            danno(p);
            p.getAttacco().killCroco(index, p.getPos());
        }
    }

    public void danno(Player p) {
        int health = p.getHealth();
        if (health == 0) {
            stop(p);
        } else {
            p.decreaseHealth();
            System.out.println("danno: " + p.getHealth());
        }
    }

    private void stop(Player p) {
        System.out.println(posto + ". [" + p.getName() + "] | Salti -> " + p.getHops());
        posto--;
        if (p.getName().equals("p1")) {
            view.removeListener(c1);
        } else {
            view.removeListener(c2);
        }
        if (posto == 0) {
            close();
        }
    }

    public void setNPlayer(int i) {
        posto += i;
    }

    public void close() {
        view.dispose();
    }
}
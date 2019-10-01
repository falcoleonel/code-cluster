public class hilo extends Thread {
    public hilo(String str) {
        super(str);
    }

    public void run() {
        for (int i = 0; i < 10; i++)
            System.out.println(i + " " + getName());
        System.out.println("Termina thread" + getName());
    }

    public static void main(String[] args) {
        new hilo("Pepe").start();
        new hilo("Juan").start();
        System.out.println("Termina thread main");
    }
}

public class hiloPrioridad extends Thread {
    public hiloPrioridad(String str, int prioridad) {
        super(str);
        setPriority(prioridad);
    }

    public void run() {
        for (int i = 0; i < 10; i++)
            System.out.println(i + " " + getName());
        System.out.println("Termina thread " + getName());
    }

    public static void main(String[] args) {
        new hiloPrioridad("Primero", 9).start();
        new hiloPrioridad("Segundo", 2).start();
        System.out.println("Termina thread main");
    }
}

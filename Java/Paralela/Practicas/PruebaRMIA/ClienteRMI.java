import java.rmi.Naming;

public class ClienteRMI {

    public static void main(String[] args) {
        
        try {

           StevieAut x = (StevieAut)Naming.lookup("//" + args[0] + ":1234/StevieT");

            System.out.println("Conectado");
            for(int i = 0; i < x.getSweeps(); i++)
                x.sweepPick();
            System.out.println("Ya");

        } catch(Exception ex) { ex.printStackTrace(); }
    }
}


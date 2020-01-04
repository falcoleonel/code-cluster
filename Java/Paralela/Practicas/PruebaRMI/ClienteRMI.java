import java.rmi.Naming;

public class ClienteRMI {

    public static void main(String[] args) {
        
        try {

           // StevieT fer = (StevieT)Naming.lookup("//" + args[0] + ":1234/StevieServer");
           StevieT fer = (StevieT)Naming.lookup("//" + args[0] + ":1234/PruebaRMI");

            System.out.println("Conectado al Fer");
            for(int i = 0; i < fer.getSweeps(); i++)
                fer.sweepPick();
            System.out.println("Ya");

        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
}


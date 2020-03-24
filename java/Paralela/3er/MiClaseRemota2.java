import java.rmi.*;
import java.rmi.activation.*;
import java.util.Properties;
import java.net.InetAddress;

public class MiClaseRemota2 extends Activatable implements MiInterfazRemota {
    public MiClaseRemota2(ActivationID a, MarshalledObject m) throws RemoteException {
        super(a, 0);
    }

    public void miMetodo1() throws RemoteException {
        System.out.println("Estoy en miMetodo1()");// Codigo
    }

    public int miMetodo2() throws RemoteException {
        return 5;// Codigo
    }

    public static void main(String[] args) throws Exception {
        try {
            // Se establece el archivo de politica de seguridad
            Properties p = new Properties();
            p.put("java.security.policy", "policy");

            // Necesitamos un grupo de activacion para activar objetos
            // remotos y los puedan acceder los clientes
            ActivationGroupDesc.CommandEnvironment ace = null;
            ActivationGroupDesc ejemplo = new ActivationGroupDesc(p, ace);
            // Se registra el grupo creado con el ActivationGroupDesc,
            // y se obtiene el identificador del registro
            ActivationGroupID agi = ActivationGroup.getSystem().registerGroup(ejemplo);
            // Objeto para indicar datos de inicializacion,
            // si se requieren
            MarshalledObject m = null;
            // Descripcion y registro del objeto en el demonio rmid
            ActivationDesc desc = new ActivationDesc(agi, "MiClaseRemota2",
                    "file://C:\\Users\\danvc\\Code-Cluster\\Java\\Paralela\\3er", m);
            MiInterfazRemota mir = (MiInterfazRemota) Activatable.register(desc);
            Naming.rebind("//" + "192.168.43.143"/* InetAddress.getLocalHost().getHostAddress() */ + ":" + args[0]
                    + "/PruebaRMI", mir);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}

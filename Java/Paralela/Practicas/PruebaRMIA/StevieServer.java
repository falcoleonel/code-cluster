import java.rmi.*;
import java.rmi.activation.*;
import java.util.Properties;
import java.net.InetAddress;

public class StevieServer extends Activatable implements StevieAut {
	public StevieServer(ActivationID a, MarshalledObject m) throws RemoteException {
		super(a, 0);
	}

	public int getSweeps() throws RemoteException {
		return 5;
	}

	public void sweepPick() throws RemoteException {
		System.out.println("Error ):");
	}

	public static void main(String[] args) throws Exception {
		try {
			Properties p = new Properties();
			p.put("java.security.policy", "policy");

			System.out.println(InetAddress.getLocalHost().getHostAddress());

			ActivationGroupDesc.CommandEnvironment ace = null;
			ActivationGroupDesc example = new ActivationGroupDesc(p, ace);

			ActivationGroupID agi = ActivationGroup.getSystem().registerGroup(example);

			MarshalledObject m = null;

			ActivationDesc desc = new ActivationDesc(agi, "StevieServer", "file:///home/samuel/Documents/Proyectos/Java/PriemaRMIA/", m);
			StevieAut s = (StevieAut) Activatable.register(desc);

			Naming.rebind("//" + "172.20.10.10" + ":1234/StevieT", s);
			System.out.println("Encendido");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

public class Subcadenas {

	public static void main(String args[]) {

		if (args.length == 0) {
			System.out.println("Error");
			return;
		}

		for (int j = 0; j < args.length; j++) {
			String cadenaPrincipal = args[j];

			for (int i = 0; i < cadenaPrincipal.length(); i++) {

				System.out.println(cadenaPrincipal.substring(i));
			}

			for (int k = 0; k < cadenaPrincipal.length(); k++) {

				System.out.println(cadenaPrincipal.substring(0, cadenaPrincipal.length() - k));
			}
		}
	}

}
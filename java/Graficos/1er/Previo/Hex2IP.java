
public class Hex2IP {
    public static void main(String args[]) {
        if (args[0].equalsIgnoreCase("Hex") && args[1].matches("^\\d{1,3}+\\.+\\d{1,3}+\\.+\\d{1,3}+\\.+\\d{1,3}$")) {
            System.out.println("Vamos a convertir la siguiente cadena de IP a Hexadecimal:  " + args[1] + "\n");
            String[] parts = args[1].split("\\.");
            String result = "";

            for (String part : parts) {
                int conv = Integer.parseInt(part);
                if (part == parts[3])
                    result += Integer.toHexString(conv);
                else
                    result += Integer.toHexString(conv) + "-";
            }
            System.out.println(" Convertido a Hexadecimal:  " + result + "\n");

        } else if (args[0].equalsIgnoreCase("IP") && args[1]
                .matches("^[0-9A-Fa-f]{1,2}+\\-+[0-9A-Fa-f]{1,2}+\\-+[0-9A-Fa-f]{1,2}+\\-+[0-9A-Fa-f]{1,2}$")) {
            System.out.println("Vamos a convertir la siguiente cadena de Hexadecimal a IP: " + args[1] + "\n");
            String[] parts = args[1].split("-");
            String result = "";

            for (String part : parts) {
                Integer conv = Integer.parseInt(part, 16);
                if (part == parts[3])
                    result += conv.toString();
                else
                    result += conv.toString() + ".";
            }
            System.out.println(" Convertido a direccion IP:  " + result + "\n");

        } else
            System.out.println(" Entrada no valida");

    }

}

public class Hex2IPconvertidora {

    public static void main(String args[]) {
        Hex2IPconvertidora converter = new Hex2IPconvertidora();

        if (args[0].contains("ip")) {

            if (args[1].matches("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$")) {
                System.out.println(converter.convertToHex(args[1]));
            } else {
                System.out.println("Error");
            }
        }

        if (args[0].contains("hex")) {

            if (args[1].matches("-?[0-9a-fA-F]+")) {
                System.out.println(converter.convertToIP(args[1]));
            } else {
                System.out.println("Error");
            }
        }
    }

    private String convertToIP(String hexValue) {

        // System.out.println("Convert to ip " + hexValue);
        String ip = "";

        for (int i = 0; i < hexValue.length(); i = i + 2) {

            if (ip.length() > 0)
                ip = ip + ".";

            // valueOf(String s, int radix)
            ip = ip + Integer.valueOf(hexValue.substring(i, i + 2), 16);
        }
        return ip;
    }

    private String convertToHex(String ipValue) {
        // System.out.println("Convert to hex " + ipValue);

        String[] values = ipValue.split("[\\.,]");
        String hex = "";

        for (int i = 0; i < values.length; i++) {

            String hexValue = Integer.toHexString(Integer.parseInt(values[i]));
            if (hexValue.length() == 1)
                hexValue = 0 + hexValue;

            hex += hexValue;

        }

        return hex.toUpperCase();

    }
}
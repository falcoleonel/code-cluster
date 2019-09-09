public class Hex2IP {

    public static void main(String[] args) {
        if (args.length != 2) {
        System.out.println("Se requieren 2 argumentos");
        return;
         String convertion = args[0];
    String convertedValue = "";
    try {
      switch (convertion) {
        case "-hex":
          convertedValue = hex2Ip(args[1]);
          break;
        case "-ip":
          convertedValue = ip2Hex(args[1]);
          break;
        default:
          System.out.println("Conversión inválida");
          return;
      }
    } catch (Exception error) {
      System.out.println(error.getMessage());
    }
    System.out.println(convertedValue);
  }

  static String hex2Ip(String hexVal) throws Exception {
    if (hexVal.length() != 8) {
      throw new Exception("Formato incorrecto");
    }

    String[] ipVals = new String[4];
    for (int i = 0; i < 4; i++) {
      int ipVal = Integer.parseInt(hexVal.substring(i * 2, i * 2 + 2), 16);
      ipVals[i] = String.valueOf(ipVal);
    }

    return String.join(".", ipVals);
  }

  static String ip2Hex(String ipVal) throws Exception {
    if (ipVal.length() < 7 && ipVal.length() > 15) {
      throw new Exception("Formato incorrecto");
    }

    String[] vals = ipVal.split("\\.");
    String hexVal = "";
    for (int i = 0; i < 4; i++) {
      int val = Integer.parseInt(vals[i]);
      if (val < 0 && val > 255) {
        throw new Exception("Formato incorrecto");
      }
      String hexDigit = Integer.toHexString(val);
      if (val < 10) {
        hexVal += "0" + vals[i];
      } else {
        hexVal += hexDigit;
      }
    }

    return hexVal.toUpperCase();
  }
    }
 }

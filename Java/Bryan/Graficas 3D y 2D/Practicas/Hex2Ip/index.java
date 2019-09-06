
import java.util.Scanner;
import java.lang.Integer;

//import java.net.InetAddress;

public class index
{
	public static void main(String[] args)
	{
        String[] command = args;
        //System.out.println(args[0]);

            switch (command[0]) {
                case "-hex":
                hex2ip(command[1]);
                break;  
                case "-ip":
                ip2hex(command[1]);
                break;
                
                case "exit":
                System.out.println("-----------Exiteando ando-----------");                
                break;
                
                default:
                System.out.println("-----------Comando NO valido-----------");
                System.out.println("Comandos disponibles:");
                System.out.println("-hex:   Convierte hexadecimal a IP");
                System.out.println("-ip:   Convierte IP a hexadecimal");
                System.out.println("exit:   Salir");
                System.out.println("---------------------------------------\n");
                
                break;
            }
    }
    public static String readConsole() {
        Scanner scan = new Scanner(System.in);
        String w=scan.nextLine();
        scan.close();
        return w;
     }
    public static String[] words(String lines) {
        return lines.trim().split("\\s+");
     }    

    public static void hex2ip(String ip) {
        System.out.println("IP: "+ip);

        if (isIP(ip)) {
            String[] ip2 = ip.trim().split("\\.");

            System.out.println("HEX : x0"+convert_ip(ip2));
        }
        else
        System.out.println("No es Valida!!");
     }
    public static String convert_ip(String[] ip) {
        String aux="";
        for(int i=0; i<ip.length;i++){
            aux = aux + String.format("%02X",Integer.valueOf(Integer.parseInt(ip[i]))) ;
        }     
        return aux;
     }

    public static void ip2hex(String hex) {
        System.out.println("HEX: "+hex);

        if (ishex(hex)) {
            System.out.println("IP :"+convert_hex(divide(hex)));
            //System.out.println("si es hex");
        }
        else
        System.out.println("No es Valida!!");
     } 
        
    public static boolean isIP(String ip){
            String[] ip2 = ip.trim().split("\\.");
            return ((ip2).length==4 && areOnRange(ip2,0,255))? true: false;
     }

    public static boolean areOnRange(String[] ip, int menor, int mayor){
        int aux;
        int aux2=0;
            
            for(int i = 0; i<ip.length;i++){
                aux=Integer.parseInt(ip[i]);
                aux2 = (aux>= menor && aux<= mayor)? aux2+1:aux2;
            }
            return (aux2==ip.length)? true : false;
     }
    
    public static boolean ishex(String hex){
        return ((divide(hex).length==4)?true:false);
     }

    public static String[] divide(String hex){
        char[] c = hex.toCharArray();
        String[] aux= new String[4];
        int F = 0;
        for(int i = 0;i < c.length;i=i+2){
            //aux[F] = (Character.toString(c[i]) + Character.toString(c[i+1]));
            aux[F] = (Character.toString(c[i]) + Character.toString(c[i+1]));
            F++;
         }
        return aux;
     }

    public static String convert_hex(String[] hex) {
        String aux="";

        for (int i=0; i< hex.length; i++) {
            aux = aux + Integer.toString(Integer.parseInt(hex[i],16)) + ((i < hex.length-1)? ".":"");
        }
          return aux;
     }
}
import javax.swing.JOptionPane;

public class index{
	public static void main(String[] args){

        
        String txt = JOptionPane.showInputDialog(null,"Inserte una cadena de caracteres");
        int y = JOptionPane.showConfirmDialog(null, "¿Quiere leer los datos de izquierda a derecha?", "title", JOptionPane.YES_NO_OPTION);
        System.out.println(y);
        boolean x = viewchar2char(txt, true);

    }
    public static boolean viewchar2char(String txt,boolean reverse){
        System.out.println(txt);
        return (!reverse) ? beginToEnd(txt.toCharArray()) : endToBegin(txt.toCharArray());
     }
    
    public static boolean beginToEnd(char[] letters){
        String tmp = "";
        for(int i=0;i<letters.length;i++){
            tmp = tmp + letters[i];
            System.out.println(tmp);
        }
        return false;
     }
    public static boolean endToBegin(char[] letters){
        String tmp = "";
        for(int i = letters.length-1; i>=0; i--){
            tmp = letters[i] + tmp;
            System.out.println(tmp);
        }
        return true;
     }
}
package pixel;
/**
 * Coordenada3d
 */
public class Coordenada3d {
    String name;
    int x,y,z;
    public Coordenada3d(String Name) {
        name = Name;
        x=0;
        y=0;
        z=0;
    }
    public int getX_2D( Coordenada3d proye) {
        return (this.x - proye.getX() *(this.z/proye.getZ()));
        //this.coordenada2d.put("y", y - proyeccion.get("y")*(z/proyeccion.get("z")));
    }
    public int getY_2D( Coordenada3d proye) {
        return (this.y - proye.getY() *(this.z/proye.getZ()));
        //this.coordenada2d.put("y", y - proyeccion.get("y")*(z/proyeccion.get("z")));
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public int getZ() {
        return this.z;
    }
    public void rotar(int grados) {
        
    }
}
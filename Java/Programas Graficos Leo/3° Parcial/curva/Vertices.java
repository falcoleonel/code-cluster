package curva;

// Tiene 8 vertices que son construidos mediante coordenadas
public class Vertices
{
    //Cuadro superior
    public Coordenadas v1;
    public Coordenadas v2;
    public Coordenadas v3;
    public Coordenadas v4;
    //Cuadro inferior
    public Coordenadas v5;
    public Coordenadas v6;
    public Coordenadas v7;
    public Coordenadas v8;
    public Vertices()
    {
        v1= new Coordenadas();
        v2= new Coordenadas();
        v3= new Coordenadas();
        v4= new Coordenadas();
        v5= new Coordenadas();
        v6= new Coordenadas();
        v7= new Coordenadas();
        v8= new Coordenadas();
        
        iniciarCoordenadas(v1);
        iniciarCoordenadas(v2);
        iniciarCoordenadas(v3);
        iniciarCoordenadas(v4);
        iniciarCoordenadas(v5);
        iniciarCoordenadas(v6);
        iniciarCoordenadas(v7);
        iniciarCoordenadas(v8);
    }
    public void iniciarCoordenadas(Coordenadas v)
    {
        v.x=0;
        v.y=0;
        v.z=0;
    }
}

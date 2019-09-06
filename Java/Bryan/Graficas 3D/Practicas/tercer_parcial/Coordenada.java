package tercer_parcial;

public class Coordenada implements Numero<Coordenada> {
    
    public double w;
    public double x;
    public double y;
    public double z;
    
    public Coordenada() {
        w = x = y = z = 0;
    }
    
    public Coordenada(double w) {
        this.w = w;
        x = y = z = 0;
    }
    
    public Coordenada(double x, double y, double z) {
        w = 0;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public Coordenada(double w, double x, double y, double z) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public Coordenada(Coordenada copia) {
        w = copia.w;
        x = copia.x;
        y = copia.y;
        z = copia.z;
    }
    
    public static final Coordenada IDENTIDAD = new Coordenada(1);
    public static final Coordenada CERO = new Coordenada();

    @Override
    public Coordenada mas(Coordenada n2) {
        return new Coordenada(w + n2.w, x + n2.x, y + n2.y, z + n2.z);
    }

    @Override
    public Coordenada menos(Coordenada n2) {
        return new Coordenada(w - n2.w, x - n2.x, y - n2.y, z - n2.z);
    }

    @Override
    public Coordenada por(Coordenada n2) {
        return new Coordenada(
            w * n2.w - x * n2.x - y * n2.y - z * n2.z,
            w * n2.x + x * n2.w + y * n2.z - z * n2.y,
            w * n2.y - x * n2.z + y * n2.w + z * n2.x,
            w * n2.z + x * n2.y - y * n2.x + z * n2.w
        );
    }

    @Override
    public Coordenada entre(Coordenada n2) {
        double norm2 = n2.w * n2.w + n2.x * n2.x + n2.y * n2.y + n2.z * n2.z;
        return new Coordenada(
            (w * n2.w + x * n2.x + y * n2.y + z * n2.z) / norm2,
            (-w * n2.x + x * n2.w - y * n2.z + z * n2.y) / norm2,
            (-w * n2.y + x * n2.z + y * n2.w - z * n2.x) / norm2,
            (-w * n2.z - x * n2.y + y * n2.x + z * n2.w) / norm2
        );
    }

    @Override
    public Coordenada por(double f) {
        return new Coordenada(f * w, f * x, f * y, f * z);
    }

    @Override
    public Coordenada entre(double f) {
        return new Coordenada(w / f, x / f, y / f, z / f);
    }

    @Override
    public Coordenada inv() {
        double norm = w * w + x * x + y * y + z * z;
        return new Coordenada(w / norm, -x / norm, -y / norm, -z / norm);
    }

    @Override
    public Coordenada exp() {
        double f = Math.exp(w);
        double av = Math.sqrt(x * x + y * y + z * z);
        if(av == 0) return new Coordenada(f);
        double fv = f * Math.sin(av) / av;
        return new Coordenada(f * Math.cos(av), fv * x, fv * y, fv * z);
    }

    @Override
    public Coordenada log() {
        double aq = Math.sqrt(w * w + x * x + y * y + z * z);
        double av = Math.sqrt(x * x + y * y + z * z);
        if(av == 0) return new Coordenada(Math.log(aq));
        double f = Math.acos(w / aq) / av;
        return new Coordenada(Math.log(aq), f * x, f * y, f * z);
    }
    
    @Override
    public Coordenada pow(Coordenada n2) {
        return n2.por(log()).exp();
    }
    
    @Override
    public Coordenada conj() {
        return new Coordenada(w, -x, -y, -z);
    }
    
    public double norm() {
        return w * w + x * x + y * y + z * z;
    }
    
    @Override
    public double abs() {
        return Math.sqrt(w * w + x * x + y * y + z * z);
    }
    
    @Override
    public String toString() {
        return String.valueOf(w) + ' ' +
            (x < 0 ? '-' : '+') + ' ' +
            String.valueOf(Math.abs(x)) + "i " +
            (y < 0 ? '-' : '+') + ' ' +
            String.valueOf(Math.abs(y)) + "j " +
            (z < 0 ? '-' : '+') + ' ' +
            String.valueOf(Math.abs(z)) + 'k';
    }
}

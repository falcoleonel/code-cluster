package matematicas;

public class Cuaternion implements Numero<Cuaternion> {
    
    public double w;
    public double x;
    public double y;
    public double z;
    
    public Cuaternion() {
        w = x = y = z = 0;
    }
    
    public Cuaternion(double w) {
        this.w = w;
        x = y = z = 0;
    }
    
    public Cuaternion(double x, double y, double z) {
        w = 0;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public Cuaternion(double w, double x, double y, double z) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public Cuaternion(Cuaternion copia) {
        w = copia.w;
        x = copia.x;
        y = copia.y;
        z = copia.z;
    }
    
    public static final Cuaternion IDENTIDAD = new Cuaternion(1);
    public static final Cuaternion CERO = new Cuaternion();

    @Override
    public Cuaternion mas(Cuaternion n2) {
        return new Cuaternion(w + n2.w, x + n2.x, y + n2.y, z + n2.z);
    }

    @Override
    public Cuaternion menos(Cuaternion n2) {
        return new Cuaternion(w - n2.w, x - n2.x, y - n2.y, z - n2.z);
    }

    @Override
    public Cuaternion por(Cuaternion n2) {
        return new Cuaternion(
            w * n2.w - x * n2.x - y * n2.y - z * n2.z,
            w * n2.x + x * n2.w + y * n2.z - z * n2.y,
            w * n2.y - x * n2.z + y * n2.w + z * n2.x,
            w * n2.z + x * n2.y - y * n2.x + z * n2.w
        );
    }

    @Override
    public Cuaternion entre(Cuaternion n2) {
        double norm2 = n2.w * n2.w + n2.x * n2.x + n2.y * n2.y + n2.z * n2.z;
        return new Cuaternion(
            (w * n2.w + x * n2.x + y * n2.y + z * n2.z) / norm2,
            (-w * n2.x + x * n2.w - y * n2.z + z * n2.y) / norm2,
            (-w * n2.y + x * n2.z + y * n2.w - z * n2.x) / norm2,
            (-w * n2.z - x * n2.y + y * n2.x + z * n2.w) / norm2
        );
    }

    @Override
    public Cuaternion por(double f) {
        return new Cuaternion(f * w, f * x, f * y, f * z);
    }

    @Override
    public Cuaternion entre(double f) {
        return new Cuaternion(w / f, x / f, y / f, z / f);
    }

    @Override
    public Cuaternion inv() {
        double norm = w * w + x * x + y * y + z * z;
        return new Cuaternion(w / norm, -x / norm, -y / norm, -z / norm);
    }

    @Override
    public Cuaternion exp() {
        double f = Math.exp(w);
        double av = Math.sqrt(x * x + y * y + z * z);
        if(av == 0) return new Cuaternion(f);
        double fv = f * Math.sin(av) / av;
        return new Cuaternion(f * Math.cos(av), fv * x, fv * y, fv * z);
    }

    @Override
    public Cuaternion log() {
        double aq = Math.sqrt(w * w + x * x + y * y + z * z);
        double av = Math.sqrt(x * x + y * y + z * z);
        if(av == 0) return new Cuaternion(Math.log(aq));
        double f = Math.acos(w / aq) / av;
        return new Cuaternion(Math.log(aq), f * x, f * y, f * z);
    }
    
    @Override
    public Cuaternion pow(Cuaternion n2) {
        return n2.por(log()).exp();
    }
    
    @Override
    public Cuaternion conj() {
        return new Cuaternion(w, -x, -y, -z);
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

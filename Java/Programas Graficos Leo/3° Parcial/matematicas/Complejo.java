package matematicas;

public class Complejo implements Numero<Complejo> {
    
    public double real;
    public double imag;
    
    public Complejo() {
        real = imag = 0;
    }
    
    public Complejo(double real) {
        this.real = real;
        imag = 0;
    }
    
    public Complejo(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }
    
    public Complejo(Complejo copia) {
        real = copia.real;
        imag = copia.imag;
    }

    @Override
    public Complejo mas(Complejo n2) {
        return new Complejo(real + n2.real, imag + n2.imag);
    }

    @Override
    public Complejo menos(Complejo n2) {
        return new Complejo(real - n2.real, imag - n2.imag);
    }

    @Override
    public Complejo por(Complejo n2) {
        return new Complejo(
            real * n2.real - imag * n2.imag,
            real * n2.imag + imag * n2.real
        );
    }
    
    @Override
    public Complejo por(double f) {
        return new Complejo(real * f, imag * f);
    }

    @Override
    public Complejo entre(Complejo n2) {
        double norm2 = n2.real * n2.real + n2.imag * n2.imag;
        return new Complejo(
            (real * n2.real + imag * n2.imag) / norm2,
            (imag * n2.real - real * n2.imag) / norm2
        );
    }
    
    @Override
    public Complejo entre(double f) {
        return new Complejo(real / f, imag / f);
    }

    @Override
    public Complejo inv() {
        double norm = real * real + imag * imag;
        return new Complejo(real / norm, -imag / norm);
    }

    @Override
    public Complejo exp() {
        double f = Math.exp(real);
        return new Complejo(f * Math.cos(imag), f * Math.sin(imag));
    }

    @Override
    public Complejo log() {
        return new Complejo(
            Math.log(real * real + imag * imag) / 2,
            Math.atan2(imag, real)
        );
    }
    
    @Override
    public Complejo pow(Complejo n2) {
        return n2.por(log()).exp();
    }
    
    @Override
    public Complejo conj() {
        return new Complejo(real, -imag);
    }
    
    public double norm() {
        return real * real + imag * imag;
    }
    
    @Override
    public double abs() {
        return Math.hypot(real, imag);
    }
    
    public double arg() {
        return Math.atan2(imag, real);
    }
    
    @Override
    public String toString() {
        return String.valueOf(real) + ' ' +
            (imag < 0 ? '-' : '+') + ' ' +
            String.valueOf(Math.abs(imag)) + 'i';
    }
}

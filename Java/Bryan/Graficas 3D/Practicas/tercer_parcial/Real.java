package tercer_parcial;

public class Real implements Numero<Real> {

    public double val;
    
    public Real(double val) {
        this.val = val;
    }
    
    @Override
    public Real mas(Real n2) {
        return new Real(val + n2.val);
    }

    @Override
    public Real menos(Real n2) {
        return new Real(val - n2.val);
    }

    @Override
    public Real por(Real n2) {
        return new Real(val * n2.val);
    }

    @Override
    public Real entre(Real n2) {
        return new Real(val / n2.val);
    }

    @Override
    public Real por(double f) {
        return new Real(f * val);
    }

    @Override
    public Real entre(double f) {
        return new Real(val / f);
    }

    @Override
    public Real inv() {
        return new Real(1.0 / val);
    }

    @Override
    public Real exp() {
        return new Real(Math.exp(val));
    }

    @Override
    public Real log() {
        return new Real(Math.log(val));
    }

    @Override
    public double abs() {
        return Math.abs(val);
    }

    @Override
    public Real conj() {
        return this;
    }

    @Override
    public Real pow(Real n2) {
        return new Real(Math.pow(val, n2.val));
    }
    
    @Override
    public String toString() {
        return String.valueOf(val);
    }
}

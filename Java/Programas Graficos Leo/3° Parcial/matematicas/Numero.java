package matematicas;

public interface Numero<T> extends Vector<T> {
    
    public T por(T n2);
    public T entre(T n2);
    
    public T inv();
    public T exp();
    public T log();
    public T pow(T n2);
    public double abs();
    public T conj();
}

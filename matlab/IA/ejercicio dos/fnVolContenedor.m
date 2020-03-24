function fitness = fnVolContenedor(particula, maximo)
    x = particula(1);
    y = particula(2);
    
    fitness = x^2*y;
    
    if maximo == 1
        fitness = fitness*-1;
    end
end
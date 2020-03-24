function fitness = fnAreaRegion(particula, maximo)
    x = particula(1);
    y = particula(2);
    
    fitness = ((sqrt(3)*x^2) / 4) + x*y;
    
    if maximo == 1
        fitness = fitness*-1;
    end
end
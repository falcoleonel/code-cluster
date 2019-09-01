function fitness = fnAreaRegion(particula)
    x = particula(1);
    y = particula(2);
    
    fitness = ((sqrt(3)*x^2) / 4) + x*y;% area total
end
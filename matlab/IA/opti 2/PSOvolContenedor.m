
clc;
close all
clear all 

dimensiones = 2;
lim_inferior = [1, 1];
lim_superior = [14, 14];
num_iteraciones = 50;
num_particulas = 5;

%inicializacion
particulas = rand(num_particulas, dimensiones) .* (lim_superior - lim_inferior) + lim_inferior;

%evualuar todas las particulas en la funcion objetivo
for i = 1:num_particulas
    particulas(i, 2) = 15 - particulas(i, 1);
    fitness(i) = fnVolContenedor(particulas(i, :));
    nuevo_fitness(i) = fitness(i);
end
    [mejor_global, posicion] = max(fitness);

mejores_globales = ones(num_particulas, dimensiones);

for i=1:dimensiones
    mejores_globales(:, i) = particulas(posicion, i);
end

%guarda mejor particula actual a nivel global
mejor_posicion = particulas(posicion, :);
mejores_locales = particulas;

%inicializa velocidad
velocidad = zeros(num_particulas, dimensiones);

for t = 1:num_iteraciones %criterio de paro
    
    %generamos nueva velocidad
    velocidad = velocidad + 2*rand(num_particulas, dimensiones) .* (mejores_locales - particulas) + 2*rand(num_particulas, dimensiones) .* (mejores_globales - particulas);
     %movemos las particulas
    particulas = particulas + velocidad;
    
     %estabilizar dentro de los limites
    for i = 1:num_particulas
        for j = 1:dimensiones
            if particulas(i, j) > lim_superior(j)
                particulas(i, j) = lim_superior(j);
            end
            
            if particulas(i, j) < lim_inferior(j)
                particulas(i, j) = lim_inferior(j);
            end
        end
        
        %volver a calcular y y funcion objetivo
        particulas(i, 2) = 15 - particulas(i, 1);
        nuevo_fitness(i) = fnVolContenedor(particulas(i, :));
        
      %determina nueva mejor posicion comparando con viejo arreglo
            if nuevo_fitness(i) > fitness(i)
                mejores_locales(i, :) = particulas(i, :);
                fitness(i) = nuevo_fitness(i);
            end
    end
    %obtiene el mejor valor global
        [nueva_mejor_global, posicion] = max(fitness);
    
    %sacar nuevo mejor global
        if nueva_mejor_global > mejor_global
            mejor_global = nueva_mejor_global;

            for i = 1:dimensiones
                mejores_globales(:, i) = particulas(posicion, i);
            end
            mejor_posicion = particulas(posicion, :);
        end
    
    Evolucion(t) = mejor_global;
    t = t+1;    
end

plot(Evolucion);
disp(['Mejor posicion de x: ', num2str(mejor_posicion(1))])
disp(['Mejor posicion de y: ', num2str(mejor_posicion(2))])
disp(['Mejor valor función objetivo: ', num2str(mejor_global)])

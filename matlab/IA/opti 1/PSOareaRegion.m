clc;
close all 
clear all 

%dimensiones = 2;
lim_inferior = [2];
lim_superior = [30];
num_iteraciones = 50;
num_particulas = 5;

%inicializacion
particulas = rand(num_particulas) .* (lim_superior - lim_inferior) + lim_inferior;

%evualuar todas las particulas en la funcion objetivo
for i = 1:num_particulas     
    particulas(i) = 50 - ((3/2) * particulas(i)); %valor de y con condicion
    fitness(i) = fnAreaRegion(particulas(i));
    nuevo_fitness(i) = fitness(i);
end

[mejor_global, posicion] = max(fitness);

mejores_globales = ones(num_particulas);

%guarda mejor particula actual a nivel global
for i=1:dimensiones
    mejores_globales = particulas(posicion);
end

mejor_posicion = particulas(posicion);
mejores_locales = particulas;

%inicializa velocidad
velocidad = zeros(num_particulas);

for t = 1:num_iteraciones
    %generamos nueva velocidad
    velocidad = velocidad + 2*rand(num_particulas) .* (mejores_locales - particulas) + 2*rand(num_particulas) .* (mejores_globales - particulas);
    %movemos las particulas
    particulas = particulas + velocidad;
    
    %estabilizar dentro de los limites
    for i = 1:num_particulas   
            if particulas(i) > lim_superior
                particulas(i) = lim_superior;
            end
            
            if particulas(i) < lim_inferior
                particulas(i) = lim_inferior;
            end
        
        %volver a calcular y y funcion objetivo
        particulas(i) = 50 - ((3/2) * particulas(i));
        nuevo_fitness(i) = fnAreaRegion(particulas(i));

      %determina nueva mejor posicion comparando con viejo arreglo
            if nuevo_fitness(i) > fitness(i)
                mejores_locales(i) = particulas(i);
                fitness(i) = nuevo_fitness(i);
            end
    end
    
    [nueva_mejor_global, posicion] = max(fitness);
   
    %sacar nuevo mejor global
        if nueva_mejor_global > mejor_global
            mejor_global = nueva_mejor_global;
                mejores_globales = particulas(posicion);
            
            mejor_posicion = particulas(posicion);
        end
     Evolucion(t) = mejor_global;
    t = t+1;    
end
plot(Evolucion);
disp(['Mejor posicion de x: ', num2str(mejor_posicion(1))])
disp(['Mejor posicion de y: ', num2str(mejor_posicion(2))])
disp(['Mejor valor función objetivo: ', num2str(mejor_global)])
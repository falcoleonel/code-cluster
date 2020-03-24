%% ALGORITMO GENETICO REAL
function AGR
    clear all;
    close all;
    
    d = 2;          %% No. dimensiones
    l = 2;          %% Límite inferior
    u = 32;         %% Límite superior
    maxk = 20;      %% No. máximo de iteraciones
    maximo = 1;     %% Obtener máximo (1) o mínimo (0)
    
    %% Parámetros:
    Np = 5;             %% Tamaño de población
    mutacion = 0.2;     %% Proporción de mutación
    cruza = 0.9;
    k = 0;                
    
    %% Inicializar 
    R = rand(Np, d) .* (u - l) + l;
    
    %% Evaluar población
    for i = 1:Np
        %% Revaluar la posición y según la posición x de la partícula
        R(i, 2) = 50 - ((3/2) * R(i, 1));
        
        fit(i, 1) = fnAreaRegion(R(i, :), maximo);
    end
    
    [f, ind] = sort(fit);
    R = R(ind, :);
    minf(k + 1) = f(1);
    
    %% Iteraciones:
    while k < maxk
        k = k + 1;  
        
        %% Selección de padres para cruza (Método de la ruleta):
        E = sum(f); 
        E = f ./ E;
        E = flip(E);
        q = [];
        q = cumsum(E);    
        for c = 1:Np
           padre1(c) = RULETA(q);
           padre2(c) = RULETA(q);
        end
        
        %% Cruza de un punto:
        H = [];
        Hf = [];
        for c = 1:Np
            hijo1 = [];
            hijo2 = [];
            
            if rand() <= cruza 
               if d == 2
                   hijo1 = [R(padre1(c), 1), R(padre2(c), 2)];
                   hijo2 = [R(padre1(c), 2), R(padre2(c), 1)];
               else
                   ptCruce = randi([1 d]);  %% Obtener el punto de cruce
                   hijo1 = [R(padre1(c), 1:ptCruce), R(padre2(c), ptCruce + 1:end)];
                   hijo2 = [R(padre2(c), 1:ptCruce), R(padre1(c), ptCruce + 1:end)];
               end
            end
            
            H = [H; hijo1; hijo2];
        end
       
        %% Mutación:
        sizeH = size(H, 1); 
        Hm = rand(sizeH, d);
        Hm = Hm < mutacion;
        
        for c = 1:sizeH
            ind = find(Hm(c, :));
            H(c, ind) = rand(1, length(ind)) .* (u - l) + l;  
        end
        
        for i = 1:sizeH
            %% Revaluar la posición y según la posición x de la partícula
            H(i, 2) = 50 - ((3/2) * H(i, 1));
            
            Hf(i, 1) = fnAreaRegion(H(i, :), maximo);
        end
        
        R = [R; H];
        f = [f; Hf];
        [f, ind] = sort(f);
        R = R(ind, :);
        minf(k) = f(1);
        
        %% Selección:
        f = f(1:Np, 1);           
        R = R((1: Np), :);
        
        if maximo == 1
            disp(sprintf('Generación %d, fitness: %.2f', k, f(1)*-1));
        else
            disp(sprintf('Generación %d, fitness: %.2f', k, f(1)));
        end
        
        disp(sprintf('Posición de x: %.2f', R(1,1)));
        disp(sprintf('Posición de y: %.2f', R(1,2)));
    end
    
    figure
    plot(minf)  %% Convergencia
end
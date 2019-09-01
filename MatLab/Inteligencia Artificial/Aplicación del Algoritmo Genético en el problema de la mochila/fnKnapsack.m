function fitness = fnKnapsack(objeto, valor, peso, num_objetos, peso_max, maximo)
    fitness = 0;
    peso_total = 0;
    
    for i = 1:num_objetos
        %% Obtener el peso total de los objetos seleccionados
        peso_total = peso_total + (peso(i) * objeto(i));
        
        %% Obtener el valor total de los objetos seleccionados
        fitness = fitness + (valor(i) * objeto(i));
    end
    
    %% Restricción
    if peso_total > peso_max
        %% Penalización
        fitness = fitness*0;
    end
    
    if maximo == 1
        fitness = fitness*-1;
    end
end
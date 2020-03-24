function seleccionado = RULETA(q)
    r = rand();
    seleccionado = -1;
    
    for ind = 1 : length(q)
        if (q(ind) >= r)
            seleccionado = ind;
            break;
        end
    end
end
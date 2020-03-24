function gaussiano(entrada)

matrizO=zeros(entrada,entrada);
c=entrada/2;
sigma = entrada/12;

% funcion gausiana
fg = (1/(2*pi*(sigma*sigma)));
for x=1:entrada
    for y=1:entrada
        matrizO(x,y)=fg*exp(-((((x-c-0.5)^2)+((y-c-0.5)^2))/(2*sigma*sigma)));
    end
end
suma=0;
for x=1:entrada
    for y=1:entrada
        suma=suma+matrizO(x,y);
    end
end
for x=1:entrada
    for y=1:entrada
        matrizO(x,y)=matrizO(x,y)/suma;
    end
end
        filtrada = convolucion('knight.jpg',matrizO);
        figure('Name','Filtro Gaussiano')
        imshow(filtrada);   
end

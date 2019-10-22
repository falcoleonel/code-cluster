function sombrero(entrada)

matrizO=zeros(entrada,entrada);
c=entrada/2;
sigma = entrada/12;
f = (1/sqrt(2*pi)*(sigma*sigma*sigma));
for x=1:entrada
    for y=1:entrada
        matrizO(x,y)=f*(1-(((x-c-0.5)^2)+((y-c-0.5)^2)/(sigma*sigma)))*exp(-((((x-c-0.5)^2)+((y-c-0.5)^2))/(2*sigma*sigma)));
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
        matriz(x,y)=matrizO(x,y)/suma;
    end
end
        filtrada = convolucion('knight.jpg',matriz);
        figure('Name','Filtro Sombrero')
        imshow(filtrada);   
end

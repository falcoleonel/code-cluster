function filtrosombrero(tam)

matrizO=zeros(tam,tam);
c=tam/2;
sigma = tam/12;
f = (1/sqrt(2*pi)*(sigma*sigma*sigma));
for x=1:tam
    for y=1:tam
        matrizO(x,y)=f*(1-(((x-c-0.5)^2)+((y-c-0.5)^2)/(sigma*sigma)))*exp(-((((x-c-0.5)^2)+((y-c-0.5)^2))/(2*sigma*sigma)));
    end
end
suma=0;
for x=1:tam
    for y=1:tam
        suma=suma+matrizO(x,y);
    end
end
for x=1:tam
    for y=1:tam
        matriz(x,y)=matrizO(x,y)/suma;
    end
end
        filtrada = convolucion('BrunoCoin.jpg',matriz);
        figure('Name','Filtro Sombrero','NumberTitle','off')
        imshow(filtrada);   
end

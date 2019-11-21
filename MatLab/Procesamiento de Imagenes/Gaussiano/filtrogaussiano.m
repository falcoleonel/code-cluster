function filtrogaussiano(tam)

matrizO=zeros(tam,tam);
c=tam/2;
sigma = tam/12;
f = (1/(2*pi*(sigma*sigma)));
for x=1:tam
    for y=1:tam
        matrizO(x,y)=f*exp(-((((x-c-0.5)^2)+((y-c-0.5)^2))/(2*sigma*sigma)));
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
        matrizO(x,y)=matrizO(x,y)/suma;
    end
end
        filtrada = convolucion('imagen.jpg',matrizO);
        figure('Name','Filtro Gaussiano','NumberTitle','off')
        imshow(filtrada);   
end

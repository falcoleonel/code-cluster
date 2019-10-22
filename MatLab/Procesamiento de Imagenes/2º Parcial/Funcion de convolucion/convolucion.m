function salida = convolucion(Img,Filtro)
I=imread(Img);
%escala de grises
Grey=I(:,:,1);

Origen=Grey;
figure('Name','Original')
imshow(Grey);

[M,N]=size(Origen);
Copia=Origen;

[FM,FN]= size(Filtro);
%incremento
inc = (FM-1)/2;

%Construir copia con borde
for i=1:(M+(inc*2))
    for j=1:(N+(inc*2))
        if i>(inc) && i<=(M+inc) && j>(inc) && j<=(N+inc)
            Copia(i,j)=Origen((i-(inc)),(j-(inc)));
        else
            Copia(i,j) = 0;
        end
    end
end

%Imagen resultante
Nueva=Origen;

for x=1+inc:M+inc
    for y = 1+inc:N+inc
        suma=0;
        for i=-inc:+inc
            for j=-inc:+inc
                suma=suma+(Copia(x+i,y+j)*Filtro((FM-inc)+i,(FN-inc)+j));
            end
        end
        Nueva(x-inc,y-inc)=suma;
    end
end

salida=Nueva;
end 
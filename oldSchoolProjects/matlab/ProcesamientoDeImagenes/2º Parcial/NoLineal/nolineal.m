function salida = nolineal(Img,entrada,Tipo)
I=imread(Img);
%escala de grises
grey=I(:,:,1);
%agregar ruido
imgRuido = imnoise(grey,'salt & pepper',0.002);

origin=imgRuido;
figure('Name','Original')
imshow(imgRuido);

[M,N]=size(origin);
copia=origin;

%incremento
fl = (entrada-1)/2;

%Construir imagen copia con borde
for i=1:(M+(fl*2))
    for j=1:(N+(fl*2))
        if i>(fl) && i<=(M+fl) && j>(fl) && j<=(N+fl)
            copia(i,j)=origin((i-(fl)),(j-(fl)));
        else
            copia(i,j) = 0;
        end
    end
end

%Imagen resultante
nueva=origin;

switch Tipo
    case 'm'
        for x=1+fl:M+fl
            for y = 1+fl:N+fl
                suma=255;
                for i=-fl:+fl
                    for j=-fl:+fl
                        valor=copia(x+i,y+j);
                        if(valor<suma)
                            suma=valor;
                        end
                    end
                end
                nueva(x-fl,y-fl)=suma;
            end
        end
    case 'M'
        for x=1+fl:M+fl
            for y = 1+fl:N+fl
                suma=0;
                for i=-fl:+fl
                    for j=-fl:+fl
                        valor=copia(x+i,y+j);
                        if(valor>suma)
                            suma=valor;
                        end
                    end
                end
                nueva(x-fl,y-fl)=suma;
            end
        end
    case 'med'
        for x=1+fl:M+fl
            for y = 1+fl:N+fl
                suma=zeros(1,entrada*entrada);
                cont=1;
                for i=-fl:+fl
                    for j=-fl:+fl
                        valor=copia(x+i,y+j);
                        suma(1,cont)=valor;
                        cont=cont+1;
                    end
                end
                medio=median(suma(1,:));
                nueva(x-fl,y-fl)=medio;
            end
        end
end

salida=nueva;
end 
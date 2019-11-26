function Y = conv(ImgO,Filtro)
[M,N]=size(ImgO);
ImgC=ImgO;

[FM,FN]= size(Filtro);
inc = (FM-1)/2;

%Construir imagen copia con borde
for i=1:(M+(inc*2))
    for j=1:(N+(inc*2))
        if i>(inc) && i<=(M+inc) && j>(inc) && j<=(N+inc)
            ImgC(i,j)=ImgO((i-(inc)),(j-(inc)));
        else
            ImgC(i,j) = 0;
        end
    end
end
%figure('Name','Copia','NumberTitle','off')
%imshow(ImgC);

%Imagen resultante
ImgR=ImgO;
for x=1+inc:M+inc
    for y = 1+inc:N+inc
        suma=0;
        for i=-inc:+inc
            for j=-inc:+inc
                suma=suma+(ImgC(x+i,y+j)*Filtro((FM-inc)+i,(FN-inc)+j));
            end
        end
        ImgR(x-inc,y-inc)=suma;
    end
end

% figure('Name','Final','NumberTitle','off')
% imshow(ImgR);
Y=ImgR;
end 
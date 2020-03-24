function deteccionEsquinas
I=imread('figuras_gris.jpg');
gris=double(rgb2gray(I));

[M N]=size(gris);

fBox=imboxfilt(gris);

prewittdx=[-1, 0, 1; 
    -1, 0, 1; 
    -1, 0, 1]/2;

prewittdy=[-1, -1, -1; 
    0, 0, 0; 
    1, 1, 1]/2;

%gradientes
Ix=conv2(fBox,prewittdx);
Iy=conv2(fBox,prewittdy);

%Estructuras HE
for x=1:M
    for y=1:N
        HEx(x,y)=Ix(x,y)^2;
        HEy(x,y)=Iy(x,y)^2;
        HEz(x,y)=Ix(x,y)*Iy(x,y);
    end
end

matriz=[0,1,2,1,0; 
    1,3,5,3,1; 
    2,5,9,5,2; 
    1,3,5,3,1; 
    0,1,2,1,0]/57;

A=conv2(HEx,matriz);
B=conv2(HEy,matriz);
C=conv2(HEz,matriz);

%gradiente
for x=1:M
    for y=1:N
        v(x,y)=(A(x,y)*B(x,y)-(C(x,y)^2))-(0.1*(A(x,y)+B(x,y))^2);
    end
end

%threshold
for x=1:M
    for y=1:N
        if(v(x,y)<=900)
            v(x,y)=0;
        end
    end
end

dif=2;
R=[];
for x=1+dif:M-dif
    for y=1+dif:N-dif
        act=v(x,y);
        valores=v(x-dif:x+dif, y-dif:y+dif);
        Mx=max(valores(:));
        if(act>0 && act == Mx)
            R=[R; y x];
        end
    end
end

%calcular el maximo central vecindad de 5
%maximizada
%R=imdilate(v,true(5));
%R = uint8(R);


figure('Name','Gris')
imshow(I);

figure('Name','Ix')
imshow(uint8(Ix));

figure('Name','Iy')
imshow(uint8(Iy));

figure('Name','')
imshow(v);

figure('Name','Gris')
imshow(I);
[RM,RN] = size(R);
for i=1:RM
    hold on;
    plot(R(i,1),R(i,2),'+', 'MarkerSize',15);
end

end
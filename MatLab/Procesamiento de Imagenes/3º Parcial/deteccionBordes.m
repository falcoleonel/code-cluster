function deteccionBordes
I=imread('heartless.jpg');
grey=rgb2gray(I);
gris=double(grey);

[M,N]=size(gris);

%filtros prewitt
px=[-1, 0, 1; 
    -1, 0, 1; 
    -1, 0, 1];

py=[-1, -1, -1; 
     0, 0, 0; 
     1,1, 1];

dx=conv2(gris,px);
dy=conv2(gris,py);

%gradiente total
for x=1:M
    for y=1:N
        v(x,y)=sqrt((dx(x,y)^2)+(dy(x,y)^2));
    end
end

%valor maximo
maxi=max(v);
maxVal=max(maxi);

%gradiente total normalizado
for x=1:M
    for y=1:N
        vN(x,y)=(v(x,y)/maxVal)*255;
    end
end

%calculo de umbral con otsu
level=graythresh(uint8(vN));

%resultado final
borde = im2bw(uint8(vN),.25);


figure('Name','Original gris')
imshow(grey);

figure('Name','dx')
imshow(uint8(dx));

figure('Name','dy')
imshow(uint8(dy));

figure('Name','Bordes')
imshow(borde);

end

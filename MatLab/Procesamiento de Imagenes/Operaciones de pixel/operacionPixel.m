function operacionPixel()
I=imread('dark.jpg');

R = I(:,:,1);
G = I(:,:,2);
B = I(:,:,3);

%muestro imagen original en escala de grises
figure('Name','Imagen en Gris')
grey = (R *.299 + G *.587 +  B *.114);
imshow(grey); 

[M,N]= size(grey);
mPixel=zeros(M,N,'uint8'); %matriz de pixeles

for x=1:M
    for y=1:N
        mPixel(x,y)= 255-I(x,y,2);
    end
end
figure('Name','Complemento')

imshow(mPixel);
for x=1:M
    for y=1:N
        mPixel(x,y)=I(x,y,1)+55;
    end
end
figure('Name','Más brillo')

imshow(mPixel);

for x=1:M
    for y=1:N
        mPixel(x,y)=I(x,y,1)-55;
    end
end
figure('Name','Menos brillo')

imshow(mPixel);

for x=1:M
    for y=1:N
        mPixel(x,y)=(I(x,y,1)*2);
    end
end
figure('Name','Más Contraste')

imshow(mPixel);

for x=1:M
    for y=1:N
        mPixel(x,y)=(I(x,y,1)*.2);
    end
end
figure('Name','Menos Contraste')
imshow(mPixel);


end
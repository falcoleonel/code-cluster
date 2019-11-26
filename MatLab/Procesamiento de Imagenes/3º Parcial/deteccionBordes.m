function deteccionBordes
I=imread('heartless.jpg');
grey=rgb2gray(I);
gris=double(grey);

[M,N]=size(gris);

dx=zeros(M,N);
dy=zeros(M,N);
v=zeros(M,N);
vN=zeros(M,N);

for x=1:M
    for y=1:N
        if(x==1||x==M)
            dx(x,y)=gris(x,y);
        else
            dx(x,y)=(gris(x+1,y)-gris(x-1,y))*0.5;
        end
    end
end

for x=1:M
    for y=1:N
        if(y==1||y==N)
            dy(x,y)=gris(x,y);
        else
            dy(x,y)=(gris(x,y+1)-gris(x,y-1))*0.5;
        end
    end
end

prewittdx=[-1, 0, 1; -1, 0, 1; -1, 0, 1];
prewittdy=[-1, -1, -1; 0, 0, 0; 1, 1, 1];

cdx=conv(dx,prewittdx);
cdy=conv(dy,prewittdy);

for x=1:M
    for y=1:N
        v(x,y)=sqrt((cdx(x,y)^2)+(cdy(x,y)^2));
    end
end

mr=max(v);
maxVal=max(mr);

for x=1:M
    for y=1:N
        vN(x,y)=(v(x,y)/maxVal)*255;
    end
end

level=graythresh(uint8(vN));
borde = imbinarize(uint8(vN),level);


figure('Name','Original gris')
imshow(grey);

figure('Name','dx')
imshow(cdx);

figure('Name','dy')
imshow(cdy);

figure('Name','Bordes')
imshow(borde);

end

function EqHistograma()
I=imread('imagen.jpg');
figure('Name','Original')
imshow(I(:,:,1));


[M,N]=size(I(:,:,1));
h=zeros(1,256);
for x=1:M
    for y=1:N
        i=I(x,y)+1;
        h(1,i)=h(1,i)+1;
    end
end
figure('Name','Histograma')
bar(h);

H(1)=0;
for i=2:256
    H(i)=H(i-1)+h(1,i);
end
figure('Name','Histograma acumulado')
bar(H);

eqI=I;
for x=1:M
    for y=1:N
        eqI(x,y)=round(H(I(x,y))*(255/(M*N)));
    end
end

figure('Name','Imagen ecualizada')
imshow(eqI);
eqh=zeros(1,256);
for x=1:M
    for y=1:N
        i=eqI(x,y)+1;
        eqh(1,i)=eqh(1,i)+1;
    end
end

figure('Name','Histograma ecualizado')
bar(eqh);

eqH(1)=0;
for i=2:256
    eqH(i)=eqH(i-1)+eqh(1,i);
end
figure('Name','Histograma Acumulado ecualizado')
bar(eqH);
end

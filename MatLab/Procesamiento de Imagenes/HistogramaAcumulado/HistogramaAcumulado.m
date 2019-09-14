function HistogramaAcumulado()
I=imread('Hornet.png');

R = I(:,:,1);
G = I(:,:,2);
B = I(:,:,3);

%muestro imagen original en escala de grises
figure('Name','Imagen en Gris')
grey = (R *.299 + G *.587 +  B *.114);
imshow(grey); 

[M,N] = size(grey);


h=zeros(1,256);
for x=1:M
    for y=1:N
        i=grey(x,y)+1;
        h(1,i)=h(1,i)+1;
    end
end
H(1)=0;
for i=2:256
    H(i)=H(i-1)+h(1,i);
end
figure('Name','Histograma Acumulado')
bar(H);

end

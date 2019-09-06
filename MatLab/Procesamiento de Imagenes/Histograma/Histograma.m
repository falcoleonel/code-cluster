function Histograma()
I=imread('CrystalPeak.png');
R = I(:,:,1);
G = I(:,:,2);
B = I(:,:,3);

%muestro imagen original en escala de grises
figure('Name','Imagen en Gris')
grey = (R *.299 + G *.587 +  B *.114);
imshow(grey); 

[M,N] = size(grey);
histo = zeros(1,256);%vector histograma

for x=1:M
    for y=1:N
        i=grey(x,y)+1;
        histo(i)=histo(i)+1;
    end
end
figure('Name','Histograma')
bar(histo);

figure('Name','Histograma MatLab')
imhist(grey);
end

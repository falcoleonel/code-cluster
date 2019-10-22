function PlanosRGB()
Origen=imread('CrystalPeak.png');

pRed = Origen(:,:,1); 
[MR,NR] = size(pRed);
ceros = zeros(MR,NR); %Máscara de ceros
iRoja = cat(3, pRed, ceros, ceros);

pGreen = Origen(:,:,2);
[MG,NG] = size(pGreen);
ceros = zeros(MG,NG);
iVerde = cat(3, ceros, pGreen, ceros);

pBlue = Origen(:,:,3);
[MB,NB] = size(pBlue);
ceros = zeros(MB,NB);
iAzul = cat(3, ceros, ceros, pBlue);
 
%Mostrar en Pantalla
figure('Name','Roja')
imshow(iRoja);
figure('Name','Verde')
imshow(iVerde);
figure('Name','Azul')
imshow(iAzul);
figure('Name','Origen')
imshow(Origen); 
end
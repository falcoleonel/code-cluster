function Transformaciones()
I=imread('interstellar.jpg');
R = I(:,:,1);
G = I(:,:,2);
B = I(:,:,3);

%muestro imagen original en escala de grises
figure('Name','Imagen en Gris')
grey = (R *.299 + G *.587 +  B *.114);
imshow(grey); 

[M,N]=size(I(:,:,1));

matriz=[[1,0,0;0,1,0,;0,0,1]];
for x=1:M
   for y=1:N
       pIde=[x,y,1]; %punto
       pIde=([x,y,1]*matriz); %alterando el punto con las matrices de transformacion
       iIdenti(pIde(1,1),pIde(1,2))=I(x,y);
   end
end
figure('Name','Identidad')
imshow(iIdenti);

matriz=[[2,0,0;0,2,0;0,0,1]];
for x=1:M
   for y=1:N
       pEscalaA=[x,y,1];
       pEscalaA=([x,y,1]*matriz);
      iEscalaA(pEscalaA(1,1),pEscalaA(1,2))=I(x,y);
   end
end
figure('Name','Escalamiento Amplificar')
imshow(iEscalaA);

matriz=[[.5,0,0;0,.5,0;0,0,1]];
for x=1:M
   for y=1:N
       pEscalaR=[x,y,1];
       pEscalaR=([x,y,1]*matriz);
       v=round(pEscalaR(1,1));
       w=round(pEscalaR(1,2));
      iEscalaR(v,w)=I(x,y);
   end
end
figure('Name','Escalamiento Reducir')
imshow(iEscalaR);



angulo= -.666;
matriz=[[cos(angulo),sin(angulo),0;-sin(angulo),cos(angulo),0;0,0,1]];
for x=1:M
    for y=1:N
        pRota=[x,y,1];
        pRota=([x,y,1]*matriz);
        %pixeles adicionales para agregar espacio
        v=round(pRota(1,1))+30;  
        w=round(pRota(1,2))+550;
       iRota(v,w)=I(x,y);
   end
end
figure('Name','Rotación')
imshow(iRota);

matriz=[1,0,0;0,1,0;50,150,1];
for x=1:M
    for y=1:N
        pTrasla=[x,y,1];
        pTrasla=([x,y,1]*matriz);
        v=pTrasla(1,1); %nuevo x
        w=pTrasla(1,2); %nuevo y
        iTrasla(v,w)=I(x,y);
    end
end
figure('Name','Traslación')
imshow(iTrasla);

matriz=[1,0,0;.666,1,0;0,0,1];
for x=1:M
    for y=1:N
        pVert=[x,y,1];
        pVert=([x,y,1]*matriz);
        v=round(pVert(1,1));
        w=round(pVert(1,2));
        iVert(v,w)=I(x,y);
    end
end
figure('Name','SV')
imshow(iVert);

matriz=[1,.666,0;0,1,0;0,0,1];
for x=1:M
    for y=1:N
        pHor=[x,y,1];
        pHor=([x,y,1]*matriz);
        v=round(pHor(1,1));
        w=round(pHor(1,2));
        iHor(v,w)=I(x,y);
    end
end
figure('Name','SH')
imshow(iHor);
end
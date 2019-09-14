function AjusteContraste()
I=imread('imagen.jpg');
[M,N]=size(I);
figure('Name','Original')
imshow(I);

h=zeros(1,256);
for x=1:M
    for y=1:N
        i=I(x,y)+1;
        h(1,i)=h(1,i)+1;
    end
end
figure('Name','Histograma')
bar(h);

Phigh=0;
Plow=0;
for i=1:256
    if(i>Phigh&&h(1,i)~=0)
        Phigh=i;
    end
    if(Plow==0&&h(1,i)~=0)
        Plow=i;
    end
    
end
I2=I;
Pmax=Phigh+100;
Pmin=Plow-100;
for x=1:M
    for y=1:N
        I2(x,y)=round(((I(x,y)-Plow)*((Pmax-Pmin)/(Phigh-Plow)))+Pmin);
    end
end
figure('Name','Ajuste de contraste')
imshow(I2);

h2=zeros(1,256);
for x=1:M
    for y=1:N
        i=I2(x,y)+1;
        h2(1,i)=h2(1,i)+1;
    end
end
figure('Name','Histograma')
bar(h2);
end
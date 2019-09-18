function Iluminacion()
I=imread('imagen.jpg');
[M,N]=size(I);

figure('Name','Original')
imshow(I)

h=zeros(1,256);
for x=1:M
    for y=1:N
        i=I(x,y)+1;
        h(1,i)=h(1,i)+1;
    end
end

figure('Name','Histograma')
bar(h);


h2=zeros(1,256);
for i=1:256
    %Sacar la iluminacion
    h2(1,i)=(((1/(M*N)))*i)*h(1,i);
end
figure('Name','Iluminación')
plot(h2);
end

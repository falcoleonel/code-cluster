function Dinamica()
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

h2=0;
for i=1:256
    if(h(1,i)~=0)
        h2=h2+1;
    end
end
figure('Name','Contraste')
stem(h2);
end

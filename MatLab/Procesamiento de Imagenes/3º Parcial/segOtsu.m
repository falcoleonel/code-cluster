
close all;clear all;clc

I=imread('lenacolor.png');
figure(1),imshow(I);
gris= rgb2gray(I);


n=imhist(gris); 
N=sum(n);
max=0;

for i=1:256
     %Probabilidad para cada nivel de intensidad
     P(i)=n(i)/N; 
end

for k = 1:255     
    %Probabilidad para la class 1
    w0=sum(P(1:k)); 
    %Probabilidad para la class 2
    w1=sum(P(k+1:256)); 
    
    % media clase 1
    u0=dot([0:k-1],P(1:k))/w0; 
    % media clase 2
    u1=dot([k:255],P(k+1:256))/w1; 
    
    % calcula varianza entre clases
    sigma=w0*w1*((u1-u0)^2);
    
    if sigma>max 
        max=sigma; 
        % el punto de segmentacion se da por la maxima varianza entre las
        % clases
        umbral=k; 
    end
end

% Metodo de matlab
% level = graythresh(I);
% otsuML = im2bw(level);
% figure(4),imshow(bw);

%Muestra el histograma con el umbral resaltado
figure(2),imhist(gris); 
hold on;
line([118, 118], ylim, 'LineWidth', 2 ,'Color',[0.4 0.1 0.5]);



%Muestra resultado en imagen binaria
imgsegmentada=im2bw(gris,umbral/255); 
figure(3),imshow(imgsegmentada); 



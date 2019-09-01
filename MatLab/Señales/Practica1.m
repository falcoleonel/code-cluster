%Periodos de tiempo
T=0.001;
T2=0.1;
%Rangos de tiempo
t=[-1:T:1];
t2=[-1:T2:1];
t3=[-2:T2:2];
n=[-10:10];
syms t4;
%Declaración de funciones
x=exp(-2+3*cos(20*pi*t)+sin(40*pi*t));
cuad=square(2*pi*t2);
saw=sawtooth(2*pi*t2)
sng=exp(t3-0.5);
eu=[zeros(1, 10), ones(1, 11)];
eupar=(eu+subs(eu,t4,-t4))/2;
euimpar=(eu-subs(eu,t4,-t4))/2;
%Impresión
subplot(2,2,1);plot(t,x,'r');
subplot(2,2,2);plot(t2,cuad);
title('2° subplot');
subplot(2,2,3);plot(t2,saw);
grid;
subplot(2,2,4);plot(t3,sng);
figure(2);
title('Escalón Unitario');
subplot(2,2,1);plot(t2,eu);
hold on;
stem(n,eupar);
stem(n,euimpar);
subplot(2,2,2);plot(t2,eu);
subplot(2,2,3);stem(n,eupar);
subplot(2,2,4);stem(n,euimpar);
clear;

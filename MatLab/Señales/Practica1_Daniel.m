clear;
T = 0.0001;
t = [-1 :T:1];
T1 = 0.1;
t1 = [-1:T1:1];
T2= 0.1;
t2 = [-2:T2:2];

x=exp(-2+3*cos(20*pi*t)+sin(40*pi*t));
cuad=square(2*pi*t1);
saw=sawtooth(2*pi*t1);
sng=exp(t2-0.5);

subplot(2,2,1);plot(t,x,'r');
subplot(2,2,2);plot(t1,cuad);
title('2°do sub');
subplot(2,2,3);plot(t1,saw);
grid;
subplot(2,2,4);plot(t2,sng);

n=[-10:10];
syms t4;
escalon=[zeros(1, 10), ones(1, 11)];
espar=(escalon+subs(escalon,t4,-t4))/2;
esimpar=(escalon-subs(escalon,t4,-t4))/2;

figure(2);
subplot(2,2,1);plot(t1,escalon);
hold on;
stem(n,espar);
stem(n,esimpar);

subplot(2,2,2);plot(t1,escalon);
subplot(2,2,3);stem(n,espar);
subplot(2,2,4);stem(n,esimpar);
find(t<0.005 & t> -0.005)
clear;

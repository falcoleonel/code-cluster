clear;
n=-5:30;
x1=(1.*(n==0)+2.*((n>=1)&(n<=4))+0);

h1=-1.*(n==0)+1.*(n==1)+2.*(n==2)+0;

h2=1.*(n==1)+2.*(n==2)+3.*(n==3)+-1.*(n==4)+0;

y1=conv(x1,h1)+conv(x1,h2);
y2=conv(x1,(h1+h2));

z1=conv(x1,conv(h1,h2));
z2=conv(conv(x1,h1),h2);
z3=conv(conv(x1,h2),x1);


figure('Name','Ejercicio a');
subplot(1,2,1); stem(n,y1(1:length(n)),'g');
subplot(1,2,2); stem(n,y2(1:length(n)),'g');

figure('Name','Ejercicio b');
subplot(1,3,1); stem(n,z1(1:length(n)),'r');
subplot(1,3,2); stem(n,z2(1:length(n)),'r');
subplot(1,3,3); stem(n,z2(1:length(n)),'r');
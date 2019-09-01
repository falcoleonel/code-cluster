h= -5:0.1:10;
%Escalon Unitario
u=[zeros(1, 10), ones(1, 11)]; 
t= [-10: 1:10];
subplot(2,3,1);plot(t,u);
grid
axis([-5 10 -0.1 1.1]);
title('Escalon Unitario')
%Exponencial Real
y = exp(-.25*h);
subplot(2,3,2);plot(h,y);
grid
title('Exponencial Real')
%Exponencial Real que empieza en t = 0
u = @(h) +(h>=0);                          
f = @(h) exp(-.25*h);
g = @(h) f(h).*u(h);
subplot(2,3,3);plot(h,g(h));
grid
axis([-5 10 -0.1 1.1]);
title('Exponencial Real t = 0')
%Sinusoide Amortiguada
g=sin(6*3.146/5*h).*exp(-0.25*h);
subplot(2,3,4);plot(h,g);
grid
title('Sinusoide Amortiguada')
%Sinusoide Amortiguada que comienza en t = 0
m = @(h) +(h>=0);                          
n = @(h) (exp(-.25*h).*sin(6*3.146/5*h));
d = @(h) n(h).*m(h);
subplot(2,3,5);plot(h,d(h));
grid
title('Sinusoide Amortiguada t = 0')
%Codigo
t = -2:0.05:3;
L = length(t);
x = zeros (1,L);
p1 = find (t==-1);
p2 = find (t==0);
p3 = find (t==1);
p4 = find (t==2);
x(p1:p2) = (t(p1:p2)+1)*-1;
x(p2:p3) = 2;
x(p3:p4) = 1;

subplot(2,3,1);plot (t,x);
grid
axis([-2 3 -1.1 2.1]);
title('señal x(t)')

%traslacion
%modo a
t1= t-2;
x1=x;
%plot (t1,x1);
subplot(2,3,2);plot(t1,x1);
grid
axis([-4.5 2.5 -1.1 2.1]);
title('x1(t)=(t-2)')

%escalado
t3= t/3;
x3=x;
%plot (t3,x3);
subplot(2,3,3);plot(t3,x3);
grid
axis([-3.5 2.5 -1.1 2.1]);
title('x2(t)=(t/3)')

%reflexion
t4 = -t;
x4 = x;
final = length(t4);
t4 = t4(final:-1:1);
x4 = x4(final:-1:1);
subplot(2,3,4);plot(t4,x4);
grid
axis([-3.5 2.5 -1.1 2.1]);
title('x3(t)=(-t)')

%restar
Lextra = length(t1)- length(t);
t = t1;
x = [x zeros(1,Lextra)];
t5 = t;
x5 = x - x1;
subplot(2,3,5);plot(t5,x5);
grid
axis([-3.5 2.5 -3 5]);
title('x4(t)=x(t)-x1(t)')

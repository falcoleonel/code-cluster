
%codigo
% Calcula y(n)
y0=input (' y ');
I=input (' constante ');
x=input (' entrada constante ');
y=[]; %se define a y como un vector vacío
y(1)=(1+(I/12))*y0-x;
for n=2:360
    y(n)=(1 + (I/12))*y(n-1)-x;
    if y(n) < 0, break, end
end
L = length(y);
subplot(1,2,1);plot(y,'g');
title('y(n)=(1 + (I/12))*y(n-1)-x');

%funcion m[n] = e(n)- e(n-1)-m(n-1)

m = [];
m0=input (' m ');
m(1) = 0-1-m0;
for n = 2:360
    b = mod(n,2);
    if b==0
        e=1;
        e2=0;
    else
        e=0;
        e2=1;
    end
    m(n) = e-e2-m(n-1);
end
subplot(1,2,2);plot(m,'r');
title('m[n] = e(n)- e(n-1)-m(n-1)');


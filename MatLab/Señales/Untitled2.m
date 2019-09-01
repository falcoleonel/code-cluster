
t=-10:0.05:20;
u = @(t) +(t>=0);                           % Create A Function For ‘u(t)’
f = @(t) (exp(-.2*t).*sin(pi*t));
g = @(t) f(t).*u(t);
figure(4)
plot(t,g(t))
grid
axis([-10 20 -1 1]);
ylabel('g(t)') 
xlabel('Time (sec)')
title('g(t)=f(t)*u(t)')
hold off;

T = 0.05;
t = -2 : T : 3;
L = length(t);

p1 = find(t==-1);
p2 = find(t==0);
p3 = find(t==1);
p4 = find(t==2);

x = zeros(1, L);
x(p1:p2) = -t(p1:p2) - 1;
x(p2:p3) = 2;
x(p3:p4) = 1;

t1 = t-2;
x1 = x;

t2 = t/3;
x2 = x;

t3 = -t;
x3 = x;
final = length(t3);
t3 = t3(final : -1 : 1);
x3 = x3(final : -1 : 1);

t4 = t;
x4 = x-x1;

figure;
plot(t,x);

figure;
plot(t1,x1);

figure;
plot(t2,x2);

figure;
plot(t3,x3);

figure;
plot(t4,x4);
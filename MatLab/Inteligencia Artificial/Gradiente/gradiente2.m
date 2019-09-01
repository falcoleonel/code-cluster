
%Gradiente Descendiente ejercicio de Optimizacion 2
%Daniel Bayardo Ramirez


clear all

%funcion
f=@(x) (-(15*x^2 - x^3));
range=[1 15];
k=0;
niter=10;

%tamaño de la perturbación
hstep = 0.01;
alfa=0.01;

%punto inicial
xrange=range(2)-range(1);
x1=rand*xrange+range(1);
%grafica de la funcion
figure
ejex=linspace(range(2),range(1),100);
ejey=[];
for i= 1:length(ejex)
   ejey(i)=f(ejex(i));
end

while (k<niter)
    zn=f(x1);
    vx=x1+hstep;
    gx=(f(vx)-zn)/hstep;
    figure(1)
    plot(ejex,ejey); hold on;
    plot(x1,zn,'.','markersize',20);
    pause(0.2);
    x1=x1-alfa*gx;
    k=k+1;
end

%imprime resultados finales
y = 15 - x1;
fprintf("Iteraciones: %d\n", k);
fprintf("X: %8g\n", x1);
fprintf("Y: %8g\n", y);
fprintf("Volumen: %8g\n", -zn);

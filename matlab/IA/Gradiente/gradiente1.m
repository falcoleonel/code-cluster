
%Gradiente Descendiente ejercicio de Optimizacion 1
%Daniel Bayardo Ramirez
clear all
%funcion
f=@(x) (-(x*(50-x*3/2)+(x^2*sqrt(3)/4)));
range=[1 34];
k=0;
niter=10;
%tamaño de la perturbación
hstep = 0.01;
%se fija el paso de optimización
alfa=0.15;
%generación del punto inicial
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
    pause(0.2)
    x1=x1-alfa*gx;
    k=k+1;
end

%imprime resultados finales
y = 34 - x1;
fprintf("Iteraciones: %d\n", k);
fprintf("X: %g\n", x1);
fprintf("Y: %g\n", y);
fprintf("Area: %g\n", -zn);

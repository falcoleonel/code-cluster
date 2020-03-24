function filtrobox(tam)

filtro=ones(tam,tam)/(tam*tam);

img=convolucion('imagen.jpg',filtro);
figure('Name','Filtro box','NumberTitle','off');
imshow(img);

end
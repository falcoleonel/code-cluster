function box(entrada)

filtro=ones(entrada,entrada)/(entrada*entrada);

img=convolucion('knight.jpg',filtro);

figure('Name','Filtro box');
imshow(img);

end
function medio(Tam)
resultante = nolineal('imagen.jpg',Tam,'med');
figure('Name','Filto medio','NumberTitle','off')
imshow(resultante);
end
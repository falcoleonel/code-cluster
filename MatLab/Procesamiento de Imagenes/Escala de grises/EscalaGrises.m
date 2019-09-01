function EscalaGrises(conv)
I=imread('IGotThisCovered.jpg');
R = I(:,:,1);
G = I(:,:,2);
B = I(:,:,3);
figure('Name','Origen')
        imshow(I);
switch conv
    case 'canales'
        figure('Name','Canal Rojo')
        imshow(R);
    
        figure('Name','Canal Verde')
        imshow(G);
   
        figure('Name','Canal Azul')
        imshow(B);
        
    case 'sombras'
        numSombras = 11;
        convFactor = 255/(numSombras-1);
        average = (R+G+B)/3;
        
        Sombras = round(((average/convFactor)+0.5)*convFactor);
        figure('Name','Sombras grises')
        imshow(Sombras);
        
    case 'desatura'
        [M,N]=size(R);
        for x=1:M
            for y=1:N
                desa(x,y) = (max([I(x,y,1) I(x,y,2) I(x,y,3)])+min([I(x,y,1) I(x,y,2) I(x,y,3)]))/2;
            end
        end
        figure('Name','Desaturación')
        imshow(desa);
        
    case 'descompo'
        [M,N]=size(R);
        for x=1:M
            for y=1:N
                Ma(x,y) = max([I(x,y,1) I(x,y,2) I(x,y,3)]);
            end
        end
        figure('Name','Máxima')
        imshow(Ma);
    
        for x=1:M
            for y=1:N
                mi(x,y) = min([I(x,y,1) I(x,y,2) I(x,y,3)]);
            end
        end
        figure('Name','Mínima')
        imshow(mi);  
        
    case 'promedio'
        prom = (R+G+B/3);
        figure('Name','Promedio')
        imshow(prom);  
        
    case 'luma'
        g1 = (R *.3 + G *.59 +  B *.11);
        figure('Name','Photoshop')
        imshow(g1);      
        
        g2 = (R *.2126 + G *.7152 +  B *.0722);
        figure('Name','BT.709')
        imshow(g2);    
        
        g3 = (R *.299 + G *.587 +  B *.114);
        figure('Name','BT.601')
        imshow(g3);   
        
    otherwise
        imshow(I);
        
end
end
# Daniel Bayardo Ramirez 16310048
import matplotlib,sys
from matplotlib import pyplot as plt
import numpy as np

	# retorna salida del perceptron
def predecir(entrada,pesos):
	salida=0.0
	for e,p in zip(entrada,pesos):
		salida += e*p 
	return 1.0 if salida>0.0 else 0.0

	# logica para armar la grafica
def plot(matriz,pesos=None,title="Matriz de prediciones"):

		fig,ax = plt.subplots()
		ax.set_title(title)
		ax.set_xlabel("x1")
		ax.set_ylabel("x2")

		if pesos!=None:
			map_min=0.0
			map_max=1.1
			y_res=0.001
			x_res=0.001
			ys=np.arange(map_min,map_max,y_res)
			xs=np.arange(map_min,map_max,x_res)
			zs=[]
			for cur_y in np.arange(map_min,map_max,y_res):
				for cur_x in np.arange(map_min,map_max,x_res):
					zs.append(predecir([1.0,cur_x,cur_y],pesos))
			xs,ys=np.meshgrid(xs,ys)
			zs=np.array(zs)
			zs = zs.reshape(xs.shape)
			cp=plt.contourf(xs,ys,zs,levels=[-1,-0.0001,0,1],colors=('green','purple'),alpha=0.1)

		c1_data=[[],[]]
		c0_data=[[],[]]
		for i in range(len(matriz)):
			cur_i1 = matriz[i][1]
			cur_i2 = matriz[i][2]
			cur_y  = matriz[i][-1]
			if cur_y==1:
				c1_data[0].append(cur_i1)
				c1_data[1].append(cur_i2)
			else:
				c0_data[0].append(cur_i1)
				c0_data[1].append(cur_i2)

		plt.xticks(np.arange(0.0,1.1,0.1))
		plt.yticks(np.arange(0.0,1.1,0.1))
		plt.xlim(0,1.05)
		plt.ylim(0,1.05)

		c0s = plt.scatter(c0_data[0],c0_data[1],s=40.0,c='purple',label='Class -1')
		c1s = plt.scatter(c1_data[0],c1_data[1],s=40.0,c='green',label='Class 1')

		plt.legend(fontsize=10,loc=1)
		plt.show()
		return

	# retorna el conjunto de predicciones hechas con los datosde entrenamiento
def exactitud(matriz,pesos):
	num_correct = 0.0
	preds       = []
	for i in range(len(matriz)):
			# obtener la clasificacion predecida
		pred   = predecir(matriz[i][:-1],pesos) 
		preds.append(pred)
		if pred==matriz[i][-1]: num_correct+=1.0 
	print("Predicciones:",preds)
	return num_correct/float(len(matriz))

	# funcion donde se entrena ajustando pesos 
def entrenar_pesos(matriz,pesos, nb_ite=10, factorA=0.5, graficar=False, paro=True, consola=True):
	for ite in range(nb_ite):
		cur_acc = exactitud(matriz,pesos)
		print("\nIteración %d \nPesos: "%ite,pesos[1:])
		print("Bias: ",pesos[0])
		print("\nExactitud: ",cur_acc)
		
			# criterio de paro
		if cur_acc==1.0 and paro: break 
		if graficar: plot(matriz,pesos,title="Iteración %d"%ite)
		
		for i in range(len(matriz)):
				# obtiene la clasificacion predecida
			prediccion = predecir(matriz[i][:-1],pesos) 
				# obtiene el error con base en la clasificacion real
			error = matriz[i][-1]-prediccion		 
			if consola: sys.stdout.write("Entrenando datos en el indice %d...\n"%(i))
				# calcular nuevos pesos
			for j in range(len(pesos)): 				 
				if consola: sys.stdout.write("\tPeso[%d]: %0.4f --> "%(j,pesos[j]))
				pesos[j] = pesos[j]+(factorA*error*matriz[i][j]) 
				if consola: sys.stdout.write("%0.4f\n"%(pesos[j]))

	plot(matriz,pesos,title="Iteracion final")
	return pesos

 # funcion principal del algoritmo
def perceptron():
		# parametros iniciales
	nb_ite		      = 30
	factorA  		  = .4
	graficar_cada_ite = False
	critero_de_paro   = True
	texto_a_consola	  = False

		 	# 	Bias 	i1 		i2 	clase esperada
	matriz = [	[1.00,	0.0625,	0.0624,	0.0],
				[1.00,	0.2,	0.058,	0.0],
				[1.00,	0.77,	0.80,	1.0],
				[1.00,	0.75,	0.0625,	0.0],
				[1.00,	0.380,	0.66,	1.0],
				[1.00,	0.40,	0.99,	1.0],
				[1.00,	0.375,	0.07,	0.0],
				[1.00,  0.85,   0.90,   1.0],
				[1.00,  0.96,   0.68,   1.0],
				[1.00,	0.1,	0.39,	0.0]]

		# bias y pesos iniciales
	pesosIniciales = [.5667, 1.25, -2.75]

		# pasamos parametros para hacer el entrenamiento con estos datos
	pesosCalculados = entrenar_pesos(matriz,
						pesos=pesosIniciales,
						nb_ite=nb_ite,
						factorA=factorA,
						graficar=graficar_cada_ite,
						paro=critero_de_paro,
						consola=texto_a_consola)

	 # mostrar nuevos valores para predecir
	print("\nNuevo bias: ", pesosCalculados[0])
	print("\nNuevos pesos: ", pesosCalculados[1:])

	 	
	res = (input("Desea clasificar datos? (y/n): "))
	if(res=='y' ):
		while True:
			entradas_string = (input("\nIngrese un nuevo dato a clasificar separando con espacio su x1 y x2: "))
			entradas = entradas_string.split()
			 # lista necesaria para agregar entrada a la grafica
			dato = []
			dato.append(1.00)
			for entrada in entradas:
				dato.append(float(entrada))
				# se calcula con los datos obtenidos del entrenamiento 
			clase = predecir(dato, pesosCalculados)
			if (clase > 0):
				print("\nEl dato pertenece a la clase 1")
			else:
				print("\nEl dato pertenece a la clase -1")
			dato.append(clase)
			matriz.append(dato)
			 # graficamos con el dato nuevo agregado
			plot(matriz,pesosCalculados,title="\nNuevo Dato agregado")		
			termino = (input("\nDesea continuar? (y/n): "))
			if(termino!='y'):
				break
		
if __name__ == '__main__':
    perceptron()

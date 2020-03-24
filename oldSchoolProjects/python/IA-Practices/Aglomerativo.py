import numpy as np
from math import sqrt

class Cluster:
    def __init__(self, puntos):
        self.puntos = puntos

class Aglomerativo:
    def __init__(self):
        self.MatrizIni = [] #Matriz de distancias inicial
        self.MatrizDis = [] #Matriz Iterada

    def datos_entrada(self, muestras=50):
        """
        Leer datos de entrada y normalizar para meter al Universo de clusters
        """
        self.Clusters = []
        print()
        print("Universo Crudo: ", mue, "\n")
        #normalizamos los datos    
        mean = np.mean(mue, axis=0, keepdims=True)
        std = np.std(mue, axis=0, keepdims=True)
        normi = (mue-mean)/std

        print("Media: ", mean , "\n")
        print("Desviacion estandar: ", std , "\n")
        #Se agrega cada punto como un cluster
        for muestra in normi:
            self.Clusters.append(Cluster(muestra))
        
        print("Clusters: ")
        for c in self.Clusters:
            print (c.puntos)
        #Definimos la matriz de distancias inicial
        self.MatrizInicial = self.matrizDistancias(self.Clusters)
        print ("\n", "Matriz Inicial: ", self.MatrizInicial, "\n")

    def matrizDistancias(self, Clusters):
        """
        Definir matriz de distancias
        """
        def dis(a, b):
            d = [a[0] - b[0], a[1] - b[1]]
            return sqrt(d[0] * d[0] + d[1] * d[1]) 

        Dist = []
        for clus1 in Clusters:
            for clus2 in Clusters:
                Dist.append(dis(clus1.puntos, clus2.puntos)) 
        return Dist
       

if __name__ == '__main__':
    print()
    R = int(input("Ingrese la cantidad de muestras:"))
    C = 2
    mue = []
    print("Ingrese los valores para cada campo:")
    for i in range(R):
        print("\n", "Muestra", i+1 , "\n")
        a = []
        for j in range(C):
            a.append(float(input()))
        mue.append(a)
    aglo = Aglomerativo()
    aglo.datos_entrada(muestras=mue)

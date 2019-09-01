
import numpy as np
from math import sqrt
import string


class Cluster:
    def __init__(self):
        self.indicador = ""
        self.puntos = []


class Aglomerativo:
    def Entrada(self, muestras=50):
        """
        Leer datos de entrada y normalizar para meter al Universo de clusters y ejecutar algoritmo aglomerativo
        """
        self.Clusters = []

        # lista de indicadores
        indices = list(string.ascii_uppercase)

        print("\nUniverso en Crudo: ")
        for m in muestras:
            print(m)
        print()
        # Normalizar
        media = np.mean(muestras, axis=0)
        desviacion = np.std(muestras, axis=0)
        normalizados = ((muestras-media)/desviacion).tolist()
        print("Media: ", media)
        print("Desviacion estandar: ", desviacion)
        # Se agrega cada punto como parte de un nuevo cluster por punto
        for punto in normalizados:
            nuevo = Cluster()
            nuevo.puntos = ([punto])
            nuevo.indicador = indices[0]
            indices.remove(indices[0])
            self.Clusters.append(nuevo)
        print("Clusters iniciales: ")
        for c in self.Clusters:
            print(c.indicador, " - ", c.puntos)
        print()
        # Correr proceso de clusterizacion
        self.Unir(self.Clusters)

    def SingleLink(self, ClusterA, ClusterB):
        """
        Regresa la distancia minima entre dos clusters
        """
        Dist = []

        def dis(a, b):
            d = [a[0] - b[0], a[1] - b[1]]
            return sqrt(d[0] * d[0] + d[1] * d[1])
        # distancia entre dos puntos
        for p1 in range(len(ClusterA.puntos)):
            for p2 in range(len(ClusterB.puntos)):
                Dist.append(dis(ClusterA.puntos[p1], ClusterB.puntos[p2]))
        print("\n     Distancia entre los puntos mas cercanos de los grupos:",
              ClusterA.indicador, " y ", ClusterB.indicador, "es de:", min(Dist))
        return min(Dist)

    def MatrizDistancias(self, Clusters):
        "Metodo que agrupa la matriz de distancias en funcion de el metodo escogido para sacar las distancias minimas"
        Distancias = []
        for i in range(len(self.Clusters)-1):
            for j in range(i+1, len(self.Clusters)):
                DistMez = []
                x = (self.SingleLink(
                    self.Clusters[i], self.Clusters[j]))
                DistMez.append(x)
                DistMez.append(self.Clusters[i].puntos)
                DistMez.append(self.Clusters[i].indicador)
                DistMez.append(self.Clusters[j].puntos)
                DistMez.append(self.Clusters[j].indicador)
                # Guarda la informacion de cada distanca en una matriz de distancias
                Distancias.append(DistMez)
        return Distancias

    def Unir(self, Clusters):
        "Metodo donde se altera el universo de clusters"
        while len(self.Clusters) > 1:
            DistMez = self.MatrizDistancias(self.Clusters)
            # crear cluster de la union de los dos mas cercanos
            minimo = min(DistMez)
            print("\n-------------------------------------------------------------------")
            print("Distancia minima de:",
                  minimo[0], " Encontrada entre:", minimo[2], "y", minimo[4], "\n")
            # quitar los dos clusters que se usaron para hacer la union
            print("\nClusters a remover: ")
            for c in self.Clusters:
                if(c.puntos == minimo[1]):
                    ind1 = (c.indicador)
                    #print(c.indicador, " - ", c.puntos)
                    print(c.indicador)
                    self.Clusters.remove(c)
            for c in self.Clusters:
                if(c.puntos == minimo[3]):
                    ind2 = (c.indicador)
                    #print(c.indicador, " - ", c.puntos)
                    print(c.indicador)
                    self.Clusters.remove(c)

            nuevo = Cluster()
            nuevo.puntos = minimo[1]+minimo[3]
            nuevo.indicador = ind1+ind2
            self.Clusters.append(nuevo)
            # imprimir todos los clusters actuales
            print("\nClusters Actualizados: ")
            for c in self.Clusters:
                #print(c.indicador, " - ", c.puntos)
                print(c.indicador)
            print()


if __name__ == '__main__':
    print()
    R = int(input("Ingrese la cantidad de muestras:"))
    C = 2
    muestras = []
    print("Ingrese los valores para cada campo:")
    for i in range(R):
        print("\n", "Muestra", i+1, "\n")
        a = []
        for j in range(C):
            a.append(float(input()))
        muestras.append(a)
    aglo = Aglomerativo()
    aglo.Entrada(muestras=muestras)

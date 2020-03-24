import numpy as np
import operator
import matplotlib.pyplot as plt
import matplotlib.cm as cm


def rand(): return np.random.randint(1, 100)


class Centroide:

    def __init__(self, pos):
        self.pos = pos
        self.puntos = []
        self.previos = []
        self.color = None


class KMeans:

    def __init__(self, n_centroides=3, iteraciones=10):
        self.n_centroides = n_centroides
        self.iteraciones = iteraciones
        self.centroides = []

        # genera los centroides iniciales

        for _ in range(n_centroides):
            self.centroides.append(
                Centroide(np.array([rand(), rand()])))
            # Centroide(np.array([rand(), rand(), rand()])))

        # asigna un color aleatorio a cada centroide
        colores = cm.rainbow(np.linspace(0, 1, len(self.centroides)))
        for i, c in enumerate(self.centroides):
            c.color = colores[i]

    def datos_aleatorios(self, muestras=50):
        """
        Genera datos aleatorios para meter al Universo
        """
        #self.Universo = [[rand(), rand()] for _ in range(muestras)]
        #self.Universo = [[rand(), rand(), rand()] for _ in range(muestras)]
        self.Universo = []

        self.Universo.append([10, 45])
        self.Universo.append([17.95, 90])
        self.Universo.append([34, 85])
        self.Universo.append([23, 62])
        self.Universo.append([94, 70])
        self.Universo.append([29.2, 90])
        self.Universo.append([11, 65])
        self.Universo.append([3.14, 82])
        self.Universo.append([74, 90])
        self.Universo.append([22, 81])
        self.Universo.append([61, 25])
        print("Universo: ", self.Universo)

    def acomodar(self):
        """
        acomoda los puntos en el Universo
        los asigna a centroides
        actualiza los centroides de acuerdo al mean de los puntos asignados
        """
        self.n_iters = 0
        acomodar = False
        for x in range(self.iteraciones):
            for punto in self.Universo:
                closest = self.asignar_centroide(punto)
                closest.puntos.append(punto)

            # si no cambio el numero de puntos asignados al centroide romper el ciclo
            if len([c for c in self.centroides if c.puntos == c.previos]) == self.n_centroides:
                acomodar = True
                break
            else:
                self._actualizar_centroides()
            if acomodar == True:
                self._actualizar_centroides(reset=False)
            if not acomodar:
                self.n_iters += 1

    def asignar_centroide(self, punto):
        """
        Retorna el centroide mas cercano
        """
        distancias = {}
        for centroide in self.centroides:
            distancias[centroide] = np.linalg.norm(centroide.pos - punto)
        mas_cercano = min(distancias.items(), key=operator.itemgetter(1))[0]
        return mas_cercano

    def _actualizar_centroides(self, reset=True):
        """
        Actualiza los centroides
        """
        for centroide in self.centroides:
            centroide.previos = centroide.puntos
            x_cor = [x[0] for x in centroide.puntos]
            y_cor = [y[1] for y in centroide.puntos]
            #z_cor = [z[2] for z in centroide.puntos]
            try:
                centroide.pos[0] = sum(x_cor)/len(x_cor)

                centroide.pos[1] = sum(y_cor)/len(y_cor)
                #centroide.pos[2] = sum(z_cor)/len(z_cor)
            except:
                pass

            if reset:
                centroide.puntos = []

    def show(self):
        """
        Muestra el cluster y salva una captura de la grafica.
        """

        for i, c in enumerate(self.centroides):
            plt.scatter(c.pos[0], c.pos[1], marker='D', color=c.color, s=75)
            x_cors = [x[0] for x in c.puntos]
            y_cors = [y[1] for y in c.puntos]
            plt.scatter(x_cors, y_cors, marker='1', color=c.color)

        titulo = 'Practica K-Means'
        plt.xlabel('X')
        plt.ylabel('Y')
        plt.title(titulo)
        plt.savefig('{}.png'.format(titulo))
        plt.show()


if __name__ == '__main__':

    mue = input("Cuantas muestras deseas usar? ")
    cent = input("Cuantos centroides deseas usar? ")
    ite = input("Cuantas iteraciones correr hasta parar? ")
    print("-- Iteraciones: ", ite, " --")
    type(ite)
    kmeans = KMeans(n_centroides=int(cent), iteraciones=int(ite))
    kmeans.datos_aleatorios(muestras=int(mue))
    kmeans.acomodar()
    print('Iteraciones: {0}'.format(kmeans.n_iters))
    kmeans.show()

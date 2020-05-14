
mensaje = "Introduzca una lista de numeros (separados por comas): \n"

lista = [ int(n.strip()) for n in input(mensaje).split(',') ]
print("\nLista ingresada:", lista)

mid = int(len(lista) / 2)
print("\n Los 2 elementos a la mitad de la lista son:", lista[mid-1:mid+1])

print("\n El primer y último elemento de la lista son: %s, %s" % (lista[0], lista[-1]))

lista = 2 * lista
print("\n La misma lista!:", lista)

lista.sort()
print("\n La lista duplicada pero ordenada:", lista)

lista = lista[::-1]
print("\n La lista duplicada ordenada descendentemente:", lista)

def cubo(lista):
    return [ n**3 for n in lista ]

print("\n El cubo de los elementos de la lista anterior:", cubo(lista))

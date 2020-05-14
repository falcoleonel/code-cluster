
def primer_Upper(cadena):
    return cadena[0].isupper()


def canti_palabras(cadena):
    return len(cadena.split(' '))


def arr_palabras(cadena):
    return cadena.split(' ')


def inverso(cadena):
    return cadena[::-1]


def mays_ult_letra_palab(cadena):
    return ' '.join([s[:-1] + s[-1].upper() for s in cadena.split(' ')])


cadena = input("Ingrese una cadena: ")
print("La primera letra de la cadena %s es mayúscula." %
      ("si" if primer_Upper(cadena) else "no"))
print("La cadena tiene en total %s palabras." % canti_palabras(cadena))
print("Las palabras de la cadena son:", arr_palabras(cadena))
print("La misma cadena pero volteada da:", inverso(cadena))
print("La misma cadena, pero con la última letra de sus palabras en mayúsculas:",
      mays_ult_letra_palab(cadena))

import re


def valido(cad: str) -> bool:
    return re.match(r"[^@]+@[^@]+\.[^@]+", cad)


correo = input("Ingrese un correo: ")
print("El correo ingresado %s es valido." %
      ("si" if valido(correo) else "no"))

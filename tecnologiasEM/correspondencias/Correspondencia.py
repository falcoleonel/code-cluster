import os
import json
import locale
import random
import string
from datetime import datetime

''' 
Inspirado en el aviso que me llego de ticketmaster por un concierto esperado que fue cancelado :c
'''

if not os.path.exists('Reembolsos'):
    os.mkdir('Reembolsos')

with open('Aviso_Reembolso_Inminente_plantilla.txt', 'r') as template:
    avisoReembolso = template.read()

with open('Clientes.json', 'r') as archivo_clientes:
    Clientes = json.load(archivo_clientes)

locale.setlocale(locale.LC_TIME, 'es_ES.utf8')
fechaActual = datetime.now().strftime('%A %d de %B de %Y')


for cliente in Clientes:
    cliente['fecha'] = fechaActual
    cliente['codigoEvento'] = ''.join(random.choices(
        string.ascii_letters + string.digits, k=12))
    cliente['folio'] = random.randint(10000, 99999)
    with open('Reembolsos/Aviso reembolso ' + cliente['email'] + '.txt', 'w') as aviso:
        aviso.write(avisoReembolso.format(**cliente))

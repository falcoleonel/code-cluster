import os
import tkinter as tk
from tkinter import messagebox
import json
from functools import partial
import locale
import random
import string
from datetime import datetime
import docx
from fpdf import FPDF

''' 
Inspirado en el aviso que me llego de ticketmaster por un concierto esperado que fue cancelado :c
'''


def retorno():  # Regresar a pantalla principal
    list = app.grid_slaves()
    listpack = app.pack_slaves()

    for e in list:
        e.destroy()

    for e in listpack:
        e.destroy()


# Registrar cliente
def registrarCliente():
    retorno()
    fields = ["Nombre:", "Apellidos:", "Titulo Evento:", "Fecha Programada:", "Asientos Reservados:", "Metodo de pago:",
              "Domicilio:", "Telefono:", "Email:"]

    crow = 0
    ccol = 0
    for f in fields:
        tk.Label(app, text=f).grid(row=crow, column=ccol)
        crow = crow+1

    nombre = tk.Entry(app)
    apellidos = tk.Entry(app)
    tituloEvento = tk.Entry(app)
    fechaEvento = tk.Entry(app)
    asientos = tk.Entry(app)
    metodoPago = tk.Entry(app)
    telefono = tk.Entry(app)
    email = tk.Entry(app)
    domicilio = tk.Entry(app)

    nombre.grid(row=0, column=1)
    apellidos.grid(row=1, column=1)
    tituloEvento.grid(row=2, column=1)
    fechaEvento.grid(row=3, column=1)
    asientos.grid(row=4, column=1)
    metodoPago.grid(row=5, column=1)
    telefono.grid(row=6, column=1)
    email.grid(row=7, column=1)
    domicilio.grid(row=8, column=1)

    def register():
        with open("clientes.json") as file:
            clientes = json.load(file)
            clientes.append({
                "nombre": nombre.get(),
                "apellidos": apellidos.get(),
                "tituloEvento": tituloEvento.get(),
                "fechaEvento": fechaEvento.get(),
                "asientos": asientos.get(),
                "metodoPago": metodoPago.get(),
                "telefono": telefono.get(),
                "email": email.get(),
                "domicilio": domicilio.get()
            })
            with open('clientes.json', 'w') as outfile:
                json.dump(clientes, outfile)
        tk.messagebox.showinfo("Cliente registrado",
                               "Datos del cliente registrados existosamente!")

    regresar = tk.Button(app, text="Regresar a inicio", command=retorno)
    regresar.grid(row=9, column=1)
    actualiza = tk.Button(app, text="Registrar cliente", command=register)
    actualiza.grid(row=9, column=2)


# Borrar registro
def borrarCliente():
    retorno()
    frame = tk.Frame(app)
    frame.pack(side=tk.TOP)
    framebtns = tk.Frame(app)
    framebtns.pack(side=tk.TOP)

    tk.Label(
        frame, text="Ingresa el nombre del cliente que quieres borrar. ").pack()
    tk.Label(frame, text="Nombre:").pack(side=tk.LEFT)

    name = tk.Entry(frame)
    name.pack(side=tk.RIGHT)

    def delete():
        if name.get() != "":
            with open("clientes.json") as file:
                clientes = json.load(file)

                for index in range(len(clientes)):
                    if clientes[index]["nombre"] == name.get():
                        clientes.pop(index)
                        with open('clientes.json', 'w') as outfile:
                            json.dump(clientes, outfile)
                        tk.messagebox.showinfo(
                            "Registro borrado", "Registro de cliente eliminado existosamente!")
                        break

    regresar = tk.Button(framebtns, text="Regresar", command=retorno)
    regresar.pack(side=tk.LEFT)
    borra = tk.Button(framebtns, text="Borrar", command=delete)
    borra.pack(side=tk.RIGHT)


# Actualizar registro
def actualizarCliente():
    retorno()
    fields = ["Nombre:", "Apellidos:", "Titulo Evento:", "Fecha Programada:", "Asientos Reservados:", "Metodo de pago:",
              "Telefono:", "Email:", "Domicilio:"]

    def detalleCliente(frame, cliente, index):

        crow = 0
        ccol = 0
        for f in fields:
            tk.Label(frame, text=f).grid(row=crow, column=ccol)
            crow = crow+1

        def actualizar(index):
            with open("clientes.json") as file:
                clientes = json.load(file)
            clientes.pop(index)
            clientes.append({
                "nombre": nombre.get(),
                "apellidos": apellidos.get(),
                "tituloEvento": tituloEvento.get(),
                "fechaEvento": fechaEvento.get(),
                "asientos": asientos.get(),
                "metodoPago": metodoPago.get(),
                "telefono": telefono.get(),
                "email": email.get(),
                "domicilio": domicilio.get(),

            })
            with open('clientes.json', 'w') as outfile:
                json.dump(clientes, outfile)
            tk.messagebox.showinfo("Información actualizada",
                                   "Los datos del registro se guardaron correctamente")

        nombre = tk.Entry(frame)
        apellidos = tk.Entry(frame)
        tituloEvento = tk.Entry(frame)
        fechaEvento = tk.Entry(frame)
        asientos = tk.Entry(frame)
        metodoPago = tk.Entry(frame)
        telefono = tk.Entry(frame)
        email = tk.Entry(frame)
        domicilio = tk.Entry(frame)

        nombre.grid(row=0, column=1)
        apellidos.grid(row=1, column=1)
        tituloEvento.grid(row=2, column=1)
        fechaEvento.grid(row=3, column=1)
        asientos.grid(row=4, column=1)
        metodoPago.grid(row=5, column=1)
        telefono.grid(row=6, column=1)
        email.grid(row=7, column=1)
        domicilio.grid(row=8, column=1)

        nombre.insert(0, cliente["nombre"])
        apellidos.insert(0, cliente["apellidos"])
        tituloEvento.insert(0, cliente["tituloEvento"])
        fechaEvento.insert(0, cliente["fechaEvento"])
        asientos.insert(0, cliente["asientos"])
        metodoPago.insert(0, cliente["metodoPago"])
        telefono.insert(0, cliente["telefono"])
        email.insert(0, cliente["email"])
        domicilio.insert(0, cliente["domicilio"])

        regresar = tk.Button(frame, text="Regresar", command=retorno)
        regresar.grid(row=9, column=1)
        actualiza = tk.Button(frame, text="Registrar",
                              command=lambda: actualizar(index))
        actualiza.grid(row=9, column=2)

    def buscarCliente():
        nombre = namesearch.get()
        frameinfo = tk.Frame(app)
        frameinfo.pack()
        with open("clientes.json") as file:
            clientes = json.load(file)
            nobody = True
            for index in range(len(clientes)):
                if clientes[index]["nombre"] == nombre:
                    nobody = False
                    busca["state"] = tk.DISABLED
                    detalleCliente(frameinfo, clientes[index], index)

            if(nobody):
                tk.messagebox.showinfo(
                    "Registro no encontrado", "No se encontro registro para el cliente '"+nombre+"'")

    framesearch = tk.Frame(app)
    framesearch.pack()
    tk.Label(framesearch, text="Ingresa el nombre del cliente:").pack(
        side=tk.LEFT)
    busca = tk.Button(framesearch, text="Buscar", command=buscarCliente)
    busca.pack(side=tk.RIGHT)
    namesearch = tk.Entry(framesearch)
    namesearch.pack(side=tk.RIGHT)


# Exportar Avisos
def exportarAvisos():
    retorno()
    frameprint = tk.Frame(app)
    frameprint.pack()

    def exportarAviso(clientes):
        if not os.path.exists('Reembolsos'):
            os.mkdir('Reembolsos')
            os.mkdir('Reembolsos/Word')
            os.mkdir('Reembolsos/PDF')
        locale.setlocale(locale.LC_TIME, 'es_ES.utf8')
        fechaActual = datetime.now().strftime('%A %d de %B de %Y')
        for select in seleccionados:
            if select["value"].get() == 1:
                clientes[select["index"]]['fecha'] = fechaActual
                clientes[select["index"]]['codigoEvento'] = ''.join(random.choices(
                    string.ascii_letters + string.digits, k=12))
                clientes[select["index"]]['folio'] = random.randint(
                    10000, 99999)
                with open('Reembolsos/Aviso reembolso ' + clientes[select["index"]]['email'] + '.txt', 'w') as aviso:
                    aviso.write(avisoReembolso.format(
                        **clientes[select["index"]]))
                with open('Reembolsos/Aviso reembolso ' + clientes[select["index"]]['email'] + '.txt', 'r') as export:
                    word = docx.Document()
                    word.add_paragraph(export.read())
                    word.save('Reembolsos/Word/Aviso reembolso ' +
                              clientes[select["index"]]['email'] + ".docx")
                avisoTexto = open('Reembolsos/Aviso reembolso ' +
                                  clientes[select["index"]]['email'] + '.txt', "r")
                pdf = FPDF()
                pdf.add_page()
                pdf.set_font("Arial", size=14)
                for x in avisoTexto:
                    pdf.write(6, txt=x)
                pdf.output('Reembolsos/PDF/Aviso reembolso ' +
                           clientes[select["index"]]['email'] + ".pdf")

    with open("clientes.json") as file:
        clientes = json.load(file)

    with open('Aviso_Reembolso_Inminente_plantilla.txt', 'r') as template:
        avisoReembolso = template.read()

    seleccionados = []
    k = 0
    while k < len(clientes):
        var = {"value": tk.IntVar(),
               "name": clientes[k]["nombre"],
               "index": k}
        value = tk.Checkbutton(frameprint, text=clientes[k]["nombre"], variable=var["value"],
                               onvalue=1, offvalue=0)
        value.pack()

        seleccionados.append(var)
        k += 1

    backbtn = tk.Button(frameprint, text="Regresar", command=retorno)
    backbtn.pack(side=tk.LEFT)
    registerbtn = tk.Button(frameprint, text="Imprimir",
                            command=lambda: exportarAviso(clientes))
    registerbtn.pack(side=tk.RIGHT)


# Constructor GUI
app = tk.Tk()
app.geometry("520x720")
app.title("Sistema reembolsos ticketmaster Dummy - 16310048")

# Create menu
menubar = tk.Menu(app)
filemenu = tk.Menu(menubar, tearoff=0)
filemenu.add_command(label="Añadir", command=registrarCliente)
filemenu.add_command(label="Eliminar", command=borrarCliente)
filemenu.add_command(label="Actualizar", command=actualizarCliente)
menubar.add_cascade(label="Clientes", menu=filemenu)
printmenu = tk.Menu(menubar, tearoff=0)
printmenu.add_command(label="Reembolsos", command=exportarAvisos)
menubar.add_cascade(label="Exportar Avisos", menu=printmenu)

app.config(menu=menubar)
app.mainloop()

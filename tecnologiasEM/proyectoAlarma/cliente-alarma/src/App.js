import React, { useEffect, useState } from "react";
import axios from "axios";
import "./App.css";

const App = () => {
  useEffect(() => {
    // Update the document title using the browser API
    iniciaReloj();
  });

  const [hora, setHora] = useState("");
  const [mins, setMins] = useState("");
  const [etiqueta, setEtiqueta] = useState("Ninguna alarma establecida...");

  const horaCambiada = (e) => {
    setHora(e.target.value);
  };

  const minutosCambiados = (e) => {
    setMins(e.target.value);
  };
  const iniciaReloj = () => {
    let today = new Date();
    let h = today.getHours();
    let m = today.getMinutes();
    let s = today.getSeconds();
    m = ajustaDigitos(m);
    s = ajustaDigitos(s);
    document.getElementById("reloj").innerHTML = h + ":" + m + ":" + s;
    setTimeout(iniciaReloj, 500);
  };
  const ajustaDigitos = (i) => {
    if (i < 10) {
      i = "0" + i;
    } // add zero in front of numbers < 10
    return i;
  };

  const programarAlarma = (event) => {
    event.preventDefault();
    console.log("hello there!");
    const data = {
      hora: hora,
      minutos: mins,
    };
    let programada = "";
    axios
      .post("http://192.168.100.32:8080/alarma/setear", data)
      .then((res) => {
        setEtiqueta(res.data.mensaje);
        programada = res.data.programada;
      })
      .catch((err) => console.log(err));
    setTimeout(() => setEtiqueta(programada), 6000);
  };
  const cancelarAlarma = (event) => {
    event.preventDefault();
    axios
      .get("http://192.168.100.32:8080/alarma/cancelar")
      .then((res) => setEtiqueta(res.data.mensaje))
      .catch((err) => console.log(err));
    setTimeout(() => setEtiqueta("Ninguna alarma establecida..."), 6000);
  };
  return (
    <div className="App">
      <h1>Sistema de alarma con Arduino</h1>
      <div id="reloj" className="Reloj" />
      <form className="flex" onSubmit={programarAlarma}>
        Ingresa una hora:
        <section>
          <input
            type="number"
            min="0"
            max="23"
            placeholder="hora"
            value={hora}
            onChange={horaCambiada}
            required
          />
          :
          <input
            type="number"
            min="0"
            max="59"
            placeholder="min"
            value={mins}
            onChange={minutosCambiados}
            required
          />
        </section>
        <button className="boton" id="set" type="submit">
          Setear alarma
        </button>
        <label>{etiqueta}</label>
        <button className="boton" id="cancel" onClick={cancelarAlarma}>
          Apagado remoto
        </button>
      </form>
    </div>
  );
};

export default App;

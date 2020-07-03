import React, { useEffect } from "react";
import "./App.css";

const App = () => {
  useEffect(() => {
    // Update the document title using the browser API
    iniciaReloj();
  });

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
  return (
    <div className="App">
      <h1>Sistema de alarma con Arduino</h1>
      <div id="reloj" className="Reloj"></div>
      <div className="flex">
        Ingresa una hora:
        <section>
          <input type="number" min="0" max="23"></input>:
          <input type="number" min="0" max="59"></input>
        </section>
        <button className="boton" id="set">
          Setear alarma
        </button>
        <label>Ninguna Alarma establecida...</label>
        <button className="boton" id="cancel">
          Apagado remoto
        </button>
      </div>
    </div>
  );
};

export default App;

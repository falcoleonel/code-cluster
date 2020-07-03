const fs = require("fs");
const path = require("path");
const express = require("express");

const router = express.Router();

const ajustaDigitos = (i) => {
  if (i < 10) {
    i = "0" + i;
  }
  return i;
};

// POST /alarma/setear
router.post("/setear", (req, res, next) => {
  const hora = req.body.hora;
  const minutos = req.body.minutos;
  fs.writeFileSync("horaProg.txt", `${hora}`);
  fs.writeFileSync("minProg.txt", `${minutos}`);
  res.status(201).json({
    mensaje: "Alarma programada con éxito!",
    detalle: { hora: hora, minutos: minutos },
  });
});

// GET /alarma/estado
router.get("/estado", (req, res, next) => {
  let today = new Date();
  let h = today.getHours();
  let m = today.getMinutes();
  m = ajustaDigitos(m);
  const horaProg = fs
    .readFileSync(path.resolve(__dirname, "horaProg.txt"))
    .toString();
  const minProg = fs
    .readFileSync(path.resolve(__dirname, "minProg.txt"))
    .toString();
  console.log(horaProg + ":" + minProg);
  console.log(h + ":" + m);

  //si la la hora actual y programada coinciden actualiza el estado
  if (h == horaProg) {
    if (m == minProg) {
      console.log("entre aqui!");

      fs.writeFileSync(path.resolve(__dirname, "estado.txt"), "Activada");
    }
  }
  const estadoActual = fs
    .readFileSync(path.resolve(__dirname, "estado.txt"))
    .toString();
  res.set("Content-Type", "text/plain");
  res.status(201).send(estadoActual);
});
// // GET /alarma/cancelar
// router.get("/cancelar", alarmController.cancelar);

// // GET /alarma/estado
// router.get("/estado", alarmController.consultarEstado);

// // POST /alarma/apagar
// router.post("/apagar", alarmController.apagar);

module.exports = router;

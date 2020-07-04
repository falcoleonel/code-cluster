const express = require("express");
const bodyParser = require("body-parser");
const fs = require("fs");
const path = require("path");

const ajustaDigitos = (i) => {
  if (i < 10) {
    i = "0" + i;
  }
  return i;
};
const server = express();
server.use(bodyParser.json());

server.use((req, res, next) => {
  res.setHeader("Access-Control-Allow-Origin", "*");
  res.setHeader(
    "Access-Control-Allow-Methods",
    "OPTIONS, GET, POST, PUT, PATCH, DELETE"
  );
  res.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
  next();
});

// POST /alarma/setear
server.post("/alarma/setear", (req, res, next) => {
  const hora = req.body.hora;
  const minutos = req.body.minutos;
  fs.writeFileSync("horaProg.txt", `${hora}`);
  fs.writeFileSync("minProg.txt", `${minutos}`);
  console.log("alarma programada desde cliente");

  res.status(201).json({
    mensaje: "Alarma programada exitosamente!",
    programada: `Alarma programada para ${hora}:${ajustaDigitos(minutos)}`,
  });
});

// GET /alarma/estado
server.get("/alarma/estado", (req, res, next) => {
  const today = new Date();
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
  if (h > horaProg) {
    console.log("Encendiendo alarma...");
    fs.writeFileSync(path.resolve(__dirname, "estado.txt"), "Activada");
  }
  if (h == horaProg) {
    if (m >= minProg) {
      console.log("Encendiendo alarma...");
      fs.writeFileSync(path.resolve(__dirname, "estado.txt"), "Activada");
    }
  }
  const estadoActual = fs
    .readFileSync(path.resolve(__dirname, "estado.txt"))
    .toString();
  res.set("Content-Type", "text/plain");
  res.status(201).send(estadoActual);
});

// GET /alarma/apagar
server.get("/alarma/apagar", (req, res, next) => {
  const today = new Date();
  fs.writeFileSync("horaProg.txt", "24");
  fs.writeFileSync("minProg.txt", "60");
  fs.writeFileSync("estado.txt", "Esperando");
  //Si se implemente WebSockets mandar a la app cliente
  console.log(`Alarm turned off at: ${today.toLocaleTimeString()}`);
  res.set("Content-Type", "text/plain");
  res.status(201).send("Alarma apagada exitosamente");
});

// GET /alarma/cancelar
server.get("/alarma/cancelar", (req, res, next) => {
  fs.writeFileSync("horaProg.txt", "24");
  fs.writeFileSync("minProg.txt", "60");
  fs.writeFileSync("estado.txt", "Esperando");
  console.log("alarma cancelada desde aplicacion");
  res.status(201).json({
    mensaje: "Alarma cancelada!",
  });
});

server.listen(8080);

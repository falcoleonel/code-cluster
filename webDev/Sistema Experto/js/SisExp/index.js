 var reglas = data;
 console.log(data);

//inicializacion de variables
var atribute = [];
var pos = 0;
for (var key in reglas) atribute.push(key);
var key = atribute[pos];
var prevkey = atribute[pos];
var quest = 0;
var sig = 100;
var finishProgram = false;
var validaFinal = false;
var validaInicial = false;
var whys = [];
var answers = [];

//funcion que construye las preguntas
function getKeyValue() {
    var pregunta = reglas[key][quest];
    document.getElementById("textQuestion").innerHTML =
    "Su figura tiene " + pregunta;
    whys.push(pregunta);
}

//funcion que valida respuesta
function getAnswer() {
    var value = document.getElementById("question");
    answers.push(value.checked ? "SI" : "NO");
    validRules(value.checked);
}

//funcion principal
function validRules(answer) {
    //antecedentes faltantes y actual
    faltantes = reglas[key].length;
    var actual = reglas[key].indexOf(reglas[key][quest]);

    if (answer) {
    //caso un solo antecedente
    if ((faltantes == 1) & (actual == 0)) {
        validaFinal = true;
        validaInicial = true;
    }
    //caso ultimo antecedente
    if (faltantes - actual == 1) {
        validaFinal = true;
    }
    //caso antecedente inicial o intermedio
    else {
        validaInicial = true;
        quest++;
    }
    }
    // respuesta negativa
    else {
    if (quest != 0) {
        prevkey = key;
        // finishProgram = true; // activar esto hace que el motor se detenga cuando se meta a un arreglo de antecedentes, desactivarlo hace que se pase a la siguiente regla
        var newKey = newValidRules(reglas[key][quest]);
        key = newKey;
        quest = 0;
    } else {
        prevkey = key;
        var newKey = newValidRules(reglas[key][quest]);
        key = newKey;
        quest = 0;
    }
    }

    //resetea pregunta y checkbox
    var value = document.getElementById("question");
    value.checked = false;

    // termino de evaluar positivamente
    if (validaFinal && validaInicial) {
    figuraEncontrada(key);
    write_porques(porque("Si", key));
    }
    // termino de evaluar negativamente
    else if ((finishProgram && validaInicial) || reglas[key] == undefined) {
    noEncontrada(prevkey);
    write_porques(porque("No", prevkey));
    }
    //pasa a la siguiente pregunta
    else {
    getKeyValue();
    }
}
//redefine las reglas
function newValidRules(statement) {
    for (var key in reglas) {
    for (var item in reglas[key][quest]) {
        if (reglas[key][quest] == statement) {
        var currentType = key;
        delete reglas[currentType];
        break;
        }
    }
    }
    atribute = [];
    for (var key in reglas) atribute.push(key);
    key = atribute[pos];
    return key;
}

function porque(Si_No, nomFig) {
    var mensajes_porque = [];
    for (let w = 0; w < whys.length; w++) {
    mensajes_porque.push(
        "<br>" +
        Si_No +
        " es " +
        nomFig +
        ", debido a que " +
        answers[w] +
        " tiene " +
        whys[w]
    );
    }
    return mensajes_porque;
}

function figuraEncontrada(key) {
    document.getElementById("textQuestion").innerHTML =
    "Su figura es un  " + key;
    document.getElementById("question").style.display = "none";
    document.getElementById("submit").style.display = "none";
}
function noEncontrada(key) {
    document.getElementById("textQuestion").innerHTML =
    "Su figura no fue encontrada";
    document.getElementById("question").style.display = "none";
    document.getElementById("submit").style.display = "none";
}
function write_porques(porque) {
    document.getElementById("whys").innerHTML = porque;
    document.getElementById("reload").innerHTML =
    "Por favor recargue la pagina para continuar";
}

reglas["figura1"]=["Regla1", "regla2", "regla3"];
console.log(reglas);
obj=reglas;

function saveObj(obj) {
    var content = "var data = " + JSON.stringify(obj) +"; ";
    var filename = "reglas.json";
    var blob = new Blob([content], {
     type: "text/plain;charset=utf-8"
    });
    
    saveAs(blob, filename);
}


/***append valores
 */ 
function add(reglas) {
    var newRules = creacionReglas(reglas);
    saveObj(newRules);
}

function creacionReglas(obj) {
    var antecedentes = [];
    var ante = '';
    var figures_ = prompt("¿Que figures_ quieres ingresar?").toString();
    if (figures_ != null) {
        ante = prompt("Ingresa tu regla:");
        antecedentes.push(ante);
        while(ante != null) {
            ante = prompt("Ingresa otra regla o cancelar para salir:");
            antecedentes.push(ante);
        }
    }
    antecedentes= antecedentes.filter(Boolean);
    console.log(figures_ + " " + antecedentes);
    obj[figures_] = antecedentes;
    var salida = obj;
    return salida;
}

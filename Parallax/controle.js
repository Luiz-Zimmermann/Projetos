
var vetorUser = [];
var vetorOcor = [];

var User = {
    nome: "",
    cpf: "",
    telefone: "",
    email: "",
    nome_user: "",
    senha: "",
    type: ""
}


var ocorrencia = {
    titulo: "",
    data: "",
    tipo: "",
    cidade: "",
    descricao: ""
}


function registro() {

    User.nome = document.getElementById("nomec").value;
    User.senha = document.getElementById("senhaa").value;
    User.cpf = document.getElementById("cpf").value;
    User.telefone = document.getElementById("tele").value;
    User.email = document.getElementById("email").value;
    User.nome_user = document.getElementById("nomee").value;
    User.type = document.getElementById("tipyuser").value;

    if (User.type == "adm") {
        document.getElementById("cadoco").style.display = "block";
        document.getElementById("veroco").style.display = "block";
    } else {
        document.getElementById("cadoco").style.display = "none";
        document.getElementById("veroco").style.display = "block";
    }

    vetorUser.push(User);
    aux();
    home();
}

function regocorrencia() {

    ocorrencia.titulo = document.getElementById("titulooco").value;
    ocorrencia.data = document.getElementById("data").value;
    ocorrencia.tipo = document.getElementById("desastre").value;
    ocorrencia.cidade = document.getElementById("cidad").value;
    ocorrencia.descricao = document.getElementById("descricaooco").value;

    vetorOcor.push(ocorrencia);
    coloca_ocor();

    window.alert("Ocorrencia Cadastrada");

    document.getElementById("titulooco").innerHTML = "";
    document.getElementById("data").innerHTML = "";
    document.getElementById("cidad").innerHTML = "";
    document.getElementById("descricaooco").innerHTML = "";

}

function coloca_ocor() {
    var table = document.getElementById("myTable");
    var row = table.insertRow(0);
    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);
    var cell4 = row.insertCell(3);
    cell1.innerHTML = vetorOcor[0].titulo;
    cell2.innerHTML = vetorOcor[0].cidade;
    cell3.innerHTML = vetorOcor[0].data;
    cell4.innerHTML = vetorOcor[0].tipo;
}


function home() {
    document.getElementById("noticias").style.display = "block";
    document.getElementById("sobre").style.display = "none";
    document.getElementById("info").style.display = "block";
    document.getElementById("cadastro").style.display = "none";
    document.getElementById("ocorrencia").style.display = "none";
    document.getElementById("ver").style.display = "none";
    document.getElementById("titulotable").style.display = "none";
    document.getElementById("telameio").style.height = "900px";
    document.getElementById("main").style.background = "url('balne.jpg')";
    document.getElementById("main").style.backgroundAttachment = "fixed";
    document.getElementById("main").style.backgroundRepeat = "no-repeat";
    document.getElementById("main").style.backgroundSize = "cover";
}

function cadastrar_ocor() {
    document.getElementById("ocorrencia").style.display = "block";
    document.getElementById("cadastro").style.display = "none";
    document.getElementById("noticias").style.display = "none";
    document.getElementById("ver").style.display = "none";
    document.getElementById("info").style.display = "none";
    document.getElementById("titulotable").style.display = "none";
    document.getElementById("telameio").style.height = "500px"
    document.getElementById("main").style.background = "url('cristo.jpg')";
    document.getElementById("main").style.backgroundAttachment = "fixed";
    document.getElementById("main").style.backgroundRepeat = "no-repeat";
    document.getElementById("main").style.backgroundSize = "cover";
    document.getElementById("main").style.backgroundColor = "rgb(243, 239, 239)"
}


function registrar() {
    document.getElementById("noticias").style.display = "none";
    document.getElementById("info").style.display = "block";
    document.getElementById("sobre").style.display = "none";
    document.getElementById("ocorrencia").style.display = "none";
    document.getElementById("ver").style.display = "none";
    document.getElementById("titulotable").style.display = "none";
    document.getElementById("telameio").style.height = "800px"
    document.getElementById("cadastro").style.display = "block";
    document.getElementById("main").style.background = "url('bal.jpg')";
    document.getElementById("main").style.backgroundAttachment = "fixed";
    document.getElementById("main").style.backgroundRepeat = "no-repeat";
    document.getElementById("main").style.backgroundSize = "cover";
}

function login() {
    if (document.getElementById("username").value == vetorUser[0].nome && document.getElementById("psw").value == vetorUser[0].senha) {
        aux();
        home();
    }
}

function login2() {
    var bol = 0;
    for (var i = 0; i < vetorUser.length; i++) {
        if (document.getElementById("username").value == vetorUser[i].nome_user && document.getElementById("psw").value == vetorUser[i].senha) {
            bol = 1;
            break;
        } else {
            bol = 0;
        }
    }
    if (bol == 1) {
        if (vetorUser[i].type == "adm") {
            document.getElementById("cadoco").style.display = "block";
            document.getElementById("veroco").style.display = "block";
        } else {
            document.getElementById("cadoco").style.display = "none";
            document.getElementById("veroco").style.display = "block";

        }
        aux();
        home();
        $('#myModal').modal('hide');
    } else {
        window.alert("Usuario não encontrado");
    }
}

function aux() {

    document.getElementById("canto").style.display = "none";
    document.getElementById("canto2").style.display = "none";
    document.getElementById("logout").style.display = "block";

}

function logout() {
    document.getElementById("cadoco").style.display = "none";
    document.getElementById("veroco").style.display = "none";
    document.getElementById("canto").style.display = "block";
    document.getElementById("canto2").style.display = "block";
    document.getElementById("logout").style.display = "none";
}

function sobre() {
    document.getElementById("noticias").style.display = "none";
    document.getElementById("cadastro").style.display = "none";
    document.getElementById("cadastro").style.display = "none"
    document.getElementById("info").style.display = "block";
    document.getElementById("pos").style.display = "none";
    document.getElementById("posbut").style.display = "none";
    document.getElementById("ocorrencia").style.display = "none";
    document.getElementById("sobre").style.display = "block";
    document.getElementById("titulotable").style.display = "none";
    document.getElementById("main").style.background = "url('cristo.jpg')";
    document.getElementById("telameio").style.height = "500px";
    document.getElementById("main").style.backgroundAttachment = "fixed";
    document.getElementById("main").style.backgroundRepeat = "no-repeat";
    document.getElementById("main").style.backgroundSize = "cover";
}

function ver() {
    document.getElementById("ver").style.display = "block";
    document.getElementById("info").style.display = "block";
    document.getElementById("pos").style.display = "block";
    document.getElementById("posbut").style.display = "block";
    document.getElementById("titulotable").style.display = "inline-table";
    document.getElementById("noticias").style.display = "none";
    document.getElementById("cadastro").style.display = "none";
    document.getElementById("sobre").style.display = "none";
    document.getElementById("ocorrencia").style.display = "none";
    document.getElementById("telameio").style.height = "900px";
}

function a() {
    
    var table = document.getElementById("tabela");
    var row = table.insertRow(0);
    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell1 = row.insertCell(2);

    cell1.innerHTML = "tit";
    cell2.innerHTML = "dat";
    cell3.innerHTML = "tip";
}

function myDeleteFunction() {
    var x = document.getElementById("pos").value;
    document.getElementById("myTable").deleteRow(x - 1);
}


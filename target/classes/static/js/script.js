function getURLPadrao() {
    return "http://localhost:8080/detalhesevento";
}

async function salvarEvento() {
    nomeEvento = document.getElementById("nomeEvento").value;
    diaSemana = document.getElementById("diaSemana").value;
    qtdPessoas = document.getElementById("qtdPessoas").value;
    descPromocional = document.getElementById("descPromocional").value;

    let url = getURLPadrao();
    url += "/persisteevento";
    url += "?nomeEvento=" + nomeEvento;
    url += "&qtdPessoas=" + qtdPessoas;
    url += "&diaSemana=" + diaSemana;
    url += "&descPromocional=" + descPromocional;

    console.log(url);

    try {
        var xhttp = new XMLHttpRequest();
        xhttp.open("GET", url, false);
        xhttp.send();//A execução do script para aqui até a requisição retornar do servidor

        const json = xhttp.responseText;
        const obj = JSON.parse(json);

        if (parseInt(obj.codigo) > 0) {
            alert("Evento salvo com sucesso :D Codigo = " + obj.codigo);
        }
        else {
            alert("Aconteceu algum erro ao salvar o Evento, por favor, tente novamente");
        }
    } catch (erro) {
        console.log(erro);
        alert("Aconteceu algum erro ao salvar o Evento, por favor, tente novamente");
    }
}

async function PesquisarEvento() {
    idEvento = document.getElementById("idEvento").value;

    console.log(idEvento);

    let url = getURLPadrao();
    url += "/dadosevento";
    url += "?codigo=" + idEvento;

    try {
        var xhttp = new XMLHttpRequest();
        xhttp.open("GET", url, false);
        xhttp.send();

        const json = xhttp.responseText;
        const obj = JSON.parse(json);
        console.log(obj.codigo);
        console.log(parseInt(obj.codigo));

        if (parseInt(obj.codigo) > 0) {
            codigo.textContent = obj.codigo
            nome.textContent = obj.nomeEvento
            qtdpessoas.textContent = obj.qtdPessoas
            diasemana.textContent = obj.diaDaSemana
            descontopromocional.textContent = obj.descontoPromocional
            desconto.textContent = obj.desconto
            valorpelodiasemana.textContent = obj.valoresPorDiaDaSemana
            valorporqtdpessoas.textContent = obj.valoresPorQtdDePessoas
            custototalevento.textContent = obj.custoDoEvento
        }
        else {
            alert("Código do evento inválido :C");
        }
    } catch (erro) {
        console.log(erro);
    }
}

async function salvarOrgBeneficente() {
    nomeOrgBeneficente = document.getElementById("nomeOrgBeneficente").value;
            
    let url = getURLPadrao();
    url += "/persisteorgbeneficente";
    url += "?nomeOrgBeneficente=" + nomeOrgBeneficente;
    
    console.log(url);
    
        try {
            var xhttp = new XMLHttpRequest();
            xhttp.open("GET", url, false);
            xhttp.send();//A execução do script para aqui até a requisição retornar do servidor
    
            const json = xhttp.responseText;
            const obj = JSON.parse(json);
    
            if (parseInt(obj.codigo) > 0) {
                alert("Organização Beneficente salva com sucesso :D Codigo = " + obj.codigo);
            }
            else {
                alert("Aconteceu algum erro ao salvar a organizacao beneficente, por favor, tente novamente");
            }
        } catch (erro) {
            console.log(erro);
            alert("Aconteceu algum erro ao salvar a organizacao beneficente, por favor, tente novamente");
        }
}

async function PesquisarOrgBeneficente() {
    idOrgBeneficente = document.getElementById("idOrgBeneficente").value;

    console.log(idOrgBeneficente);

    let url = getURLPadrao();
    url += "/dadosorgbeneficente";
    url += "?codigo=" + idOrgBeneficente;

    try {
        var xhttp = new XMLHttpRequest();
        xhttp.open("GET", url, false);
        xhttp.send();

        const json = xhttp.responseText;
        const obj = JSON.parse(json);
        console.log(obj.codigo);
        console.log(parseInt(obj.codigo));

        if (parseInt(obj.codigo) > 0) {
            codigo.textContent = obj.codigo
            nome.textContent = obj.nomeOrgBeneficente
        }
        else {
            alert("Código da organização beneficente inválido :C");
        }
    } catch (erro) {
        console.log(erro);
    }
}


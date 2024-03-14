let usuario = sessionStorage.getItem('usuarioVO');
usuario = JSON.parse(usuario);

let despesa = {};

async function cadastrarDespesa() {
    let options = {
        method: "POST",
        headers: { "Content-type": "application/json" },
        body: JSON.stringify({
            idUsuario: usuario.idUsuario,
            descricao: document.querySelector('#descricao').value,
            dataPagamento: document.querySelector('#data-pagamento').value,
            dataVencimento: document.querySelector('#data-vencimento').value,
            valor: document.querySelector('#valor').value
        })
    };
    const resposta = await fetch('http://localhost:8080/senhor-financas/rest/despesa/cadastrar', options);
    despesa = await resposta.json();
    if (despesa.iddespesa != 0) {
        alert("Cadastro realizado com sucesso.");
        despesa = {};
        listarDespesas();
        window.location.href = "./despesas.html";
    } else {
        alert("Houve um problema no cadastro da despesa.");
    }
    form.reset();
}

async function listarDespesas() {
    let options = {
        method: "GET",
        headers: { "Content-type": "application/json" }
    };
    const listaDespesas = await fetch('http://localhost:8080/senhor-financas/rest/despesa/listar/' + usuario.idUsuario, options);
    const listaDespesasJson = await listaDespesas.json();
    if (listaDespesasJson.length != 0) {
        preencherTabelaDespesas(listaDespesasJson);
    } else {
        alert("Houve um problema na busca das despesas.");
    }
}

function preencherTabelaDespesas(dados) {
    let tbody = document.getElementById('meio-tabela');
    let acumulador = 0;

    tbody.innerText = '';
    for (let i = 0; i < dados.length; i++) {
        let tr = tbody.insertRow();
        let td_id = tr.insertCell();
        let td_descricao = tr.insertCell();
        let td_dataVencimento = tr.insertCell();
        let td_dataPagamento = tr.insertCell();
        let td_valor = tr.insertCell();
        let td_acoes = tr.insertCell();

        acumulador += dados[i].valor;

        td_id.innerText = dados[i].idDespesa;
        td_descricao.innerText = dados[i].descricao;
        td_dataVencimento.innerText = formatarData(dados[i].dataVencimento);
        td_dataPagamento.innerText = formatarData(dados[i].dataPagamento);
        td_valor.innerText = dados[i].valor.toLocaleString('pt-br', {style: 'currency', currency: 'BRL'});

        let editar = document.createElement('button');
        editar.textContent = 'Editar';
        editar.setAttribute('onclick', 'salvarInformacoesDespesa(' + JSON.stringify(dados[i]) + ')');
        td_acoes.appendChild(editar);

        let excluir = document.createElement('button');
        excluir.textContent = 'Excluir';
        excluir.setAttribute('onclick', 'excluirDespesa(' + JSON.stringify(dados[i]) + ')');
        td_acoes.appendChild(excluir);

    }
    total.innerText = 'Total - ' + acumulador.toLocaleString('pt-br', {style: 'currency', currency: 'BRL'});
}

function formatarData(data){
    let dataFormatada = new Date(data);
    dia = dataFormatada.getDate().toString().padStart(2,'0');
    mes = (dataFormatada.getMonth() + 1).toString().padStart(2,'0');
    ano = dataFormatada.getFullYear();
    if(data != undefined){
      return dia + "/" + mes + "/" + ano;
    } else {
      return "";
    }
  }

function limpar() {
    let tbody = document.getElementById('meio-tabela');
    tbody.innerHTML = '';
}

async function salvarInformacoesDespesa(despesa) {
    sessionStorage.setItem('despesa', JSON.stringify(despesa));
    window.location.href = "./atualizarDespesa.html";
}

async function excluirDespesa(dados) {
    let options = {
        method: "DELETE",
        headers: { "Content-type": "application/json" },
        body: JSON.stringify({
            idDespesa: dados.idDespesa,
        })
    };
    const resultado = await fetch('http://localhost:8080/senhor-financas/rest/despesa/excluir', options);
    if (resultado.ok == true) {
        alert("Exclusão realizada com sucesso.");
        listarDespesas();
    } else {
        alert("Houve um problema na exclusão da despesa.");
    }
}
let usuario = sessionStorage.getItem('usuarioVO');
usuario = JSON.parse(usuario);

let receita = {};

async function cadastrarReceita() {
    let options = {
        method: "POST",
        headers: { "Content-type": "application/json" },
        body: JSON.stringify({
            idUsuario: usuario.idUsuario,
            descricao: document.querySelector('#descricao').value,
            dataReceita: document.querySelector('#data-receita').value,
            valor: document.querySelector('#valor').value
        })
    };
    const resposta = await fetch('http://localhost:8080/senhor-financas/rest/receita/cadastrar', options);
    receita = await resposta.json();
    if (receita.idreceita != 0) {
        alert("Cadastro realizado com sucesso.");
        receita = {};
        window.location.href = "./telaReceitas.html";
    } else {
        alert("Houve um problema no cadastro da receita.");
    }
}

async function listarReceitas() {
    let options = {
        method: "GET",
        headers: { "Content-type": "application/json" }
    };
    const listaReceitas = await fetch('http://localhost:8080/senhor-financas/rest/receita/listar/' + usuario.idUsuario, options);
    const listaReceitasJson = await listaReceitas.json();
    if (listaReceitasJson.length != 0) {
        preencherTabelaReceitas(listaReceitasJson);
    } else {
        alert("Houve um problema na busca das receitas.");
    }
}

function preencherTabelaReceitas(dados) {
    let tbody = document.getElementById('meio-tabela');
    let acumulador = 0;

    tbody.innerText = '';
    for (let i = 0; i < dados.length; i++) {
        let tr = tbody.insertRow();
        let td_id = tr.insertCell();
        let td_descricao = tr.insertCell();
        let td_data = tr.insertCell();
        let td_valor = tr.insertCell();
        let td_acoes = tr.insertCell();

        acumulador += dados[i].valor;

        td_id.innerText = dados[i].idReceita;
        td_descricao.innerText = dados[i].descricao;
        td_data.innerText = formatarData(dados[i].dataReceita);
        td_valor.innerText = dados[i].valor.toLocaleString('pt-br', {style: 'currency', currency: 'BRL'})

        let editar = document.createElement('button');
        editar.textContent = 'Editar';
        editar.setAttribute('onclick', 'salvarInformacoesReceita(' + JSON.stringify(dados[i]) + ')');
        td_acoes.appendChild(editar);

        let excluir = document.createElement('button');
        excluir.textContent = 'Excluir';
        excluir.setAttribute('onclick', 'excluirReceita(' + JSON.stringify(dados[i]) + ')');
        td_acoes.appendChild(excluir);

    }
    total.innerText = 'Total - ' + acumulador.toLocaleString('pt-br', {style: 'currency', currency: 'BRL'});
}

function formatarData(data) {
    let dataFormatada = new Date(data),
        dia = dataFormatada.getDate().toString().padStart(2,'0'),
        mes = (dataFormatada.getMonth() + 1).toString().padStart(2,'0'),
        ano = dataFormatada.getFullYear();
    return dia + "/" + mes + "/" + ano;
}

function limpar() {
    let tbody = document.getElementById('meio-tabela');
    tbody.innerHTML = '';
}

async function salvarInformacoesReceita(receita) {
    sessionStorage.setItem('receita', JSON.stringify(receita));
    window.location.href = "./atualizarReceita.html";
}

async function excluirReceita(dados) {
    let options = {
        method: "DELETE",
        headers: { "Content-type": "application/json" },
        body: JSON.stringify({
            idReceita: dados.idReceita,
        })
    };
    const resultado = await fetch('http://localhost:8080/senhor-financas/rest/receita/excluir', options);
    if (resultado.ok == true) {
        alert("Exclusão realizada com sucesso.");
        listarReceitas();
    } else {
        alert("Houve um problema na exclusão da receita.");
    }
}
let receita = sessionStorage.getItem('receita');
receita = JSON.parse(receita);

function preencherTela(){
    document.querySelector("#descricao").value = receita.descricao;
    document.querySelector("#data-receita").value = receita.dataReceita;
    document.querySelector("#valor").value = receita.valor;
}

preencherTela();

async function atualizarReceita() {
    let options = {
        method: "PUT",
        headers: { "Content-type": "application/json" },
        body: JSON.stringify({
            idUsuario: receita.idUsuario,
            idReceita: receita.idReceita,
            descricao: document.querySelector('#descricao').value,
            dataReceita: document.querySelector('#data-receita').value,
            valor: document.querySelector('#valor').value
        })
    };
    const resultado = await fetch('http://localhost:8080/senhor-financas/rest/receita/atualizar', options);
    if (resultado.ok == true) {
        alert("Atualização realizada com sucesso.");
        window.location.href = "./telaReceitas.html";
    } else {
        alert("Houve um problema na atualização da receita.");
    }
}
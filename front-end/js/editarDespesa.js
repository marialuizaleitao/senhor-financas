let despesa = sessionStorage.getItem('despesa');
despesa = JSON.parse(despesa);

function preencherTela(){
    document.querySelector("#descricao").value = despesa.descricao;
    document.querySelector("#data-pagamento").value = despesa.dataPagamento;
    document.querySelector("#data-vencimento").value = despesa.dataVencimento;
    document.querySelector("#valor").value = despesa.valor;
}

preencherTela();

async function atualizarDespesa() {    
    let options = {
        method: "PUT",
        headers: { "Content-type": "application/json" },
        body: JSON.stringify({
            idUsuario: despesa.idUsuario,
            idDespesa: despesa.idDespesa,
            descricao: document.querySelector('#descricao').value,
            dataPagamento: document.querySelector('#data-pagamento').value,
            dataVencimento: document.querySelector('#data-vencimento').value,
            valor: document.querySelector('#valor').value
        })
    };
    const resultado = await fetch('http://localhost:8080/senhor-financas/rest/despesa/atualizar', options);
    if (resultado.ok == true) {
        alert("Atualização realizada com sucesso.");
        window.location.href = "./despesas.html";
    } else {
        alert("Houve um problema na atualização da despesa.");
    }
}
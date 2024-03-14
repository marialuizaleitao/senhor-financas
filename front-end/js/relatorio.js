const url = 'http://localhost:8080/senhor-financas/rest/relatorio/';

let dados = [];
let meuGrafico = null;

async function adicionarEventosRelatorio(){
    //const buscar = document.getElementById('gerar');
    gerar.addEventListener("click", (evento) => {
        evento.preventDefault();
        gerarRelatorio();
    });
}

async function gerarRelatorio(){
    let usuario = sessionStorage.getItem('usuario');
    let idUsuario = JSON.parse(usuario).idUsuario;
    let ano = document.getElementById('ano').value;
    let options = {
        method: 'GET',
        headers: {'Content-type': 'application/json'}
    };
    const relatorio = await fetch(url + idUsuario + "/" + ano, options);
    dados = await relatorio.json();
    if(dados.length > 0){
        gerarGrafico();
    } else {
        alert("Houve um problema na montagem do gráfico.");
    }
}

async function gerarGrafico() {
    const meses = ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'];
    const valoresReceita = dados.map(dado => dado.valorReceita);
    const valoresDespesa = dados.map(dado => dado.valorDespesa);
    const grafico = document.getElementById('grafico').getContext('2d');

    Chart.defaults.borderColor = '#000';
    Chart.defaults.color = '#fff';

    if(meuGrafico != null){
        meuGrafico.destroy();
    }

    meuGrafico = new Chart(grafico, {
        type: 'bar',
        data: {
            labels: meses,
            datasets: [{
                label: 'Total de Receitas',
                data: valoresReceita,
                backgroundColor: '#CCFFCC'
            }, 
            {
                label: 'Total de Despesas',
                data: valoresDespesa,
                backgroundColor: '#FFCCCC'
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });

}
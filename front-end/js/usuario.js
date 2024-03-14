async function cadastrarUsuario() {
    let options = {
        method: "POST",
        headers: { "Content-type": "application/json" },
        body: JSON.stringify({
            id: 0,
            nome: document.querySelector('#nome').value,
            cpf: document.querySelector('#cpf').value,
            email: document.querySelector('#email').value,
            dataNascimento: document.querySelector('#data-nascimento').value,
            login: document.querySelector('#login').value,
            senha: document.querySelector('#senha').value
        })
    };
    const resposta = await fetch('http://localhost:8080/senhor-financas/rest/usuario/cadastrar', options);
    pessoa = await resposta.json();
    if (pessoa.idUsuario != 0) {
        alert("Usuário cadastrado com sucesso!");        
        window.location.href = "./login.html";
    } else {
        alert("Houve um problema no cadastro do usuário.");
    }
    form.reset();
}

async function logarUsuario() {
    let options = {
        method: "POST",
        headers: { "Content-type": "application/json" },
        body: JSON.stringify({
            login: document.querySelector('#login').value,
            senha: document.querySelector('#senha').value
        })
    };
    const resposta = await fetch('http://localhost:8080/senhor-financas/rest/usuario/logar', options);
    const usuarioLogado = await resposta.json();
    if (usuarioLogado.idUsuario != 0) {
        sessionStorage.setItem('usuarioVO', JSON.stringify(usuarioLogado));
        window.location.href = "./telaReceitas.html";
    } else {
        alert("Login ou Senha incorretos.");
        document.querySelector("#senha").value = "";
    }
}
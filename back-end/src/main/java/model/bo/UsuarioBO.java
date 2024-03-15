package model.bo;

import model.dao.UsuarioDAO;
import model.vo.UsuarioVO;

// responsável por intermediar as operações relacionadas a UsuarioVO entre a camada de apresentação (view) e a camada de acesso a dados (DAO)

public class UsuarioBO {

	// cadastra um usuário
	public UsuarioVO cadastrarUsuarioBO(UsuarioVO usuarioVO) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();

		// verifica se o usuário já está cadastrado
		if (usuarioDAO.verificarCadastroUsuario(usuarioVO)) {
			System.out.println("\nPessoa já cadastrada na base.");
		} else {
			// tenta cadastrar o usuário e atualiza o objeto com o ID gerado
			usuarioVO = usuarioDAO.cadastrarUsuarioDAO(usuarioVO);
		}
		return usuarioVO;
	}

	// efetua o login de um usuário
	public UsuarioVO logarUsuario(UsuarioVO usuarioVO) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();

		return usuarioDAO.logarNoSistemaDAO(usuarioVO);
	}
}

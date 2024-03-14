package model.bo;

import java.util.ArrayList;

import model.dao.ReceitaDAO;
import model.vo.ReceitaVO;

// responsável por intermediar as operações relacionadas a ReceitaVO entre a camada de apresentação (view) e a camada de acesso a dados (DAO)

public class ReceitaBO {

	// cadastra uma receita
	public ReceitaVO cadastrarReceitaBO(ReceitaVO receitaVO) {
		ReceitaDAO receitaDAO = new ReceitaDAO();

		// verifica se a receita já está cadastrada
		if (receitaDAO.verificarCadastroReceita(receitaVO)) {
			System.out.println("\nReceita já cadastrada.");
			return null;
		} else {
			// tenta cadastrar a receita e informa o resultado
			boolean sucessoCadastro = receitaDAO.cadastrarReceitaDAO(receitaVO);

			if (sucessoCadastro) {
				System.out.println("\nReceita cadastrada com sucesso!");
				return receitaVO;
			} else {
				System.out.println("\nNão foi possível cadastrar a receita.");
				return null;
			}
		}
	}

	// atualiza uma receita
	public boolean atualizarReceitaBO(ReceitaVO receitaVO) {
		boolean resultado = false;
		ReceitaDAO receitaDAO = new ReceitaDAO();

		// tenta atualizar a receita e informa o resultado
		resultado = receitaDAO.atualizarReceitaDAO(receitaVO);
		return resultado;
	}

	// exclui uma receita
	public boolean excluirReceitaBO(ReceitaVO receitaVO) {
		boolean resultado = false;
		ReceitaDAO receitaDAO = new ReceitaDAO();

		// tenta excluir a receita e informa o resultado
		if (receitaDAO.excluirReceitaDAO(receitaVO)) {
			System.out.println("Receita excluída.");
			resultado = true;
		} else {
			System.out.println("Falha na exclusão da receita.");
		}
		return resultado;
	}

	// lista todas as receitas de um usuário
	public ArrayList<ReceitaVO> listarReceitasBO(int idUsuario) {
		ReceitaDAO receitaDAO = new ReceitaDAO();
		ArrayList<ReceitaVO> listaReceitasVO = receitaDAO.listarReceitasDAO(idUsuario);

		// informa se a lista de receitas está vazia
		if (listaReceitasVO.isEmpty()) {
			System.out.println("A lista de Receitas está vazia.");
		}
		return listaReceitasVO;
	}

	// pesquisa uma receita por ID
	public ReceitaVO pesquisarReceitaBO(int idReceita) {
		ReceitaDAO receitaDAO = new ReceitaDAO();
		ReceitaVO receita = receitaDAO.pesquisarReceitaDAO(idReceita);

		// informa se a receita não foi encontrada
		if (receita == null) {
			System.out.println("Receita não encontrada.");
		}
		return receita;
	}
}

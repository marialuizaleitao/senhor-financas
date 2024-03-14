package model.bo;

import java.util.ArrayList;

import model.dao.DespesaDAO;
import model.vo.DespesaVO;

// responsável por intermediar as operações relacionadas a DespesaVO entre a camada de apresentação (view) e a camada de acesso a dados (DAO)

public class DespesaBO {

	// cadastra uma despesa
	public DespesaVO cadastrarDespesaBO(DespesaVO despesaVO) {
		DespesaDAO despesaDAO = new DespesaDAO();

		// verifica se a despesa já está cadastrada
		if (despesaDAO.verificarCadastroDespesa(despesaVO)) {
			System.out.println("\nDespesa já cadastrada.");
			return null;
		} else {
			// tenta cadastrar a despesa e informa o resultado
			boolean sucessoCadastro = despesaDAO.cadastrarDespesaDAO(despesaVO);

			if (sucessoCadastro) {
				System.out.println("\nDespesa cadastrada com sucesso!");
				return despesaVO;
			} else {
				System.out.println("\nNão foi possível cadastrar a despesa.");
				return null;
			}
		}
	}

	// atualiza uma despesa
	public boolean atualizarDespesaBO(DespesaVO despesaVO) {
		boolean resultado = false;
		DespesaDAO despesaDAO = new DespesaDAO();

		// tenta atualizar a despesa e informa o resultado
		resultado = despesaDAO.atualizarDespesaDAO(despesaVO);
		return resultado;
	}

	// exclui uma despesa
	public boolean excluirDespesaBO(DespesaVO despesaVO) {
		boolean resultado = false;
		DespesaDAO despesaDAO = new DespesaDAO();

		// tenta excluir a despesa e informa o resultado
		resultado = despesaDAO.excluirDespesaDAO(despesaVO);
		return resultado;
	}

	// lista todas as despesas de um usuário
	public ArrayList<DespesaVO> listarDespesasBO(int idUsuario) {
		DespesaDAO despesaDAO = new DespesaDAO();
		ArrayList<DespesaVO> listaDespesasVO = despesaDAO.listarDespesasDAO(idUsuario);

		// informa se a lista de despesas está vazia
		if (listaDespesasVO.isEmpty()) {
			System.out.println("A lista de Despesas está vazia.");
		}
		return listaDespesasVO;
	}

	// pesquisa uma despesa por ID
	public DespesaVO pesquisarDespesaBO(int idDespesa) {
		DespesaDAO despesaDAO = new DespesaDAO();
		DespesaVO despesa = despesaDAO.pesquisarDespesaDAO(idDespesa);

		// informa se a despesa não foi encontrada
		if (despesa == null) {
			System.out.println("Despesa não encontrada.");
		}
		return despesa;
	}

}

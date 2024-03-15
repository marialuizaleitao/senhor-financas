package model.bo;

import java.util.ArrayList;

import model.dao.RelatorioDAO;
import model.dto.RelatorioDTO;

// responsável por intermediar as operações relacionadas a RelatorioDTO entre a camada de apresentação (view) e a camada de acesso a dados (DAO)

public class RelatorioBO {

	// gera relatório de um usuário para um determinado ano
	public ArrayList<RelatorioDTO> gerarRelatorioPorUsuarioBO(int idUsuario, int ano) {
		RelatorioDAO relatorioDAO = new RelatorioDAO();
		ArrayList<RelatorioDTO> listaRelatorioDTO = relatorioDAO.gerarRelatorioPorUsuarioDAO(idUsuario,
				ano);

		// informa se o relatório de receitas está vazio
		if (listaRelatorioDTO.isEmpty()) {
			System.out.println("\nRelatório de receitas do usuário está vazio.");
		}
		return listaRelatorioDTO;
	}

}

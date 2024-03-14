package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.vo.ReceitaVO;

public class ReceitaDAO {

	// verifica se a receita já foi cadastrada
	public boolean verificarCadastroReceita(ReceitaVO receitaVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;

		// monta a query SQL
		String query = "SELECT descricao, valor, datareceita FROM receita " + " WHERE descricao = '"
				+ receitaVO.getDescricao() + "' " + " AND valor = " + receitaVO.getValor() + " "
				+ " AND datareceita = '" + receitaVO.getDataReceita() + "' ";
		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				return true; // retorna true se a receita já existe
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query do método verificarCadastroReceita!");
			System.out.println("Erro: " + e.getMessage());
			return false;
		} finally { // fecha os recursos JDBC
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return false; // retorna false se a despesa não existe
	}

	// insert receita
	public boolean cadastrarReceitaDAO(ReceitaVO receitaVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean sucesso = false;

		String query = "INSERT INTO receita (idusuario, descricao, valor, datareceita) VALUES ("
				+ receitaVO.getIdUsuario() + ", '" + receitaVO.getDescricao() + "', " + receitaVO.getValor() + ", '"
				+ receitaVO.getDataReceita() + "')";

		try {
			int resultado = stmt.executeUpdate(query); // retorna 1 se for bem sucedida
			sucesso = resultado > 0;
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query do método cadastrarReceitaDAO!");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return sucesso; // retorna o resultado da operação
	}

	// update receita
	public boolean atualizarReceitaDAO(ReceitaVO receitaVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean sucesso = false;

		String query = "UPDATE receita SET idusuario = " + receitaVO.getIdUsuario() + ", descricao = '"
				+ receitaVO.getDescricao() + "', valor = " + receitaVO.getValor() + ", datareceita = '"
				+ receitaVO.getDataReceita() + "' WHERE idreceita = " + receitaVO.getIdReceita();

		try {
			int resultado = stmt.executeUpdate(query);
			sucesso = resultado > 0;
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query do método atualizarReceitaDAO!");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return sucesso;
	}

	// delete receita
	public boolean excluirReceitaDAO(ReceitaVO receitaVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean sucesso = false;

		String query = "DELETE FROM receita WHERE idreceita = " + receitaVO.getIdReceita();

		try {
			int resultado = stmt.executeUpdate(query);
			sucesso = resultado > 0;
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query do método excluirReceitaDAO!");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return sucesso;
	}

	// lista todas as receitas de um usuário
	public ArrayList<ReceitaVO> listarReceitasDAO(int idUsuario) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;

		// cria a lista que armazenará as receitas
		ArrayList<ReceitaVO> listarReceitas = new ArrayList<ReceitaVO>();
		String query = "SELECT idreceita, idusuario, descricao, valor, datareceita FROM receita WHERE idusuario = "
				+ idUsuario;

		try { // executa a query e adiciona as receitas à lista
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				ReceitaVO receita = new ReceitaVO();
				receita.setIdReceita(resultado.getInt(1));
				receita.setIdUsuario(resultado.getInt(2));
				receita.setDescricao(resultado.getString(3));
				receita.setValor(resultado.getDouble(4));
				receita.setDataReceita(LocalDate.parse(resultado.getString(5),
						DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				listarReceitas.add(receita);
			}
		} catch (SQLException erro) {
			System.out.println("\nErro ao executar a query do método listarReceitasDAO!");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return listarReceitas;
	}

	// pesquisa uma receita por ID
	public ReceitaVO pesquisarReceitaDAO(int idReceita) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		ResultSet resultado = null;
		ReceitaVO receita = new ReceitaVO(); // instancia uma ReceitaVO a ser preenchida pela receita desejada
		String query = "SELECT idreceita, idusuario, descricao, valor, datareceita FROM receita WHERE idreceita = "
				+ idReceita;

		try { // executa a query e preenche o objeto ReceitaVO
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				receita.setIdReceita(resultado.getInt(1));
				receita.setIdUsuario(resultado.getInt(2));
				receita.setDescricao(resultado.getString(3));
				receita.setValor(resultado.getDouble(4));
				receita.setDataReceita(LocalDate.parse(resultado.getString(5),
						DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			}
		} catch (SQLException erro) {
			System.out.println("\nErro ao executar a query do método pesquisarReceitaDAO!");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return receita; // retorna o objeto ReceitaVO encontrado
	}

}

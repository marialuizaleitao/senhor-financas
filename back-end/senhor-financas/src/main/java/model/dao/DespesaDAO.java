package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.vo.DespesaVO;

public class DespesaDAO {

	// verifica se a despesa já foi cadastrada
	public boolean verificarCadastroDespesa(DespesaVO despesaVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;

		// monta a query SQL
		String query = "SELECT descricao, valor, datavencimento, datapagamento FROM despesa " + " WHERE descricao = '"
				+ despesaVO.getDescricao() + "' " + " AND valor = " + despesaVO.getValor() + " "
				+ " AND datavencimento = '" + despesaVO.getDataVencimento() + "' " + " AND datapagamento = '"
				+ despesaVO.getDataPagamento() + "' ";

		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				return true; // retorna true se a despesa já existe
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query do método verificarCadastroDespesa!");
			System.out.println("Erro: " + e.getMessage());
			return false;
		} finally { // fecha os recursos JDBC
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return false; // retorna false se a despesa não existe
	}

	// insert despesa	
	public boolean cadastrarDespesaDAO(DespesaVO despesaVO) {
        String query = "INSERT INTO despesa (idusuario, descricao, valor, datavencimento, datapagamento) VALUES (?, ?, ?, ?, ?)";
        Connection conn = Banco.getConnection();
        PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
        boolean sucesso = false;
        
        try {
            pstmt.setInt(1, despesaVO.getIdUsuario());
            pstmt.setString(2, despesaVO.getDescricao());
            pstmt.setDouble(3, despesaVO.getValor());
            pstmt.setObject(4, despesaVO.getDataVencimento());
            pstmt.setObject(5, despesaVO.getDataPagamento());
            pstmt.execute();
            ResultSet resultado = pstmt.getGeneratedKeys();
            if(resultado.next()) {
                sucesso = true;
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao executar a query do método cadastrarDespesaDAO!");
            System.out.println("Erro: " + erro.getMessage());
        } finally {
            Banco.closeStatement(pstmt);
            Banco.closeConnection(conn);
        }
        return sucesso;
    }

	// update despesa	
	public boolean atualizarDespesaDAO(DespesaVO despesaVO) {
		String query = "UPDATE despesa SET idusuario = ?, descricao = ?, valor = ?, datavencimento = ?, datapagamento = ? WHERE iddespesa = " + despesaVO.getIdDespesa();
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		boolean sucesso = false;

		try {
			pstmt.setInt(1, despesaVO.getIdUsuario());
            pstmt.setString(2, despesaVO.getDescricao());
            pstmt.setDouble(3, despesaVO.getValor());
            pstmt.setObject(4, despesaVO.getDataVencimento());
            pstmt.setObject(5, despesaVO.getDataPagamento());
            pstmt.execute();
            ResultSet resultado = pstmt.getGeneratedKeys();
            if(resultado.next()) {
                sucesso = true;
            }
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query do método atualizarDespesaDAO!");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return sucesso;
	}

	// delete despesa
	public boolean excluirDespesaDAO(DespesaVO despesaVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean sucesso = false;

		String query = "DELETE FROM despesa WHERE iddespesa = " + despesaVO.getIdDespesa();

		try {
			int resultado = stmt.executeUpdate(query);
			sucesso = resultado > 0;
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query do método excluirDespesaDAO!");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return sucesso;
	}

	// lista todas as despesas de um usuário
	public ArrayList<DespesaVO> listarDespesasDAO(int idUsuario) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;

		// cria a lista que armazenará as despesas
		ArrayList<DespesaVO> listarDespesas = new ArrayList<DespesaVO>();
		String query = "SELECT iddespesa, idusuario, descricao, valor, datavencimento, datapagamento FROM despesa WHERE idusuario = "
				+ idUsuario;

		try { // executa a query e adiciona as despesas à lista
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				DespesaVO despesa = new DespesaVO();
				despesa.setIdDespesa(Integer.parseInt(resultado.getString(1)));
				despesa.setIdUsuario(resultado.getInt(2));
				despesa.setDescricao(resultado.getString(3));
				despesa.setValor(resultado.getDouble(4));
				despesa.setDataVencimento(
						LocalDate.parse(resultado.getString(5), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				if (resultado.getString(6) != null) {
					despesa.setDataPagamento(
							LocalDate.parse(resultado.getString(6), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				}
				listarDespesas.add(despesa);
			}
		} catch (SQLException erro) {
			System.out.println("\nErro ao executar a query do método listarDespesasDAO!");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return listarDespesas;
	}

	// pesquisa uma despesa por ID
	public DespesaVO pesquisarDespesaDAO(int idDespesa) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		ResultSet resultado = null;
		DespesaVO despesa = new DespesaVO(); // instancia uma DespesaVO a ser preenchida pela despesa desejada
		String query = "SELECT iddespesa, idusuario, descricao, valor, datavencimento, datapagamento FROM despesa WHERE iddespesa = "
				+ idDespesa;

		try { // executa a query e preenche o objeto DespesaVO
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				despesa.setIdDespesa(Integer.parseInt(resultado.getString(1)));
				despesa.setIdUsuario(resultado.getInt(2));
				despesa.setDescricao(resultado.getString(3));
				despesa.setValor(resultado.getDouble(4));
				despesa.setDataVencimento(
						LocalDate.parse(resultado.getString(5), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				if (resultado.getString(6) != null) {
					despesa.setDataPagamento(
							LocalDate.parse(resultado.getString(6), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				}
			}
		} catch (SQLException erro) {
			System.out.println("\nErro ao executar a query do método pesquisarDespesaDAO!");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return despesa; // retorna o objeto DespesaVO encontrado
	}

}

package model.vo;

import java.text.DecimalFormat;
import java.time.LocalDate;

public class DespesaVO {

	private int idDespesa;
	private int idUsuario;
	private String descricao;
	private double valor;
	private LocalDate dataVencimento;
	private LocalDate dataPagamento;

	DecimalFormat df = new DecimalFormat("0.00");

	public DespesaVO(int idDespesa, int idUsuario, String descricao, double valor, LocalDate dataVencimento,
			LocalDate dataPagamento) {
		super();
		this.idDespesa = idDespesa;
		this.idUsuario = idUsuario;
		this.descricao = descricao;
		this.valor = valor;
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}

	public DespesaVO() {
		super();
	}

	public int getIdDespesa() {
		return idDespesa;
	}

	public void setIdDespesa(int idDespesa) {
		this.idDespesa = idDespesa;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	@Override
	public String toString() {
		return "Código de Despesa: " + this.getIdDespesa() + "\nUsuário: " + this.getIdUsuario() + "\nDescrição: "
				+ this.getDescricao() + "\nValor: " + df.format(this.getValor()) + "\nData de Vencimento: "
				+ this.getDataVencimento() + "\nData de Pagamento: " + validarData(this.getDataPagamento());
	}

	private String validarData(LocalDate data) {
		String resultado = "";
		if (data != null) {
			resultado = data.toString();
		}
		return resultado;
	}

}

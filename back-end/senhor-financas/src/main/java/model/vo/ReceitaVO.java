package model.vo;

import java.text.DecimalFormat;
import java.time.LocalDate;

public class ReceitaVO {

	private int idReceita;
	private int idUsuario;
	private String descricao;
	private double valor;
	private LocalDate dataReceita;

	DecimalFormat df = new DecimalFormat("0.00");

	public ReceitaVO(int idReceita, int idUsuario, String descricao, double valor, LocalDate dataReceita) {
		super();
		this.idReceita = idReceita;
		this.idUsuario = idUsuario;
		this.descricao = descricao;
		this.valor = valor;
		this.dataReceita = dataReceita;
	}

	public ReceitaVO() {
		super();
	}

	public int getIdReceita() {
		return idReceita;
	}

	public void setIdReceita(int idReceita) {
		this.idReceita = idReceita;
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

	public LocalDate getDataReceita() {
		return dataReceita;
	}

	public void setDataReceita(LocalDate dataReceita) {
		this.dataReceita = dataReceita;
	}

	@Override
	public String toString() {
		return "Código de Receita: " + this.getIdReceita() + "\nUsuário: " + this.getIdUsuario() + "\nDescrição: "
				+ this.getDescricao() + "\nValor: " + df.format(this.getValor()) + "\nData da Receita: "
				+ this.getDataReceita();
	}

}

package model.dto;

public class RelatorioDTO {

	private int mes;
	private double valorReceita;
	private double valorDespesa;

	public RelatorioDTO(int mes, double valorReceita, double valorDespesa) {
		super();
		this.mes = mes;
		this.valorReceita = valorReceita;
		this.valorDespesa = valorDespesa;
	}

	public RelatorioDTO() {
		super();
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public double getValorReceita() {
		return valorReceita;
	}

	public void setValorReceita(double valorReceita) {
		this.valorReceita = valorReceita;
	}

	public double getValorDespesa() {
		return valorDespesa;
	}

	public void setValorDespesa(double valorDespesa) {
		this.valorDespesa = valorDespesa;
	}

}

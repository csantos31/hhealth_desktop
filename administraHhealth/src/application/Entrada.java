package application;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Entrada {
	int id;
	LocalDate data;
	Float valor;
	String descricao;

	@Override
	public String toString(){
		return getData().toString();
	}

	public Date toDate(){
		return Date.from(getData().atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public Float getValor() {
		return valor;
	}
	public void setValor(Float valor) {
		this.valor = valor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}

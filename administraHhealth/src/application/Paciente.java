package application;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

public class Paciente {

	private String codigo;
	private String nome;
	private Time hora;
	private LocalDate data;

	public String fromString(){
		return getCodigo();
	}

	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Time getHora() {
		return hora;
	}
	public void setHora(Time hora) {
		this.hora = hora;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}



}

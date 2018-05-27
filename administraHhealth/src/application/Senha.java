package application;

public class Senha {
	private int id;
	private char emergencia;
	private String senha;
	private char ativo;

	public String fromString(){
		return getSenha();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public char getEmergencia() {
		return emergencia;
	}
	public void setEmergencia(char emergencia) {
		this.emergencia = emergencia;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public char getAtivo() {
		return ativo;
	}
	public void setAtivo(char ativo) {
		this.ativo = ativo;
	}
}

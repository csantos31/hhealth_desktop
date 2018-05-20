package application;

public class Unidade {

	int id_unidade;
	int id_endereco;
	String imagem;
	String nome_unidade;
	char status;
	char ativo;
	String texto;
	String telefone;

	@Override
	public String toString(){
		return getNome_unidade();
	}

	public Unidade(){
	}

	public Unidade(String nome_unidade){
		this.nome_unidade = nome_unidade;
	}

	public int getId_unidade() {
		return id_unidade;
	}
	public void setId_unidade(int id_unidade) {
		this.id_unidade = id_unidade;
	}
	public int getId_endereco() {
		return id_endereco;
	}
	public void setId_endereco(int id_endereco) {
		this.id_endereco = id_endereco;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	public String getNome_unidade() {
		return nome_unidade;
	}
	public void setNome_unidade(String nome_unidade) {
		this.nome_unidade = nome_unidade;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public char getAtivo() {
		return ativo;
	}
	public void setAtivo(char ativo) {
		this.ativo = ativo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}



}

package application;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Cadastra_entrada {
	Entrada entradaAtualizar;
	private Boolean atualizacao = false;

	@FXML DatePicker txt_data;
	@FXML TextField txt_valor;
	@FXML TextArea txt_descricao;

	EntradaDao dao = new EntradaDao();

	public void initialize(){
		if(entradaAtualizar != null){
			txt_descricao.setText(entradaAtualizar.getDescricao());
			txt_data.setValue(entradaAtualizar.getData());
			txt_valor.setText(entradaAtualizar.getValor().toString());
			atualizacao = true;
		}

	}

	@FXML public void salvarEntrada(){
		if(atualizacao){
			LocalDate date = txt_data.getValue();
			entradaAtualizar.setData(date);
			entradaAtualizar.setDescricao(txt_descricao.getText());
			entradaAtualizar.setValor(Float.parseFloat(String.valueOf(txt_valor.getText())));
			dao.atualizar(entradaAtualizar);
		}else{
			Entrada entrada = new Entrada();
			LocalDate date = txt_data.getValue();
			entrada.setData(date);
			entrada.setDescricao(txt_descricao.getText());
			entrada.setValor(Float.parseFloat(String.valueOf(txt_valor.getText())));
			dao.inserir(entrada);
		}

		Main.abrirTela("ver_entradas", true);
	}

	@FXML public void voltar(){
		Main.abrirTela("ver_entradas", true);
	}
}

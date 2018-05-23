package application;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Cadastra_gasto {

	Gasto gastoAtualizar;
	private Boolean atualizacao = false;

	@FXML DatePicker txt_data;
	@FXML TextField txt_valor;
	@FXML TextArea txt_descricao;

	GastoDao dao = new GastoDao();

	public void initialize(){
		if(gastoAtualizar != null){
			txt_descricao.setText(gastoAtualizar.getDescricao());
			txt_data.setValue(gastoAtualizar.getData());
			txt_valor.setText(gastoAtualizar.getValor().toString());
			atualizacao = true;
		}

	}

	@FXML public void salvarGasto(){
		if(atualizacao){
			LocalDate date = txt_data.getValue();
			gastoAtualizar.setData(date);
			gastoAtualizar.setDescricao(txt_descricao.getText());
			gastoAtualizar.setValor(Float.parseFloat(String.valueOf(txt_valor.getText())));
			dao.atualizar(gastoAtualizar);
		}else{
			Gasto g = new Gasto();
			LocalDate date = txt_data.getValue();
			g.setData(date);
			g.setDescricao(txt_descricao.getText());
			g.setValor(Float.parseFloat(String.valueOf(txt_valor.getText())));
			dao.inserir(g);
		}

		Main.abrirTela("ver_gastos", true);
	}

	@FXML public void voltar(){
		Main.abrirTela("ver_gastos", true);
	}
}

package application;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Cadastra_veiculo {

	Veiculo veiculoAtualizar;
	private Boolean atualizacao = false;

	@FXML ComboBox<Unidade> cbo_unidade;
	@FXML TextField txt_placa;
	@FXML TextArea txt_descricao;

	VeiculoDao dao = new VeiculoDao();
	UnidadeDao dao_uni = new UnidadeDao();

	public void initialize(){
		ArrayList<Unidade> comboUnidades = new UnidadeDao().obterTodos();
		ObservableList<Unidade> oList = FXCollections.observableArrayList(comboUnidades);
		cbo_unidade.setItems(oList);

		if(veiculoAtualizar != null){
			txt_descricao.setText(veiculoAtualizar.getDescricao());
			txt_placa.setText(veiculoAtualizar.getPlaca());
			Unidade a_unidade = dao_uni.listarUnidade(veiculoAtualizar.getUnidade());
			cbo_unidade.getSelectionModel().select(a_unidade);
			atualizacao = true;
		}

	}

	@FXML public void salvarVeiculo(){

		if(atualizacao){
			veiculoAtualizar.setPlaca(txt_placa.getText());
			veiculoAtualizar.setDescricao(txt_descricao.getText());
			Unidade uni = cbo_unidade.getSelectionModel().getSelectedItem();
			veiculoAtualizar.setUnidade(uni.getId_unidade());
			dao.atualizar(veiculoAtualizar);
		}else{
			Veiculo v = new Veiculo();
			v.setPlaca(txt_placa.getText());
			v.setDescricao(txt_descricao.getText());
			Unidade uni = cbo_unidade.getSelectionModel().getSelectedItem();
			v.setUnidade(uni.getId_unidade());
			dao.inserir(v);
		}

		Main.abrirTela("ver_veiculos", true);
	}

	@FXML public void voltar(){
		Main.abrirTela("ver_veiculos", true);
	}

	public void limpar(){
		txt_placa.setText("");
		txt_descricao.setText("");
	}
}

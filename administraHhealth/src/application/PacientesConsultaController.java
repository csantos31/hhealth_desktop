package application;

import java.sql.Time;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class PacientesConsultaController {

	@FXML ImageView gerarqrcode;
	@FXML ImageView retornarapp;
	@FXML TableView tblpacientes;
	@FXML TableColumn colnome;
	@FXML TableColumn colhora;

	PacienteDao dao;

	public void initialize(){
		dao = new PacienteDao();

		colnome.setCellValueFactory(new PropertyValueFactory<Paciente, String>("nome"));
		colhora.setCellValueFactory(new PropertyValueFactory<Paciente, Time>("hora"));
		ArrayList<Paciente> lstpacientes = dao.obterComConsultas();
		tblpacientes.setItems(FXCollections.observableArrayList(lstpacientes));
		tblpacientes.setPadding(new Insets(20, 20, 20, 20));
		tblpacientes.setStyle("-fx-font: 16 verdana;");

	}

	public void buscar(){

	}
}

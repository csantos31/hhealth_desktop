package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;

public class Gasto_controller {

	@FXML ImageView img_add;
	@FXML ImageView menu_registrar_gasto;
	@FXML ImageView menu_registrar_veiculos;
	@FXML ImageView editarGasto;
	@FXML ImageView deletarGasto;
	@FXML TableView tbl_gastos;
	@FXML TableColumn coluna_data;
	@FXML TableColumn coluna_valor;
	@FXML TableColumn coluna_descricao;

	GastoDao dao;

	public void initialize(){
		dao = new GastoDao();

		coluna_data.setCellValueFactory(new PropertyValueFactory<Gasto, LocalDate>("data"));
        coluna_descricao.setCellValueFactory(new PropertyValueFactory<Gasto, String>("descricao"));
        coluna_valor.setCellValueFactory(new PropertyValueFactory<Gasto, Float>("valor"));
        ArrayList<Gasto> lst_gasto = dao.obterTodos();
        tbl_gastos.setItems(FXCollections.observableArrayList(lst_gasto));
        tbl_gastos.setPadding(new Insets(20, 20, 20, 20));
        tbl_gastos.setStyle("-fx-font: 16 verdana;");


        img_add.setPickOnBounds(true); // allows click on transparent areas
        img_add.setOnMouseClicked((MouseEvent e) -> {
        	Cadastra_gasto controller = new Cadastra_gasto();
        	Main.abrirTela("cadastra_saida",false, controller);
        });

        editarGasto.setPickOnBounds(true); // allows click on transparent areas
        editarGasto.setOnMouseClicked((MouseEvent e) -> {
            Gasto g = (Gasto)tbl_gastos.getSelectionModel().getSelectedItem();

            Cadastra_gasto controller = new Cadastra_gasto();
            controller.gastoAtualizar = g;

            Main.abrirTela("cadastra_saida", false, controller);
        });

        deletarGasto.setPickOnBounds(true); // allows click on transparent areas
        deletarGasto.setOnMouseClicked((MouseEvent e) -> {
            Gasto g = (Gasto)tbl_gastos.getSelectionModel().getSelectedItem();

            dao.deletar(g);

            tbl_gastos.getItems().remove(g);
        });

        menu_registrar_gasto.setPickOnBounds(true); // allows click on transparent areas
        menu_registrar_gasto.setOnMouseClicked((MouseEvent e) -> {
            Main.abrirTela("ver_gastos", true);
        });

        menu_registrar_veiculos.setPickOnBounds(true); // allows click on transparent areas
        menu_registrar_veiculos.setOnMouseClicked((MouseEvent e) -> {
            Main.abrirTela("ver_veiculos", true);
        });

	}
}

package application;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Entrada_controller {

	@FXML ImageView img_add;
	@FXML ImageView menu_registrar_gasto;
	@FXML ImageView menu_registrar_veiculos;
	@FXML ImageView menu_registrar_entrada;
	@FXML ImageView editarEntrada;
	@FXML ImageView deletarEntrada;
	@FXML TableView tbl_entradas;
	@FXML TableColumn coluna_data;
	@FXML TableColumn coluna_valor;
	@FXML TableColumn coluna_descricao;

	EntradaDao dao;

	public void initialize(){
		dao = new EntradaDao();

		coluna_data.setCellValueFactory(new PropertyValueFactory<Gasto, LocalDate>("data"));
        coluna_descricao.setCellValueFactory(new PropertyValueFactory<Gasto, String>("descricao"));
        coluna_valor.setCellValueFactory(new PropertyValueFactory<Gasto, Float>("valor"));
        ArrayList<Entrada> lst_entrada = dao.obterTodos();
        tbl_entradas.setItems(FXCollections.observableArrayList(lst_entrada));
        tbl_entradas.setPadding(new Insets(20, 20, 20, 20));
        tbl_entradas.setStyle("-fx-font: 16 verdana;");


        img_add.setPickOnBounds(true); // allows click on transparent areas
        img_add.setOnMouseClicked((MouseEvent e) -> {
        	Cadastra_entrada controller = new Cadastra_entrada();
        	Main.abrirTela("cadastra_entrada",false, controller);
        });

        editarEntrada.setPickOnBounds(true); // allows click on transparent areas
        editarEntrada.setOnMouseClicked((MouseEvent e) -> {
            Entrada entrada = (Entrada)tbl_entradas.getSelectionModel().getSelectedItem();

            Cadastra_entrada controller = new Cadastra_entrada();
            controller.entradaAtualizar = entrada;

            Main.abrirTela("cadastra_entrada", false, controller);
        });

        deletarEntrada.setPickOnBounds(true); // allows click on transparent areas
        deletarEntrada.setOnMouseClicked((MouseEvent e) -> {
            Entrada entrada = (Entrada)tbl_entradas.getSelectionModel().getSelectedItem();

            dao.deletar(entrada);

            tbl_entradas.getItems().remove(entrada);
        });

        menu_registrar_gasto.setPickOnBounds(true); // allows click on transparent areas
        menu_registrar_gasto.setOnMouseClicked((MouseEvent e) -> {
            Main.abrirTela("ver_gastos", true);
        });

        menu_registrar_veiculos.setPickOnBounds(true); // allows click on transparent areas
        menu_registrar_veiculos.setOnMouseClicked((MouseEvent e) -> {
            Main.abrirTela("ver_veiculos", true);
        });

        menu_registrar_entrada.setPickOnBounds(true); // allows click on transparent areas
        menu_registrar_entrada.setOnMouseClicked((MouseEvent e) -> {
            Main.abrirTela("ver_veiculos", true);
        });

	}
}

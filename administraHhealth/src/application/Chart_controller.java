package application;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Chart_controller {

	@FXML LineChart<String,Number> lineChart;

	GastoDao gastoDao;
	EntradaDao entradaDao;
	@FXML ImageView menu_registrar_gasto;
	@FXML ImageView menu_registrar_veiculos;
	@FXML ImageView menu_registrar_entrada;
	@FXML ImageView menu_registrar_resumo;

	public void initialize(){

		gastoDao = new GastoDao();
		entradaDao = new EntradaDao();

		ArrayList<Gasto> lstgastos = gastoDao.obterTodos();
		ArrayList<Entrada> lstentradas = entradaDao.obterTodos();

		XYChart.Series<String,Number> gastos = new XYChart.Series<String,Number>();

		for (Object object : lstgastos) {
			Gasto gasto = new Gasto();
			gasto = (Gasto) object;
			gastos.getData().add(new XYChart.Data<String, Number>(gasto.getData().toString(), gasto.getValor()));
            System.out.println(gasto.getDescricao());
        }

		gastos.setName("Gastos");
		lineChart.getData().add(gastos);

		XYChart.Series<String,Number> entradas = new XYChart.Series<String,Number>();
		for (Object object2 : lstentradas) {
			Entrada entrada = new Entrada();
			entrada = (Entrada) object2;
			entradas.getData().add(new XYChart.Data<String, Number>(entrada.getData().toString(), entrada.getValor()));
		}
		entradas.setName("Entradas");
		lineChart.getData().add(entradas);

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
            Main.abrirTela("ver_entradas", true);
        });

        menu_registrar_resumo.setPickOnBounds(true); // allows click on transparent areas
        menu_registrar_resumo.setOnMouseClicked((MouseEvent e) -> {
            Main.abrirTela("chart_resumo", true);
        });

	}

}

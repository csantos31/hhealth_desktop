package application;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import org.magiclen.magicqrcode.QRCodeEncoder;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PacientesConsultaController {

	@FXML Label gerarqrcode;
	@FXML Label senhaemergencia;
	@FXML ImageView retornarapp;
	@FXML TableView tblpacientes;
	@FXML TableColumn colnome;
	@FXML TableColumn colhora;
	@FXML TextField txtbusca;
	Stage primaryStage;

	PacienteDao dao;
	SenhaDao senhaDao;

	public Boolean pesquisa = false;
	public ArrayList<Paciente> lstpesquisa;

	public void initialize(){
		dao = new PacienteDao();
		senhaDao = new SenhaDao();

		colnome.setCellValueFactory(new PropertyValueFactory<Paciente, String>("nome"));
		colhora.setCellValueFactory(new PropertyValueFactory<Paciente, Time>("hora"));
		ArrayList<Paciente> lstpacientes = dao.obterComConsultas();
		if(pesquisa == false){
			tblpacientes.setItems(FXCollections.observableArrayList(lstpacientes));
		}else{
			tblpacientes.setItems(FXCollections.observableArrayList(lstpesquisa));
		}
		tblpacientes.setPadding(new Insets(20, 20, 20, 20));
		tblpacientes.setStyle("-fx-font: 16 verdana;");

		retornarapp.setPickOnBounds(true); // allows click on transparent areas
        retornarapp.setOnMouseClicked((MouseEvent e) -> {
            Main.abrirTela("ver_veiculos", true);
        });

        senhaemergencia.setPickOnBounds(true); // allows click on transparent areas
        senhaemergencia.setOnMouseClicked((MouseEvent e) -> {
        	primaryStage = new Stage();

        	double numero = Math.random() * 100;
        	double valorAleatorio = Math.round(numero);

    		Senha senha = new Senha();
    		senha.setSenha(String.valueOf(valorAleatorio));

    		senhaDao.inserirEmergencia(senha);

    		QRCodeEncoder qr = new QRCodeEncoder(String.valueOf(valorAleatorio));
    		Canvas canvas = new Canvas(300, 250);
            GraphicsContext gc = canvas.getGraphicsContext2D();
            boolean[][] qrData = qr.encode();

            Group root = new Group();

    		drawQRCode(canvas, qrData);

    		root.getChildren().add(canvas);
    		primaryStage.setScene(new Scene(root));
    		primaryStage.show();
        });


		gerarqrcode.setPickOnBounds(true); // allows click on transparent areas
        gerarqrcode.setOnMouseClicked((MouseEvent e) -> {

        	Paciente paciente = (Paciente) tblpacientes.getSelectionModel().getSelectedItem();

        	if(paciente == null){
        		JOptionPane.showMessageDialog(null, "Por favor, selecione um paciente", "Erro", JOptionPane.ERROR_MESSAGE);
        	}else{
        		primaryStage = new Stage();

        		String codigo = paciente.fromString();

        		Senha senha = new Senha();
        		senha.setSenha(codigo);

        		senhaDao.inserir(senha);

        		QRCodeEncoder qr = new QRCodeEncoder(codigo);
        		Canvas canvas = new Canvas(300, 250);
                GraphicsContext gc = canvas.getGraphicsContext2D();
                boolean[][] qrData = qr.encode();

                Group root = new Group();

        		drawQRCode(canvas, qrData);

        		root.getChildren().add(canvas);
        		primaryStage.setScene(new Scene(root));
        		primaryStage.show();
        	}
        });
	}

	public void buscar(){
		Paciente paciente = new Paciente();
		if(txtbusca.getText() != null){
			paciente.setNome(txtbusca.getText().toString());
			ArrayList<Paciente> lstpesquisa = dao.pesquisar(paciente);

			PacientesConsultaController controller = new PacientesConsultaController();
			controller.lstpesquisa = lstpesquisa;
			controller.pesquisa = true;

			Main.abrirTela("pacientes_com_consulta", true, controller);
		}else{
			JOptionPane.showMessageDialog(null,"O campo de busca n�o pode estar vazio!", "Erro", JOptionPane.ERROR_MESSAGE);;
		}
	}

	public static void drawQRCode(final Canvas canvas, final boolean[][] qrData) {
		final int width = (int)canvas.getWidth();
		final int height = (int)canvas.getHeight();
		final GraphicsContext gc = canvas.getGraphicsContext2D();

		// Draw the background(white)
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, width, height);

		final int imageSize = Math.min(width, height);
		final int length = qrData.length;
		final int size = imageSize / length;
		final int actualImageSize = size * length;
		final int offsetImageX = (width - actualImageSize) / 2;
		final int offsetImageY = (height - actualImageSize) / 2;

		// Draw the data(true is black)
		gc.setFill(Color.BLACK);
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				if (qrData[i][j]) {
					final int x = i * size + offsetImageX;
					final int y = j * size + offsetImageY;
					gc.fillRect(x, y, size, size);
				}
			}
		}
	}
}

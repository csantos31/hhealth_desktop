package application;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Date;
import java.util.GregorianCalendar;

import org.magiclen.magicqrcode.QRCodeEncoder;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;


public class Main extends Application {

	static Stage primaryStage;
	@FXML TextField txtusuario;
	@FXML TextField txtsenha;


	@Override
	public void start(Stage primaryStage) {
		//Main.primaryStage = primaryStage;
		//abrirTela("login", false);

		QRCodeEncoder qr = new QRCodeEncoder("4574");
		Canvas canvas = new Canvas(300, 250);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        boolean[][] qrData = qr.encode();

        Group root = new Group();

		drawQRCode(canvas, qrData);

		root.getChildren().add(canvas);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();

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

	@FXML public void logar(){
		Acesso acesso = new AcessoDao().login(txtusuario.getText(),txtsenha.getText());
		System.out.println(acesso + "here is the access");
		if(acesso != null){
			abrirTela("ver_veiculos", true);
		}

	}

	public static void abrirTela(String fileName, Boolean resizable){
		Parent tela;

		try{
			tela = FXMLLoader.load(Main.class.getResource(fileName + ".fxml"));
			Scene scene = new Scene(tela);
			primaryStage.setScene(scene);
			primaryStage.setResizable(resizable);
			primaryStage.show();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public static void abrirTela(String fileName, Boolean resizable, Object controller){
		Parent tela;
		try{
			FXMLLoader loader = new FXMLLoader(Main.class.getResource(fileName+".fxml"));

			//definindo controller
			loader.setController(controller);

			//carregar o arquivo XML
			tela = loader.load();


			//Criando a cena
			Scene sc = new Scene(tela);

			primaryStage.setResizable(resizable);

			//Exibindo a cena no stage principal
			primaryStage.setScene(sc);
			primaryStage.show();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}



	public static void main(String[] args) {
		launch(args);
	}


}

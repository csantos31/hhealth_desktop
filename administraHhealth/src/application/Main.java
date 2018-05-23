package application;

import java.sql.Connection;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;


public class Main extends Application {

	static Stage primaryStage;
	@FXML TextField txtusuario;
	@FXML TextField txtsenha;

	@Override
	public void start(Stage primaryStage) {
		Main.primaryStage = primaryStage;
		abrirTela("ver_gastos", true);
	}


	@FXML public void logar(){
		Acesso acesso = new AcessoDao().login(txtusuario.getText(),txtsenha.getText());
		System.out.println(acesso + "here is the access");
		if(acesso != null){
			abrirTela("login", false);
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

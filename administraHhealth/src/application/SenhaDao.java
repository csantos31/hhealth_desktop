package application;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class SenhaDao {

	Connection conn = (Connection) DBUtils.getConnection();

	public void inserir(Senha...senhas){

		for(Senha s : senhas){

			String sql = "INSERT INTO tbl_senha set emergencia = 0, senha = ?, ativo = 1;";

			try{
				PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

				ps.setString(1, s.getSenha());

				int rowsInserted = ps.executeUpdate();
				if (rowsInserted > 0) {
				    System.out.println("Inserido com sucesso");
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}

		}
	}

	public void inserirEmergencia(Senha...senhas){

		for(Senha s : senhas){


			String sql = "INSERT INTO tbl_senha set emergencia = 1, senha = ?, ativo = 1;";

			try{
				PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

				ps.setString(1, s.getSenha());

				int rowsInserted = ps.executeUpdate();
				if (rowsInserted > 0) {
				    System.out.println("Inserido com sucesso");
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}

		}
	}

}

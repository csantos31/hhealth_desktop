package application;

import java.time.LocalDate;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

public class EntradaDao {
	Connection conn = (Connection) DBUtils.getConnection();

	public void inserir(Entrada...entradas){

		for(Entrada entrada : entradas){


			String sql = "INSERT INTO tbl_entrada_hospital set data= ?, valor = ?, descricao =?;";

			try{
				PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
				String dat = entrada.getData().toString();

				ps.setDate(1,  java.sql.Date.valueOf(dat));
				ps.setFloat(2, entrada.getValor());
				ps.setString(3, entrada.getDescricao());

				System.out.println(ps);

				int rowsInserted = ps.executeUpdate();
				if (rowsInserted > 0) {
				    System.out.println("Inserido com sucesso");
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}

		}
	}

	public ArrayList<Entrada> obterTodos(){

		ArrayList<Entrada> entradas =  new ArrayList<>();


		String SQL ="SELECT * FROM tbl_entrada_hospital;";

		try{

			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(SQL);

			ResultSet rs = (ResultSet) ps.executeQuery();

			while(rs.next()){

				Entrada entrada = new Entrada();
				entrada.setId(rs.getInt("id_entrada_hospital"));
				LocalDate date = rs.getDate("data").toLocalDate();
				entrada.setData(date);
				entrada.setDescricao(rs.getString("descricao"));
				entrada.setValor(rs.getFloat("valor"));
				entradas.add(entrada);
			}


		}catch(Exception ex){
			ex.printStackTrace();
		}

		return entradas;

	}

	public boolean atualizar(Entrada entrada){
		String sql = "UPDATE tbl_entrada_hospital set data= ?, valor = ?, descricao =? WHERE id_entrada_hospital = ?;";
		try{
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			String dat = entrada.getData().toString();

			ps.setDate(1,  java.sql.Date.valueOf(dat));
			ps.setFloat(2, entrada.getValor());
			ps.setString(3, entrada.getDescricao());
			ps.setInt(4, entrada.getId());

			System.out.println(ps);


			int rowsUpdated = ps.executeUpdate();

			if (rowsUpdated > 0) {
			    System.out.println("Atualizado com sucesso");
			    return true;
			}

		}catch(Exception ex){
			ex.printStackTrace();
		}

		return false;
	}

	public boolean deletar(Entrada entrada){
		String sql = "DELETE FROM tbl_entrada_hospital WHERE id_entrada_hospital = ?;";
		try{
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, entrada.getId());
			int rowsUpdated = ps.executeUpdate();
			if (rowsUpdated > 0) {
			    System.out.println("Deletado com sucesso");
			    return true;
			}

		}catch(Exception ex){
			ex.printStackTrace();
		}

		return false;
	}
}

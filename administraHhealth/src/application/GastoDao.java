package application;

import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

public class GastoDao {


	Connection conn = (Connection) DBUtils.getConnection();

	public void inserir(Gasto...gastos){

		for(Gasto g : gastos){


			String sql = "INSERT INTO tbl_saida_hospital set data= ?, valor = ?, descricao =?;";

			try{
				PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
				ps.setString(1, g.getData());
				ps.setFloat(2, g.getValor());
				ps.setString(3, g.getDescricao());


				int rowsInserted = ps.executeUpdate();
				if (rowsInserted > 0) {
				    System.out.println("Inserido com sucesso");
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}

		}
	}

	public ArrayList<Gasto> obterTodos(){

		ArrayList<Gasto> gastos =  new ArrayList<>();


		String SQL ="SELECT * FROM tbl_saida_hospital;";

		try{

			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(SQL);

			ResultSet rs = (ResultSet) ps.executeQuery();

			while(rs.next()){

				Gasto g = new Gasto();
				g.setId(rs.getInt("id_saida_hospital"));
				g.setData(rs.getString("data"));
				g.setDescricao(rs.getString("descricao"));
				g.setValor(rs.getFloat("valor"));


				gastos.add(g);
			}


		}catch(Exception ex){
			ex.printStackTrace();
		}

		return gastos;

	}

	public boolean atualizar(Gasto g){

		String sql = "UPDATE tbl_saida_hospital set data= ?, valor = ?, descricao =? WHERE id_saida_hospital = ?;";

		try{
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, g.getData());
			ps.setFloat(2, g.getValor());
			ps.setString(3, g.getDescricao());
			ps.setInt(4, g.getId());

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

	public boolean deletar(Gasto g){

		String sql = "DELETE FROM tbl_saida_hospital WHERE id_saida_hospital = ?;";

		try{
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, g.getId());

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

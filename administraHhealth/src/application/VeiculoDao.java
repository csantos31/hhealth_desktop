package application;

import java.util.ArrayList;
import java.util.Arrays;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

public class VeiculoDao {

	Connection conn = (Connection) DBUtils.getConnection();

	public void inserir(Veiculo...veiculos){

		for(Veiculo v : veiculos){


			String sql = "INSERT INTO tbl_ambulancia set placa= ?, descricao = ?, id_unidade =?;";

			try{
				PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
				ps.setString(1, v.getPlaca());
				ps.setString(2, v.getDescricao());
				ps.setInt(3, v.getUnidade());


				int rowsInserted = ps.executeUpdate();
				if (rowsInserted > 0) {
				    System.out.println("Inserido com sucesso");
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}

		}
	}

	public ArrayList<Veiculo> obterTodos(){

		ArrayList<Veiculo> veiculos =  new ArrayList<>();


		String SQL ="SELECT * FROM tbl_ambulancia;";

		try{

			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(SQL);

			ResultSet rs = (ResultSet) ps.executeQuery();

			while(rs.next()){

				Veiculo v = new Veiculo();
				v.setId(rs.getInt("id_ambulancia"));
				v.setPlaca(rs.getString("placa"));
				v.setDescricao(rs.getString("descricao"));
				v.setUnidade(rs.getInt("id_unidade"));


				veiculos.add(v);
			}


		}catch(Exception ex){
			ex.printStackTrace();
		}

		return veiculos;

	}

	public boolean atualizar(Veiculo v){

		String sql = "UPDATE tbl_ambulancia set placa= ?, descricao = ?, id_unidade =? WHERE id_ambulancia=?;";

		try{
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, v.getPlaca());
			ps.setString(2, v.getDescricao());
			ps.setInt(3, v.getUnidade());
			ps.setInt(4, v.getId());

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

	public boolean deletar(Veiculo v){

		String sql = "DELETE FROM tbl_ambulancia WHERE id_ambulancia=?;";

		try{
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, v.getId());

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

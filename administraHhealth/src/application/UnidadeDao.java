package application;

import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

public class UnidadeDao {

	Connection conn = (Connection) DBUtils.getConnection();

	//Efetua o select no banco
		public ArrayList<Unidade> obterTodos(){

			ArrayList<Unidade> unidades =  new ArrayList<>();


			String SQL ="select * from tbl_unidade;";

			try{

				PreparedStatement ps = (PreparedStatement) conn.prepareStatement(SQL);

				ResultSet rs = (ResultSet) ps.executeQuery();

				while(rs.next()){

					Unidade u = new Unidade();
					u.setId_unidade(rs.getInt("id_unidade"));
					u.setTexto(rs.getString("texto"));
					u.setTelefone(rs.getString("telefone"));
					u.setNome_unidade(rs.getString("nome_unidade"));



					unidades.add(u);
				}


			}catch(Exception ex){
				ex.printStackTrace();
			}

			return unidades;

		}

		public Unidade listarUnidade(int id){

			Unidade u =  new Unidade();


			String SQL ="SELECT * from tbl_unidade WHERE id_unidade= ?;";

			try{

				PreparedStatement ps = (PreparedStatement) conn.prepareStatement(SQL);
				ps.setInt(1, id);

				ResultSet rs = (ResultSet) ps.executeQuery();

				while(rs.next()){

				u.setId_unidade(rs.getInt("id_unidade"));
				u.setTexto(rs.getString("texto"));
				u.setTelefone(rs.getString("telefone"));
				u.setNome_unidade(rs.getString("nome_unidade"));}

			}catch(Exception ex){
				ex.printStackTrace();
			}

			return u;

		}

}

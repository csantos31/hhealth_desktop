package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AcessoDao {
	public Acesso login(String login, String senha){

		Connection conn = DBUtils.getConnection();
		Acesso acesso = null;

		try{
			String sql = "SELECT * FROM tbl_usuario_funcionario WHERE usuario =? AND senha=?";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setObject(1, login);
	        ps.setObject(2, senha);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            acesso = new Acesso();
	            acesso.setEmail(rs.getString("usuario"));
	            /// ....
	        }
		    return acesso;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return acesso;
	}

}

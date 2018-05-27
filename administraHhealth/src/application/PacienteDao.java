package application;

import java.time.LocalDate;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

public class PacienteDao {

	Connection conn = (Connection) DBUtils.getConnection();

	public ArrayList<Paciente> obterComConsultas(){

		ArrayList<Paciente> pacientes =  new ArrayList<>();


		String SQL ="SELECT CONCAT(cons.id_agendamento_consulta, pac.id_paciente) AS codigo, "
				+ "concat_ws(?, pac.nome, pac.sobrenome) AS nome, cons.data, cons.hora "
						+ "FROM tbl_agendamento_consultas AS cons INNER JOIN tbl_paciente AS pac "
						+ "ON cons.id_paciente = pac.id_paciente WHERE data = ?;";

		try{

			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(SQL);
			ps.setString(1, " ");
			ps.setString(2, LocalDate.now().toString());

			ResultSet rs = (ResultSet) ps.executeQuery();

			while(rs.next()){

				Paciente p = new Paciente();
				p.setCodigo(rs.getString("codigo"));
				p.setNome(rs.getString("nome"));
				LocalDate date = rs.getDate("data").toLocalDate();
				p.setData(date);
				p.setHora(rs.getTime("hora"));
				pacientes.add(p);
			}


		}catch(Exception ex){
			ex.printStackTrace();
		}

		return pacientes;

	}
}

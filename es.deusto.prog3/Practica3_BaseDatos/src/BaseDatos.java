import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDatos {
	public static void main(String[] args) {
		try {
			Class.forName( "org.sqlite.JDBC" );
			Connection conn = DriverManager.getConnection( "jdbc:sqlite:res/NOMEKOP_Data_Base.db" );
			Statement statement = conn.createStatement();
			
//			statement.executeUpdate( "drop table if exists persona" );  // SOLO PORQUE ES PRUEBA!!!!!
			statement.executeUpdate( "create table Usuario (id integer, nombre string)" );
			statement.executeUpdate( "create table Personaje (id integer, nombre string)" );
			
			// Create - insert
			statement.executeUpdate( "insert into persona values (1, 'Andoni')" );
			int ret = statement.executeUpdate( "insert into persona (id, nombre) values (2, 'Olatz')" );
			statement.executeUpdate( "insert into persona values (3, 'Emilio')" );
			System.out.println( "Devuelve " + ret );
			statement.executeUpdate( "insert into persona values (3, 'Iker')" );
			// Update - update
			statement.executeUpdate( "update persona set nombre = 'Aitziber' where id = 2" );
			
			// Delete - delete
			statement.executeUpdate( "delete from persona where id = 3" );
			
			ResultSet rs = statement.executeQuery( "select * from persona order by nombre" );
			while (rs.next()) {
				int ident = rs.getInt( "id" );
				String nom = rs.getString( "nombre" );
				System.out.println( ident + " -> " + nom );
			}
			rs.close();
			
			statement.close();
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}

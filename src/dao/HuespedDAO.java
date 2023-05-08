package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Huesped;
import modelo.Reservas;

public class HuespedDAO {
	final private Connection con;

	public HuespedDAO(Connection con) {
		this.con = con;
	}
	
	public void guardarHuesped(Huesped hursped)
	{
		String insertStatementSQL = "INSERT INTO huesped "
				+ "(Reservas_ID, "
				+ "Nombre, "
				+ "Apellido, "
				+ "FechaNacimiento, "
				+ "Nacionalidad, "
				+ "Telefono) "
				+ "VALUES (?, ?, ?, ?, ?, ?);"; 
	    try(con){
	    	final PreparedStatement preparedStatement = con.prepareStatement(insertStatementSQL, Statement.RETURN_GENERATED_KEYS); 
	    	
                try (preparedStatement)
                {  
	                try {	
	                        ejecutaRegistro(hursped, preparedStatement);
	                }catch (Exception e){
	                        e.printStackTrace();
	                        }
                }
	    }catch(SQLException ex)
	    {
	    	throw new RuntimeException(ex);
	    }
	}
	
	public void EditarHuesped(Huesped huesped)
	{
		String updateStatementSQL = "UPDATE huesped SET Nombre = ?, "
				+ "Apellido = ?, "
				+ "FechaNacimiento = ?, "
				+ "Nacionalidad = ?, "
				+ "Telefono = ? WHERE Id = ? ;"; 
	    try(con){
	    	final PreparedStatement preparedStatement = con.prepareStatement(updateStatementSQL); 
	    	
                try (preparedStatement)
                {  
	                try {	
	                	preparedStatement.setString(1, huesped.getNombre());
	                	preparedStatement.setString(2, huesped.getApellido());
	                	preparedStatement.setDate(3, huesped.getFechaNacimiento());
	                	preparedStatement.setString(4, huesped.getNacionalidad());
	                	preparedStatement.setString(5, huesped.getTelefono());
	                	preparedStatement.setInt(6, huesped.getId());
	            			
	            	        
	                	preparedStatement.execute();
	                }catch (Exception e){
	                        e.printStackTrace();
	                        }
                }
	    }catch(SQLException ex)
	    {
	    	throw new RuntimeException(ex);
	    }
	}
	
	public void DesabilitarHuesped(Huesped huesped)
	{
		String updateStatementSQL = "UPDATE huesped SET eliminado = 1 WHERE (Id = ?);"; 
	    try(con){
	    	final PreparedStatement preparedStatement = con.prepareStatement(updateStatementSQL); 
	    	
                try (preparedStatement)
                {  
	                try {	
	                	preparedStatement.setInt(1, huesped.getId());
	            			
	                	preparedStatement.execute();
	                }catch (Exception e){
	                        e.printStackTrace();
	                        }
                }
	    }catch(SQLException ex)
	    {
	    	throw new RuntimeException(ex);
	    }
	}
	
	private void ejecutaRegistro(Huesped huesped, PreparedStatement prepStatement)
        throws SQLException {
		
		prepStatement.setInt(1, huesped.getReservas().getReservas_ID());
	    prepStatement.setString(2, huesped.getNombre());
	    prepStatement.setString(3, huesped.getApellido());
	    prepStatement.setDate(4, huesped.getFechaNacimiento());
	    prepStatement.setString(5, huesped.getNacionalidad());
	    prepStatement.setString(6, huesped.getTelefono());
        
        prepStatement.execute();

        final ResultSet rs = prepStatement.getGeneratedKeys();
        try(rs)
        {
            while(rs.next())
            {
            	huesped.setId(rs.getInt(1));
            }
        }
	}

	
	public List<Huesped> getHuespedesList() {
		List<Huesped> resultado = new ArrayList<>();
		String selectStatementSQL = "SELECT hu.Id, hu.Nombre, hu.Apellido, hu.FechaNacimiento, hu.Nacionalidad, hu.Telefono, re.LocalizaReserva "
									+ "FROM reservas re, huesped hu WHERE re.Reservas_ID = hu.Reservas_ID AND hu.eliminado = 0"; 
		try(con){
	    	final PreparedStatement preparedStatement = con.prepareStatement(selectStatementSQL); 
	    	
                try (preparedStatement)
                {  
                	final ResultSet rs = preparedStatement.executeQuery();

                    try(rs)
                    {
                    	while (rs.next())
    					{
                    		var huesped = new Huesped(	rs.getInt("ID"), 
														rs.getString("Nombre"), 
														rs.getString("Apellido"), 
														rs.getDate("FechaNacimiento"),
														rs.getString("Nacionalidad"),
														rs.getString("Telefono"),
														rs.getString("LocalizaReserva"));
    						resultado.add(huesped);
    					}
	                }catch (Exception e){
	                        e.printStackTrace();
	                        }
                }
	    }catch(SQLException ex)
	    {
	    	throw new RuntimeException(ex);
	    }
		return resultado;
	}
}

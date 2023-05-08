package dao;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Utils.LocalizadorDeReservas;
import modelo.Huesped;
import modelo.Reservas;

public class ReservasDAO {
	final private Connection con;

	public ReservasDAO(Connection con) {
		this.con = con;
	}
	
	public void guardarReserva(Reservas reserva)
	{
		String insertStatementSQL = "INSERT INTO reservas "
				+ "(FechaEntrada, "
				+ "FechaSalida, "
				+ "Valor, "
				+ "FormaPago, "
				+ "LocalizaReserva, "
				+ "tipo_ocupacionId) "
				+ "VALUES ( ?, ?, ?, ?, ?, ?);"; 
	    try(con){
	    	final PreparedStatement preparedStatement = con.prepareStatement(insertStatementSQL, Statement.RETURN_GENERATED_KEYS); 
	    	
                try (preparedStatement)
                {  
	                try {	
	                        ejecutaRegistro(reserva, preparedStatement);
	                }catch (Exception e){
	                        e.printStackTrace();
	                        }
                }
	    }catch(SQLException ex)
	    {
	    	throw new RuntimeException(ex);
	    }
	}
	
	private void ejecutaRegistro(Reservas reserva, PreparedStatement prepStatement)
        throws SQLException {

		prepStatement.setDate(1, reserva.getFechaEntrada());
        prepStatement.setDate(2, reserva.getFechaSalida());
        prepStatement.setBigDecimal(3, reserva.getValor());
        prepStatement.setString(4, reserva.getFormaPago());
        prepStatement.setString(5, reserva.getLocalizaReserva());
        prepStatement.setInt(6, reserva.getTipo_ocupacionId());

        prepStatement.execute();

        final ResultSet rs = prepStatement.getGeneratedKeys();
        try(rs)
        {
            while(rs.next())
            {
            	reserva.setReservas_ID(rs.getInt(1));
            }
        }
	}
	
	public void DesabilitarReserva(Reservas reserva)
	{
		String updateStatementSQL = "UPDATE reservas SET eliminado = 1 WHERE Reservas_ID = ? ;"; 
	    try(con){
	    	final PreparedStatement preparedStatement = con.prepareStatement(updateStatementSQL); 
	    	
                try (preparedStatement)
                {  
	                try {	
	                	preparedStatement.setInt(1, reserva.getReservas_ID());
	            			
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
	
	public void UpdateReserva(Reservas reserva)
	{
		 String updateStatementSQL = "UPDATE reservas SET "
				+ "FechaEntrada = ?, "
				+ "FechaSalida = ?, "
				+ "Valor = ?, "
				+ "FormaPago = ? "
				+ " WHERE Reservas_ID = ? ;"; 
	    try(con){
	    	final PreparedStatement preparedStatement = con.prepareStatement(updateStatementSQL); 
	    	
                try (preparedStatement)
                {  
	                try {	
	                	preparedStatement.setDate(1, reserva.getFechaEntrada());
	                	preparedStatement.setDate(2, reserva.getFechaSalida());
	                	preparedStatement.setBigDecimal(3, reserva.getValor());
	                	preparedStatement.setString(4, reserva.getFormaPago());
	                	preparedStatement.setInt(5, reserva.getReservas_ID());
	                	
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
	
	public String GenerarLocalizadorDeReservas()
    {
		while (true)
		{
			LocalizadorDeReservas localizaReserva = new LocalizadorDeReservas(5, new SecureRandom(), 
					LocalizadorDeReservas.upper + 
					LocalizadorDeReservas.upper);
			String CodigoReserva = localizaReserva.nextString();
			if (VerificaLocalizadorDeReservas(CodigoReserva) == false)
			{
				return CodigoReserva;
			}
		}  
    }
	
	public boolean VerificaLocalizadorDeReservas(String localizaReserva)
	{
		boolean Existe = false;
		String selectStatementSQL = "SELECT Reservas_ID FROM reservas WHERE LocalizaReserva = ? "; 
	    try(con){
	    	final PreparedStatement preparedStatement = con.prepareStatement(selectStatementSQL); 
	    	
                try (preparedStatement)
                {  
                	preparedStatement.setString(1, localizaReserva);

                	final ResultSet rs = preparedStatement.executeQuery();

                    try(rs)
                    {
                    	Existe = rs.next();    
	                }catch (Exception e){
	                        e.printStackTrace();
	                        }
                }
	    }catch(SQLException ex)
	    {
	    	throw new RuntimeException(ex);
	    }
		return Existe;
	}
	
	public int getReservasIdFromLocalizador(String localizaReserva)
	{
		int Reservas_ID = 0;
		String selectStatementSQL = "SELECT Reservas_ID FROM reservas WHERE LocalizaReserva = ? "; 

			try(con){
	    	final PreparedStatement preparedStatement = con.prepareStatement(selectStatementSQL); 
	    	
                try (preparedStatement)
                {  
                	preparedStatement.setString(1, localizaReserva);
                	final ResultSet rs = preparedStatement.executeQuery();

                    try(rs)
                    {
                    	if (rs.next())
                    	{
                    		Reservas_ID = rs.getInt("Reservas_ID");
                    	}
	                }catch (Exception e){
	                        e.printStackTrace();
	                        }
                }
	    }catch(SQLException ex)
	    {
	    	throw new RuntimeException(ex);
	    }
		return Reservas_ID;
	}
	
	
	public List<Reservas> getReservasList()
	{
		List<Reservas> resultado = new ArrayList<>();
		String selectStatementSQL = "SELECT * FROM reservas WHERE eliminado = 0"; 
		try(con){
	    	final PreparedStatement preparedStatement = con.prepareStatement(selectStatementSQL); 
	    	
                try (preparedStatement)
                {  
                	final ResultSet rs = preparedStatement.executeQuery();

                    try(rs)
                    {
                    	while (rs.next())
    					{
                    		var reservas = new Reservas(rs.getInt("reservas_ID"), 
    													rs.getDate("FechaEntrada"), 
    													rs.getDate("FechaSalida"), 
    													rs.getBigDecimal("Valor"),
    													rs.getString("FormaPago"),
    													rs.getString("LocalizaReserva"),
    													rs.getInt("tipo_ocupacionId"));
    						resultado.add(reservas);
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
	
	public Reservas getReserva(String LocalizaReserva)
	{
		Reservas reserva = new Reservas();
		String selectStatementSQL = "SELECT * FROM reservas WHERE eliminado = 0 AND LocalizaReserva = ?"; 
		try(con){
	    	final PreparedStatement preparedStatement = con.prepareStatement(selectStatementSQL); 
	    	
	    		preparedStatement.setString(1, LocalizaReserva);
                try (preparedStatement)
                {  
                	final ResultSet rs = preparedStatement.executeQuery();
                	
                    try(rs)
                    {
                    	while (rs.next())
    					{
                    		reserva = new Reservas(rs.getInt("reservas_ID"), 
    													rs.getDate("FechaEntrada"), 
    													rs.getDate("FechaSalida"), 
    													rs.getBigDecimal("Valor"),
    													rs.getString("FormaPago"),
    													rs.getString("LocalizaReserva"),
    													rs.getInt("tipo_ocupacionId"));
    						
    					}
	                }catch (Exception e){
	                        e.printStackTrace();
	                        }
                }
	    }catch(SQLException ex)
	    {
	    	throw new RuntimeException(ex);
	    }
		return reserva;
	}
}

package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.TipoOcupacion;

public class TipoOcupacionDAO {
	
	final private Connection con;

	public TipoOcupacionDAO(Connection con) {
		this.con = con;
	}
	
	public List<TipoOcupacion> getTipoOcupacionList()
	{
		
		List<TipoOcupacion> resultado = new ArrayList<>();
		try {
			final PreparedStatement prepSatement = con.prepareStatement("SELECT tipo_ocupacionId, Ocupacion FROM tipo_ocupacion");
			try(prepSatement)
			{
				final ResultSet rs = prepSatement.executeQuery();
				try(rs)
				{
					while (rs.next())
					{
						TipoOcupacion tipoOcupacion = new TipoOcupacion(rs.getInt("tipo_ocupacionId"), rs.getString("Ocupacion"));
						resultado.add(tipoOcupacion);
					}
				}
			}
		}catch (SQLException ex)
		{
			throw new RuntimeException(ex);
		}
		return resultado;
	}
	
	public BigDecimal getTipoOcupacionPrecio(int tipo_oculacionId)
	{
		BigDecimal Precio = new BigDecimal(0.0);
		try {
			final PreparedStatement prepSatement = con.prepareStatement("SELECT Precio FROM tipo_ocupacion WHERE tipo_ocupacionId = ? ");
			try(prepSatement)
			{
				prepSatement.setInt(1, tipo_oculacionId);
				final ResultSet rs = prepSatement.executeQuery();
				try(rs)
				{
					if(rs.next())
					{
						Precio = rs.getBigDecimal("Precio");
					}
					
				}
			}
		}catch (SQLException ex)
		{
			throw new RuntimeException(ex);
		}
		return Precio;
	}
		
	public String getTipoOcupacion(int tipo_oculacionId)
	{
		String vReturn = "";
		try {
			final PreparedStatement prepSatement = con.prepareStatement("SELECT Ocupacion FROM tipo_ocupacion WHERE tipo_ocupacionId = ? ");
			try(prepSatement)
			{
				prepSatement.setInt(1, tipo_oculacionId);
				final ResultSet rs = prepSatement.executeQuery();
				try(rs)
				{
					if(rs.next())
					{
						vReturn = rs.getString("Ocupacion");
					}
					
				}
			}
		}catch (SQLException ex)
		{
			throw new RuntimeException(ex);
		}
		return vReturn;
	}
	
}

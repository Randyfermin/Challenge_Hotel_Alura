package factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	private DataSource datasource;
	
	public ConnectionFactory() {
		ComboPooledDataSource pooledDataSource = new ComboPooledDataSource();
		pooledDataSource.setJdbcUrl("jdbc:mysql://localhost/Hotel_Alura?useTimeZone=true&serverTimeZone=UTC");
		pooledDataSource.setUser("root");
		pooledDataSource.setPassword("Rfh031@027");
		
		pooledDataSource.setMaxPoolSize(30);
		
		this.datasource = pooledDataSource;
	}
	
	public Connection recuperaConexion() {
		try {
			return this.datasource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public String getDataBaseName(String DBName) throws SQLException {
		
        final Connection con = recuperaConexion();
        try(con)
        {
        	
        	final PreparedStatement  preperedst = con.prepareStatement("SHOW DATABASES LIKE ? ");
            try(preperedst)
            {
            		preperedst.setString(1, DBName);
            	
                    final ResultSet rs = preperedst.executeQuery();
                    
                    try(rs){
                            
                            return rs.getString(0);
                    }
            }

        }
	}
}

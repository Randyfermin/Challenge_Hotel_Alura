package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Usuario;

public class UsuarioDAO {
	final private Connection con;

	public UsuarioDAO(Connection con) {
		this.con = con;
	}
	
	public void guardarUsuario(Usuario usuario)
	{
		String insertStatementSQL = "INSERT INTO USUARIOS "
                                        + "(NOMBRE_COMPLETO, UserName, UserPwd) "
                                        + "VALUES(?, ?, ?)"; 
	    try(con){
	    	final PreparedStatement preparedStatement = con.prepareStatement(insertStatementSQL, Statement.RETURN_GENERATED_KEYS); 
	    	
                try (preparedStatement)
                {  
	                try {	
	                        ejecutaRegistro(usuario, preparedStatement);
	                }catch (Exception e){
	                        e.printStackTrace();
	                        }
                }
	    }catch(SQLException ex)
	    {
	    	throw new RuntimeException(ex);
	    }
	}
	
	private void ejecutaRegistro(Usuario usuario, PreparedStatement prepStatement)
        throws SQLException {

		prepStatement.setString(1, usuario.getNOMBRE_COMPLETO());
        prepStatement.setString(2, usuario.getUserName());
        prepStatement.setString(3, usuario.getUserPwd());

        prepStatement.execute();

        final ResultSet rs = prepStatement.getGeneratedKeys();
        try(rs)
        {
            while(rs.next())
            {
                    usuario.setId(rs.getInt(1));
                    System.out.println(String.format("Fue insertado el Usuario ID %s", usuario));
            }
        }
	}
	
	public boolean grandAccess(Usuario usuario) {
		try
		{
            final PreparedStatement  st = con.prepareStatement ("SELECT NOMBRE_COMPLETO "
            													+ " FROM hotel_alura.usuarios WHERE UserName = ? "
            													+ " AND UserPwd = md5(?);");
            try(st)
            {
            	st.setString(1, usuario.getUserName());
            	st.setString(2, usuario.getUserPwd());
                st.execute();

                final ResultSet rs = st.getResultSet();
                try(rs){
                    	return rs.next();
                    
                }
            }
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}

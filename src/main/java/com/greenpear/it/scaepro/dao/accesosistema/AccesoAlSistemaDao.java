package com.greenpear.it.scaepro.dao.accesosistema;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.greenpear.it.scaepro.model.accesosistema.AccesoAlSistemaModel;
import com.greenpear.it.scaepro.services.DataSourceService;
import com.greenpear.it.scaepro.services.LoginService;

@Repository("loginDaoService")
public class AccesoAlSistemaDao extends DataSourceService implements LoginService{
	
	private static final Logger log = LoggerFactory.getLogger("usuarioDaoService");

	@Override
	public String consultarUsuario(AccesoAlSistemaModel usuario) throws SQLException {
		String sql="SELECT nombreUsuario,passwordUsuario "
				+ "FROM c_usuario "
				+ "WHERE nombreUsuario='"+usuario.getUsuario()+"' AND "
				+ "passwordUsuario='"+usuario.getPassword()+"'";
		
		try{
			usuario=getJdbcTemplate().query(sql, new ResultSetExtractor<AccesoAlSistemaModel>(){
				public AccesoAlSistemaModel extractData(ResultSet rs) throws SQLException{
					if(rs.next()){
						AccesoAlSistemaModel resultValue=new AccesoAlSistemaModel();
						resultValue.setUsuario(rs.getString("nombreUsuario"));
						resultValue.setPassword(rs.getString("passwordUsuario"));
						return resultValue;
					}else{
						return null;
					}
				}
			});
			
			if (usuario==null){
				return "Nombre de usuario o contraseña incorrectos!";
			}else{
				return "Acceso concedido!";
			}
			
		}catch(Exception e){
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ",e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n"
					+ "No se pudo realizar la consulta!");
		}
	}
}
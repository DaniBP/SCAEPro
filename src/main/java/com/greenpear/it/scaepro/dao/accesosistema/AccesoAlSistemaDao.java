package com.greenpear.it.scaepro.dao.accesosistema;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.greenpear.it.scaepro.model.accesosistema.AccesoAlSistemaModel;
import com.greenpear.it.scaepro.services.DataSourceService;
import com.greenpear.it.scaepro.services.LoginService;
import com.greenpear.it.scaepro.services.SelectAllService;

@Repository("loginDaoService")
public class AccesoAlSistemaDao extends DataSourceService implements LoginService {
	
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

	public String consultaUsuarios() throws SQLException {
		List<AccesoAlSistemaModel> usuarios = new ArrayList<AccesoAlSistemaModel>();
		String sql = "SELECT * FROM c_usuario";
		try{
			usuarios = getJdbcTemplate().query(sql, new RowMapper<AccesoAlSistemaModel>(){
				public AccesoAlSistemaModel mapRow(ResultSet rs, int columna)throws SQLException {
					AccesoAlSistemaModel resultValue = new AccesoAlSistemaModel();
					resultValue.setUsuario(rs.getString("nombreUsuario"));
					return resultValue;
				}
			});
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la consulta!");
		}
		if(usuarios.size()==0){
			return "¡No existe ningún usuario registrado actualmente,\n"
					+ "favor de registrar un usuario nuevo!";
		}else{
			return "Existen usuarios";
		}
	}
}
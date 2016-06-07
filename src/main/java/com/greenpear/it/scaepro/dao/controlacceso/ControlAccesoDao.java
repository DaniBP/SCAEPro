package com.greenpear.it.scaepro.dao.controlacceso;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.greenpear.it.scaepro.model.empleado.EmpleadoModel;
import com.greenpear.it.scaepro.services.DataSourceService;
import com.greenpear.it.scaepro.services.SelectOneService;

/**
 * 
 * @author DanielBP
 *
 */
@Repository("accesoDaoService")
public class ControlAccesoDao extends DataSourceService implements SelectOneService<EmpleadoModel>{
	
	private static final Logger log = LoggerFactory.getLogger("usuarioDaoService");

	@Override
	public EmpleadoModel consultaIndividual(int id) throws SQLException {
		
		EmpleadoModel empleado=new EmpleadoModel();
		String sql="SELECT * FROM t_empleado WHERE nipEmpleado='"+id+"'";
		
		try{
			empleado=getJdbcTemplate().query(sql, new ResultSetExtractor<EmpleadoModel>(){
				public EmpleadoModel extractData(ResultSet rs) throws SQLException{
					EmpleadoModel resultValue=new EmpleadoModel();
					if(rs.next()){
						resultValue.setIdEmpleado(rs.getInt("idEmpleado"));
					}
					return resultValue;
				}
			});
		}catch(Exception e){
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ",e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n"
					+ "No se pudo realizar la consulta!");
		}
		return empleado;
	}

}

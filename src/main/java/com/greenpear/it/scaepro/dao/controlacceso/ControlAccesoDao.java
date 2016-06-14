package com.greenpear.it.scaepro.dao.controlacceso;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.greenpear.it.scaepro.model.controlacceso.ControlAccesoModel;
import com.greenpear.it.scaepro.model.empleado.EmpleadoModel;
import com.greenpear.it.scaepro.model.turno.TurnoModel;
import com.greenpear.it.scaepro.services.DataSourceService;
import com.greenpear.it.scaepro.services.InsertService;
import com.greenpear.it.scaepro.services.SelectOneService;

/**
 * 
 * @author DanielBP
 *
 */
@Repository("accesoDaoService")
public class ControlAccesoDao extends DataSourceService implements SelectOneService<EmpleadoModel>, InsertService<ControlAccesoModel>{
	
	private static final Logger log = LoggerFactory.getLogger("usuarioDaoService");

	@Override
	public EmpleadoModel consultaIndividual(int id) throws SQLException {
		
		EmpleadoModel empleado;
		String sql="SELECT idEmpleado,nombreEmpleado,apePatEmpleado,apeMatEmpleado,nombreArea,fotografia "
				+ "FROM t_empleado "
				+ "INNER JOIN c_turno ON c_turno.idTurno=t_empleado.idTurno "
				+ "INNER JOIN c_area ON c_area.idArea=c_turno.idArea "
				+ "WHERE nipEmpleado='"+id+"'";
		
		try{
			empleado=getJdbcTemplate().query(sql, new ResultSetExtractor<EmpleadoModel>(){
				public EmpleadoModel extractData(ResultSet rs) throws SQLException{
					EmpleadoModel resultValue=new EmpleadoModel();
					if(rs.next()){
						resultValue.setIdEmpleado(rs.getInt("idEmpleado"));
						resultValue.setNombreEmpleado(rs.getString("nombreEmpleado"));
						resultValue.setApePatEmpleado(rs.getString("apePatEmpleado"));
						resultValue.setApeMatEmpleado(rs.getString("apeMatEmpleado"));
						resultValue.setArea(rs.getString("nombreArea"));
						resultValue.setFotografia(rs.getString("fotografia"));
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
	
	public ControlAccesoModel consultarControlAcceso(int idEmpleado,String fecha) throws SQLException{
		ControlAccesoModel controlAcceso;
		String sql="SELECT idControlAcceso "
				+ "FROM t_control_acceso "
				+ "WHERE idEmpleado="+idEmpleado+" "
				+ "AND fecha='"+fecha+"'";
		
		try{
			controlAcceso=getJdbcTemplate().query(sql, new ResultSetExtractor<ControlAccesoModel>(){
				public ControlAccesoModel extractData(ResultSet rs) throws SQLException{
					ControlAccesoModel resultValue=new ControlAccesoModel();
					if(rs.next()){
						resultValue.setIdControlAcceso(rs.getInt("idControlAcceso"));
					}
					return resultValue;
				}
			});
		}catch(Exception e){
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ",e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n"
					+ "No se pudo realizar la consulta!");
		}
		return controlAcceso;
	}

	@Override
	public String insertar(ControlAccesoModel t) throws SQLException {
		try {
			
			if(t.getIdControlAcceso()==0){
				SimpleJdbcInsert insertControlAcceso = new SimpleJdbcInsert(getDataSource());
				insertControlAcceso.setTableName("t_control_acceso");
				insertControlAcceso.setGeneratedKeyName("idControlAcceso");
				
				Map<String, Object> paramteters1 = new HashMap<String, Object>();
				paramteters1.put("idEmpleado", t.getIdEmpleado());
				paramteters1.put("fecha", t.getFecha());
				
				t.setIdControlAcceso(insertControlAcceso.executeAndReturnKey(paramteters1).intValue());
			}
			
			SimpleJdbcInsert insertControlHoras = new SimpleJdbcInsert(getDataSource());
			insertControlHoras.setTableName("t_control_horas");
			insertControlHoras.setGeneratedKeyName("idControlHoras");
			
			Map<String, Object> paramteters2 = new HashMap<String, Object>();
			paramteters2.put("idControlAcceso", t.getIdControlAcceso());
			paramteters2.put("horaControl", t.getHoraControl());
			paramteters2.put("horaRegistrada", t.getHoraRegistrada());
			
			t.setIdControlHoras(insertControlHoras.executeAndReturnKey(paramteters2).intValue());

		} catch (Exception e) {
			log.error("Consulta : Error al cargar los datos. Motivo: {} ",e.getMessage());
			throw new SQLException("Error al tratar de generar el INSERT");
		}
		return null;
	}
	
	public TurnoModel getHorario(ControlAccesoModel acceso, String dia) throws SQLException{
		TurnoModel turnoModel;
		String sql = "SELECT * FROM t_horario_turno "
				+ "INNER JOIN c_turno ON c_turno.idTurno=t_horario_turno.idTurno "
				+ "INNER JOIN t_empleado ON t_empleado.idTurno=t_horario_turno.idTurno "
				+ "WHERE idEmpleado = "+acceso.getIdEmpleado()+" AND "
				+ "dia='"+dia+"'";
		try{
			turnoModel=getJdbcTemplate().query(sql, new ResultSetExtractor<TurnoModel>(){
				public TurnoModel extractData(ResultSet rs) throws SQLException{
					TurnoModel resultValue=new TurnoModel();
					if(rs.next()){
						resultValue.setTiempoRetardo(rs.getInt("tiempoRetardo"));
						resultValue.setTiempoFalta(rs.getInt("tiempoFalta"));
						resultValue.setHoraEntrada(rs.getString("horaEntrada"));
						resultValue.setHoraSalidaComer(rs.getString("horaSalidaComer"));
						resultValue.setHoraEntradaComer(rs.getString("horaEntradaComer"));
						resultValue.setHoraSalida(rs.getString("horaSalida"));
						resultValue.setIdEstatus(rs.getInt("idEstatus"));
						resultValue.setIdEstatusComida(rs.getInt("idestatusComida"));
					}
					return resultValue;
				}
			});
		}catch(Exception e){
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ",e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n"
					+ "No se pudo realizar la consulta!");
		}
		return turnoModel;
	}
}

package com.greenpear.it.scaepro.dao.controlacceso;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.greenpear.it.scaepro.model.controlacceso.ControlAccesoModel;
import com.greenpear.it.scaepro.model.empleado.EmpleadoModel;
import com.greenpear.it.scaepro.model.incidencia.IncidenciaModel;
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
	
	private static final Logger log = LoggerFactory.getLogger("accesoDaoService");
	
	public List<EmpleadoModel> consultarHuella() throws SQLException{
		List<EmpleadoModel> empleados = new ArrayList<EmpleadoModel>();
		String sql = "SELECT huellaEmpleado,idEmpleado FROM t_empleado";
		
		try{
			empleados=getJdbcTemplate().query(sql, new RowMapper<EmpleadoModel>(){
				public EmpleadoModel mapRow(ResultSet rs, int columna) throws SQLException{
					EmpleadoModel resultValue=new EmpleadoModel();
					
					resultValue.setHuellaEmpleado(rs.getBytes("huellaEmpleado"));
					resultValue.setIdEmpleado(rs.getInt("idEmpleado"));
					
					return resultValue;
				}
			});
		}catch(Exception e){
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ",e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n"
					+ "No se pudo realizar la consulta!");
		}
		
		return empleados;
	}

	@Override
	public EmpleadoModel consultaIndividual(int id) throws SQLException {
		
		EmpleadoModel empleado;
		String sql="SELECT idEmpleado,nombreEmpleado,apePatEmpleado,apeMatEmpleado,nombreArea,fotografia "
				+ "FROM t_empleado "
				+ "INNER JOIN c_turno ON c_turno.idTurno=t_empleado.idTurno "
				+ "INNER JOIN c_area ON c_area.idArea=c_turno.idArea "
				+ "WHERE idEmpleado='"+id+"'";
		
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
	
	public List<ControlAccesoModel> consultarControlAcceso(int idEmpleado,String fecha) throws SQLException{
		List<ControlAccesoModel> listaControlAcceso= new ArrayList<ControlAccesoModel>();
		
		String sql="SELECT * FROM t_control_horas "
				+ "INNER JOIN t_control_acceso ON t_control_acceso.idControlAcceso = t_control_horas.idControlAcceso "
				+ "WHERE idEmpleado="+idEmpleado+" AND "
						+ "fecha='"+fecha+"'";
		
		try{
			listaControlAcceso=getJdbcTemplate().query(sql, new RowMapper<ControlAccesoModel>(){
				public ControlAccesoModel mapRow(ResultSet rs, int columna) throws SQLException{
					ControlAccesoModel resultValue=new ControlAccesoModel();
					
					resultValue.setIdControlHoras(rs.getInt("idControlHoras"));
					resultValue.setIdControlAcceso(rs.getInt("idControlAcceso"));
					resultValue.setHoraControl(rs.getString("horaControl"));
					resultValue.setHoraRegistrada(rs.getString("horaRegistrada"));
					resultValue.setIdEmpleado(rs.getInt("idEmpleado"));
					resultValue.setFecha(rs.getString("fecha"));
					resultValue.setHorasTrabajadas(rs.getInt("horasTrabajadas"));
					
					return resultValue;
				}
			});
		}catch(Exception e){
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ",e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n"
					+ "No se pudo realizar la consulta!");
		}
		return listaControlAcceso;
	}

	@Override
	public String insertar(ControlAccesoModel t) throws SQLException {
		try {
			
			if(t.getHoraControl().equals("Hora de entrada")){
				SimpleJdbcInsert insertControlAcceso = new SimpleJdbcInsert(getDataSource());
				insertControlAcceso.setTableName("t_control_acceso");
				insertControlAcceso.setGeneratedKeyName("idControlAcceso");
				
				Map<String, Object> paramteters = new HashMap<String, Object>();
				paramteters.put("idEmpleado", t.getIdEmpleado());
				paramteters.put("fecha", t.getFecha());
				
				t.setIdControlAcceso(insertControlAcceso.executeAndReturnKey(paramteters).intValue());
			}else if(t.getHoraControl().equals("Hora de salida")){
				String sql="UPDATE t_control_acceso SET "
						+ "horasTrabajadas=? "
						+ "WHERE idControlAcceso=?";
				
				try{
					getJdbcTemplate().update(sql,
							t.getHorasTrabajadas(),
							t.getIdControlAcceso());
				}catch(Exception e){
					log.error("\nSQL: Error al cargar los datos.\nMotivo: {} ",e.getMessage());
					throw new SQLException("Existe un problema con la base de datos\n"
							+ "No se pudo realizar la actualización!");
				}
			}
			
			SimpleJdbcInsert insertControlHoras = new SimpleJdbcInsert(getDataSource());
			insertControlHoras.setTableName("t_control_horas");
			insertControlHoras.setGeneratedKeyName("idControlHoras");
			
			Map<String, Object> paramteters = new HashMap<String, Object>();
			paramteters.put("idControlAcceso", t.getIdControlAcceso());
			paramteters.put("horaControl", t.getHoraControl());
			paramteters.put("horaRegistrada", t.getHoraRegistrada());
			
			t.setIdControlHoras(insertControlHoras.executeAndReturnKey(paramteters).intValue());

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
	
	public void generarIncidencia(IncidenciaModel incidenciaModel) throws SQLException{
		try{
			SimpleJdbcInsert insertIncidencia = new SimpleJdbcInsert(getDataSource());
			insertIncidencia.setTableName("t_incidencia");
			insertIncidencia.setGeneratedKeyName("idIncidencia");
			
			Map<String, Object> paramteters = new HashMap<String, Object>();
			paramteters.put("idEmpleado", incidenciaModel.getIdEmpleado());
			paramteters.put("idTipoIncidencia", incidenciaModel.getIdTipoIncidencia());
			paramteters.put("idEstatusIncidencia", incidenciaModel.getIdEstatusIncidencia());
			paramteters.put("fechaIncidencia", incidenciaModel.getFechaIncidencia());
			
			incidenciaModel.setIdIncidencia(insertIncidencia.executeAndReturnKey(paramteters).intValue());
		} catch (Exception e) {
			log.error("Consulta : Error al cargar los datos. Motivo: {} ",e.getMessage());
			throw new SQLException("Error al tratar de generar el INSERT");
		}
	}
	
	public List<EmpleadoModel> consultarRegistroMovil() throws SQLException{
		List<EmpleadoModel> empleados = new ArrayList<EmpleadoModel>();
		
		String sql="SELECT idEmpleado FROM t_registroMovil";
		
		try{
			empleados=getJdbcTemplate().query(sql, new RowMapper<EmpleadoModel>(){
				public EmpleadoModel mapRow(ResultSet rs, int columna) throws SQLException{
					EmpleadoModel resultValue=new EmpleadoModel();
					
					resultValue.setIdEmpleado(rs.getInt("idEmpleado"));
					
					return resultValue;
				}
			});
		}catch(Exception e){
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ",e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n"
					+ "No se pudo realizar la consulta!");
		}
		
		return empleados;
	}
	
	public void truncarRegistroMovil() throws SQLException{
		String sql="TRUNCATE t_registroMovil ";
		
		try{
			getJdbcTemplate().update(sql);
		}catch(Exception e){
			log.error("\nSQL: Error al cargar los datos.\nMotivo: {} ",e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n"
					+ "No se pudo truncar el registro movil!");
		}
	}
}

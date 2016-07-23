package com.greenpear.it.scaepro.dao.configurarscaepro;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.greenpear.it.scaepro.model.empleado.EmpleadoModel;
import com.greenpear.it.scaepro.model.gestionareas.ConsultaAreasModel;
import com.greenpear.it.scaepro.model.turno.TurnoModel;
import com.greenpear.it.scaepro.services.DataSourceService;

/**
 * 
 * @author DanielBP
 *
 */
@Repository("configurarScaeProDao")
public class ConfigurarScaeProDao extends DataSourceService{
	
	private static final Logger log = LoggerFactory.getLogger("configurarScaeProDao");
	
	public List<ConsultaAreasModel> consultarAreas() throws SQLException  {
		List<ConsultaAreasModel> areas = new ArrayList<ConsultaAreasModel>();
		String sql = "SELECT * FROM c_area";
		
		try{
			areas=getJdbcTemplate().query(sql, new RowMapper<ConsultaAreasModel>(){
				public ConsultaAreasModel mapRow(ResultSet rs, int columna) throws SQLException{
					ConsultaAreasModel resultValue=new ConsultaAreasModel();
					
					resultValue.setIdArea(rs.getInt("idArea"));
					resultValue.setArea(rs.getString("nombreArea"));
					
					return resultValue;
				}
			});
			
			return areas;
		}catch(Exception e){
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ",e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n"
					+ "No se pudo realizar la consulta!");
		}
	}

	public List<TurnoModel> consultarTurnos(int idArea)throws SQLException{
		List<TurnoModel> turnos = new ArrayList<TurnoModel>();
		String sql= "SELECT * FROM c_turno INNER JOIN "
				+ "t_horario_turno ON t_horario_turno.idTurno = c_turno.idTurno "
				+ "WHERE idArea = " + idArea;
		
		
		try{
			turnos=getJdbcTemplate().query(sql, new RowMapper<TurnoModel>(){
				public TurnoModel mapRow(ResultSet rs, int columna) throws SQLException{
					TurnoModel resultValue=new TurnoModel();
					
					resultValue.setIdArea(rs.getInt("idArea"));
					resultValue.setIdTurno(rs.getInt("idTurno"));
					resultValue.setNombreTurno(rs.getString("nombreTurno"));
					resultValue.setTiempoRetardo(rs.getInt("tiempoRetardo"));
					resultValue.setTiempoFalta(rs.getInt("tiempoFalta"));
					resultValue.setIdHorarioTurno(rs.getInt("idHorarioTurno"));
					resultValue.setDia(rs.getString("dia"));
					resultValue.setHoraEntrada(rs.getString("horaEntrada"));
					resultValue.setHoraSalidaComer(rs.getString("horaSalidaComer"));
					resultValue.setHoraEntradaComer(rs.getString("horaEntradaComer"));
					resultValue.setHoraSalida(rs.getString("horaSalida"));
					resultValue.setIdEstatus(rs.getInt("idEstatus"));
					resultValue.setIdEstatusComida(rs.getInt("idEstatusComida"));
					
					return resultValue;
				}
			});
			
			return turnos;
		}catch(Exception e){
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ",e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n"
					+ "No se pudo realizar la consulta!");
		}		
	}
	
	public String registrarTurno(List<TurnoModel> turno) throws SQLException{
		try{
			SimpleJdbcInsert insertTurno = new SimpleJdbcInsert(getDataSource());
			insertTurno.setTableName("c_turno");
			insertTurno.setGeneratedKeyName("idturno");
			
			Map<String, Object> paramteters = new HashMap<String, Object>();
			paramteters.put("idArea", turno.get(0).getIdArea());
			paramteters.put("nombreTurno", turno.get(0).getNombreTurno());
			paramteters.put("tiempoRetardo", turno.get(0).getTiempoRetardo());
			paramteters.put("tiempoFalta", turno.get(0).getTiempoFalta());
			
			int idTurno=insertTurno.executeAndReturnKey(paramteters).intValue();
			
			for (int i = 0; i < 7; i++) {
				turno.get(i).getHoraEntrada();
				SimpleJdbcInsert insertHorarioTurno = new SimpleJdbcInsert(getDataSource());
				insertHorarioTurno.setTableName("t_horario_turno");
				insertHorarioTurno.setGeneratedKeyName("idHorarioTurno");
				
				Map<String, Object> paramteters2 = new HashMap<String, Object>();
				paramteters2.put("idTurno", idTurno);
				paramteters2.put("dia", turno.get(i).getDia());
				paramteters2.put("horaEntrada", turno.get(i).getHoraEntrada());
				paramteters2.put("horaSalida", turno.get(i).getHoraSalida());
				paramteters2.put("horaSalidaComer", turno.get(i).getHoraSalidaComer());
				paramteters2.put("horaEntradaComer", turno.get(i).getHoraEntradaComer());
				paramteters2.put("idEstatus", turno.get(i).getIdEstatus());
				paramteters2.put("idEstatusComida", turno.get(i).getIdEstatusComida());
				
				turno.get(i).setIdHorarioTurno(insertHorarioTurno.executeAndReturnKey(paramteters2).intValue());
			}
			return "correcto";
		}catch(Exception e){
			log.error("\nSQL: Error al cargar los datos.\nMotivo: {} ",e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n"
					+ "No se pudo realizar la Inserción!");
		}		
	}
	
	public String editarTurno(List<TurnoModel> turno) throws SQLException{
		String sql="UPDATE c_turno SET "
				+ "nombreTurno = ?, "
				+ "tiempoRetardo = ?, "
				+ "tiempoFalta = ? "
				+ "WHERE idTurno = ?";
		
		try{
			getJdbcTemplate().update(sql,
					turno.get(0).getNombreTurno(),
					turno.get(0).getTiempoRetardo(),
					turno.get(0).getTiempoFalta(),
					turno.get(0).getIdTurno());
			
			sql="UPDATE t_horario_turno SET "
					+ "horaEntrada = ?, "
					+ "horaSalidaComer = ?, "
					+ "horaEntradaComer = ?, "
					+ "horaSalida = ?, "
					+ "idEstatus = ?, "
					+ "idEstatusComida = ? "
					+ "WHERE idTurno = ? "
					+ "AND dia = ?";
			
			for (int i = 0; i < 7; i++) {				
				getJdbcTemplate().update(sql,
						turno.get(i).getHoraEntrada(),
						turno.get(i).getHoraSalidaComer(),
						turno.get(i).getHoraEntradaComer(),
						turno.get(i).getHoraSalida(),
						turno.get(i).getIdEstatus(),
						turno.get(i).getIdEstatusComida(),
						turno.get(i).getIdTurno(),
						turno.get(i).getDia());
			}
			
			return "correcto";
		}catch(Exception e){
			log.error("\nSQL: Error al cargar los datos.\nMotivo: {} ",e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n"
					+ "No se pudo realizar la actualización!");
		} 
	}
	
	public String eliminarTurno(int idTurno) throws SQLException{
		String sql = "DELETE FROM c_turno WHERE idTurno= ? ";

		try {
			getJdbcTemplate().update(sql, idTurno);
			
			return "correcto";
		} catch (Exception e) {
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ", e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n" + "No se pudo realizar la eliminación!");
		}
	}
	
	public String verificarEmpleados(int idTurno) throws SQLException{
		List<EmpleadoModel> empleados = new ArrayList<EmpleadoModel>();
		
		String sql = "SELECT idEmpleado FROM t_empleado WHERE idTurno = "+idTurno;
		
		try{
			empleados=getJdbcTemplate().query(sql, new RowMapper<EmpleadoModel>(){
				public EmpleadoModel mapRow(ResultSet rs, int columna) throws SQLException{
					EmpleadoModel resultValue=new EmpleadoModel();
					
					resultValue.setIdEmpleado(rs.getInt("idEmpleado"));
					
					return resultValue;
				}
			});
			
			if(empleados.isEmpty()){
				return "Sin empleados en el turno";
			}
			
			return "¡El turno no puede ser eliminado "
			+ "debido a que existen empleados afiliados a este!";
		}catch(Exception e){
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ",e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n"
					+ "No se pudo realizar la consulta!");
		}		
	}
}

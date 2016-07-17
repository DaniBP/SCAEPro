package com.greenpear.it.scaepro.dao.configurarscaepro;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
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
		}catch(Exception e){
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ",e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n"
					+ "No se pudo realizar la consulta!");
		}
		return areas;
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
		}catch(Exception e){
			log.error("\nSQL: Error al cargar los datos.\nMotivo {} ",e.getMessage());
			throw new SQLException("Existe un problema con la base de datos\n"
					+ "No se pudo realizar la consulta!");
		}
		
		return turnos;
	}
}

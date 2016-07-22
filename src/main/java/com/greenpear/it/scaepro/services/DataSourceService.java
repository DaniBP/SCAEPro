package com.greenpear.it.scaepro.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

public class DataSourceService {

private JdbcTemplate jdbcTemplate;
	
	protected JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Autowired
	@Qualifier("dataSource")
	protected void setDataSource(DataSource ds) {
		jdbcTemplate=new JdbcTemplate(ds);
	}
	
	protected DataSource getDataSource() {
		return jdbcTemplate.getDataSource();
	}
	
	public  String puerto="3306";
	public  String nomservidor="localhost";
	public  String db="scaeprobd";
	public  String user="root";
	public  String pass=null;
	
	Connection conn=null;

	public Connection conectar(){
	    try{
	    String ruta="jdbc:mysql://";
	    String servidor=nomservidor+":"+puerto+"/";
	    Class.forName("com.mysql.jdbc.Driver");
	    conn = DriverManager.getConnection(ruta+servidor+db,user,pass);

	    if (conn!=null){
	    }
	    else if (conn==null)
	    {
	    throw new SQLException();
	    }
	    }catch(SQLException e){
	        JOptionPane.showMessageDialog(null, e.getMessage());
	    } catch (ClassNotFoundException e) {
	        JOptionPane.showMessageDialog(null, "Se produjo el siguiente error: "+e.getMessage());
	    }catch (NullPointerException e){
	        JOptionPane.showMessageDialog(null, "Se produjo el siguiente error: "+e.getMessage());
	    }finally{
	    	
	    return conn;
	    
	    }
	}
	public void desconectar(){
	    conn = null;
	}
}

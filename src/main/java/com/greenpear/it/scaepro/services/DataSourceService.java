package com.greenpear.it.scaepro.services;

import javax.sql.DataSource;

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
}

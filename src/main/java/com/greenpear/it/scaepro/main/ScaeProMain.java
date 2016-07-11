package com.greenpear.it.scaepro.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.greenpear.it.scaepro.controller.government.Government;

/**
 * @author DanielBP
 *
 */
@Component
public class ScaeProMain {

	private static final Logger log = LoggerFactory.getLogger(ScaeProMain.class);
	
	public static void main(String[] args) {
		log.debug("Configurando el aplicativo SMART...");
	    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/spring/applicationContext.xml");
	    log.info("Configurando el aplicativo SMART... OK");
	    ((Government) ctx.getBean("government")).mostrarVistaSolicitudReporte();
	}
}

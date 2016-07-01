/**
 * 
 */
package com.greenpear.it.scaepro.view.administracionmovil;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * @author EDSONJOSUE
 *
 */
public class NoticiaImagenView extends JFrame {
	public JLabel lblImagen;
	
	public NoticiaImagenView() {
		setResizable(false);
		setBounds(100, 100, 544, 321);
		getContentPane().setLayout(null);
		
		lblImagen = new JLabel("");
		lblImagen.setBounds(0, 0, 544, 321);
		getContentPane().add(lblImagen);
	}
}

/**
 * 
 */
package com.greenpear.it.scaepro.view.administracionmovil;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Toolkit;

/**
 * @author EDSONJOSUE
 *
 */
public class NoticiaImagenView extends JFrame {
	public JLabel lblImagen;
	
	public NoticiaImagenView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(NoticiaImagenView.class.getResource("/img/Logo1.png")));
		setResizable(false);
		setBounds(100, 100, 544, 321);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		lblImagen = new JLabel("");
		lblImagen.setBounds(0, 0, 544, 321);
		getContentPane().add(lblImagen);
	}
}

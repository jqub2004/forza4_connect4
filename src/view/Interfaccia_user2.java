package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.Control;
import scambio_informazioni.Dati;

import java.awt.Color;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public class Interfaccia_user2 extends JFrame {
	final String dirBase = System.getProperty("user.dir");
	final int dim = 68;
	private JPanel contentPane;
	private Control c;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	private JButton btnNewButton_6;
	private JButton RitornaSchermatabase = new JButton("RITORNA ALLA SCHERMATA INIZIALE");
	private Control control;
	private String[] mosse;

	public Interfaccia_user2() {
		mosse = generaMosse();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 825, 533);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(191, 255, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnNewButton = new JButton("");
		btnNewButton.setFont(new Font("Century Gothic", Font.ITALIC, 10));
		btnNewButton.setBounds(157, 10, 68, 68);
		contentPane.add(btnNewButton);
		btnNewButton_1 = new JButton("");
		btnNewButton_1.setFont(new Font("Century Gothic", Font.ITALIC, 10));
		btnNewButton_1.setBounds(225, 10, 68, 68);
		contentPane.add(btnNewButton_1);

		btnNewButton_2 = new JButton("");
		btnNewButton_2.setFont(new Font("Century Gothic", Font.ITALIC, 10));
		btnNewButton_2.setBounds(293, 10, 68, 68);
		contentPane.add(btnNewButton_2);

		btnNewButton_3 = new JButton("");
		btnNewButton_3.setFont(new Font("Century Gothic", Font.ITALIC, 10));
		btnNewButton_3.setBounds(361, 10, 68, 68);
		contentPane.add(btnNewButton_3);

		btnNewButton_4 = new JButton("");
		btnNewButton_4.setFont(new Font("Century Gothic", Font.ITALIC, 10));
		btnNewButton_4.setBounds(429, 10, 68, 68);
		contentPane.add(btnNewButton_4);

		btnNewButton_5 = new JButton("");
		btnNewButton_5.setFont(new Font("Century Gothic", Font.ITALIC, 10));
		btnNewButton_5.setBounds(497, 10, 68, 68);
		contentPane.add(btnNewButton_5);

		btnNewButton_6 = new JButton("");
		btnNewButton_6.setFont(new Font("Century Gothic", Font.ITALIC, 10));
		btnNewButton_6.setBounds(565, 10, 68, 68);
		contentPane.add(btnNewButton_6);

		genera_celle(157, 78, contentPane, dim, mosse);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public Interfaccia_user2(String[] mosse) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 825, 533);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(191, 255, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnNewButton = new JButton("");
		btnNewButton.setFont(new Font("Century Gothic", Font.ITALIC, 10));
		btnNewButton.setBounds(157, 10, 68, 68);
		contentPane.add(btnNewButton);
		btnNewButton_1 = new JButton("");
		btnNewButton_1.setFont(new Font("Century Gothic", Font.ITALIC, 10));
		btnNewButton_1.setBounds(225, 10, 68, 68);
		contentPane.add(btnNewButton_1);

		btnNewButton_2 = new JButton("");
		btnNewButton_2.setFont(new Font("Century Gothic", Font.ITALIC, 10));
		btnNewButton_2.setBounds(293, 10, 68, 68);
		contentPane.add(btnNewButton_2);

		btnNewButton_3 = new JButton("");
		btnNewButton_3.setFont(new Font("Century Gothic", Font.ITALIC, 10));
		btnNewButton_3.setBounds(361, 10, 68, 68);
		contentPane.add(btnNewButton_3);

		btnNewButton_4 = new JButton("");
		btnNewButton_4.setFont(new Font("Century Gothic", Font.ITALIC, 10));
		btnNewButton_4.setBounds(429, 10, 68, 68);
		contentPane.add(btnNewButton_4);

		btnNewButton_5 = new JButton("");
		btnNewButton_5.setFont(new Font("Century Gothic", Font.ITALIC, 10));
		btnNewButton_5.setBounds(497, 10, 68, 68);
		contentPane.add(btnNewButton_5);

		btnNewButton_6 = new JButton("");
		btnNewButton_6.setFont(new Font("Century Gothic", Font.ITALIC, 10));
		btnNewButton_6.setBounds(565, 10, 68, 68);
		contentPane.add(btnNewButton_6);

		genera_celle(157, 78, contentPane, dim, mosse);
		this.setResizable(false);
		this.setVisible(false);
	}

	public JButton getBtnNewButton() {
		return btnNewButton;
	}

	public void setBtnNewButton(JButton btnNewButton) {
		this.btnNewButton = btnNewButton;
	}

	public JButton getBtnNewButton_1() {
		return btnNewButton_1;
	}

	public void setBtnNewButton_1(JButton btnNewButton_1) {
		this.btnNewButton_1 = btnNewButton_1;
	}

	public JButton getBtnNewButton_2() {
		return btnNewButton_2;
	}

	public void setBtnNewButton_2(JButton btnNewButton_2) {
		this.btnNewButton_2 = btnNewButton_2;
	}

	public JButton getBtnNewButton_3() {
		return btnNewButton_3;
	}

	public void setBtnNewButton_3(JButton btnNewButton_3) {
		this.btnNewButton_3 = btnNewButton_3;
	}

	public JButton getBtnNewButton_4() {
		return btnNewButton_4;
	}

	public void setBtnNewButton_4(JButton btnNewButton_4) {
		this.btnNewButton_4 = btnNewButton_4;
	}

	public JButton getBtnNewButton_5() {
		return btnNewButton_5;
	}

	public void setBtnNewButton_5(JButton btnNewButton_5) {
		this.btnNewButton_5 = btnNewButton_5;
	}

	public JButton getBtnNewButton_6() {
		return btnNewButton_6;
	}

	public void setBtnNewButton_6(JButton btnNewButton_6) {
		this.btnNewButton_6 = btnNewButton_6;
	}
	//genero la cella iniziale e quella modificata
	public void genera_celle(int x, int y, JPanel contentPane, final int dim, String[] mosse) {
		int coordinata_x = x;
		int coordinata_y = y;
		int i = 0;
		for (int riga = 1; riga <= 6; riga++) {
			for (int colonna = 1; colonna <= 7; colonna++) {
				JLabel cella = new JLabel(mosse[i]);
				String[] cont = mosse[i].split("-");
				switch (cont[1]) {
				case "g":
					cella.setIcon(new ImageIcon(dirBase + "/src/img/giocatore1.jpg"));
					cella.setBounds(coordinata_x, coordinata_y, dim, dim);
					coordinata_x += dim;
					contentPane.add(cella);
					break;
				case "r":
					cella.setIcon(new ImageIcon(dirBase + "/src/img/giocatore2.jpg"));
					cella.setBounds(coordinata_x, coordinata_y, dim, dim);
					coordinata_x += dim;
					contentPane.add(cella);
					break;
				default:
					cella.setIcon(new ImageIcon(dirBase + "/src/img/cella_vuota.jpg"));
					cella.setBounds(coordinata_x, coordinata_y, dim, dim);
					coordinata_x += dim;
					contentPane.add(cella);
					break;
				}
				i++;
			}
			coordinata_x = x;
			coordinata_y += dim;
		}
	}

	public String getDirBase() {
		return dirBase;
	}

	public int getDim() {
		return dim;
	}

	public String[] getMosse() {
		return mosse;
	}

	public void setMosse(String[] mosse) {
		this.mosse = mosse;
	}
	//inizializzo il buffer locale 
	private String[] generaMosse() {
		String[] mosse1 = new String[42];
		int cont=0;
		boolean flag = false;
		for (int i = 1; i <= 6; i++) {
			for (int t = 1; t <= 7; t++) {
				if(cont>=42)
				{
					flag = true;
					break;
				}
				mosse1[cont] = (i + t + "-n");
				cont++;
			}
			if(flag == true)
				break;
		}
		return mosse1;
		
	}

}
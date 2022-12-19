package view;
import scambio_informazioni.Dati;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import control.Control;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Interfaccia_user extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JTextField textField;
	private JButton btnNewButton;
	private JLabel lblNewLabel_1;
	//interfaccia del gioco iniziale
	public Interfaccia_user() {
		setBounds(100, 100, 814, 505);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 210, 210));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("Forza 4");
		lblNewLabel.setFont(new Font("Century Gothic", Font.ITALIC, 40));
		lblNewLabel.setBounds(335, 80, 231, 71);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.ITALIC, 16));
		textField.setBounds(291, 269, 213, 36);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton("Login");
		btnNewButton.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 15));
		btnNewButton.setForeground(new Color(64, 0, 64));
		btnNewButton.setBackground(new Color(0, 255, 128));
		btnNewButton.setBounds(351, 377, 85, 36);
		contentPane.add(btnNewButton);
		
		lblNewLabel_1 = new JLabel("IP_SERVER");
		lblNewLabel_1.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_1.setBounds(340, 204, 138, 36);
		contentPane.add(lblNewLabel_1);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}
	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}
	public void setLblNewLabel(JLabel lblNewLabel) {
		this.lblNewLabel = lblNewLabel;
	}
	public JTextField getTextField() {
		return textField;
	}
	public void setTextField(JTextField textField) {
		this.textField = textField;
	}
	public JButton getBtnNewButton() {
		return btnNewButton;
	}
	public void setBtnNewButton(JButton btnNewButton) {
		this.btnNewButton = btnNewButton;
	}
	public JLabel getLblNewLabel_1() {
		return lblNewLabel_1;
	}
	public void setLblNewLabel_1(JLabel lblNewLabel_1) {
		this.lblNewLabel_1 = lblNewLabel_1;
	}
	//main
	public static void main(String[] args) {
		//inizializzo tutte le interfacce e il relativo client "Control.java"
		Interfaccia_user interfaccia_user = new Interfaccia_user();
		Interfaccia_user2 interfaccia_user2 = new Interfaccia_user2();
		SchermoVittoria schermoVittoria = new SchermoVittoria();
		Control controller = new Control(interfaccia_user,interfaccia_user2,schermoVittoria);

	}
	
}

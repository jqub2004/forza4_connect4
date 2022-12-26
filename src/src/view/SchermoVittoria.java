package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class SchermoVittoria extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JButton btnNewButton;
	public SchermoVittoria() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 826, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 36));
		lblNewLabel.setBounds(193, 32, 444, 85);
		contentPane.add(lblNewLabel);
		
		btnNewButton = new JButton("torna al login");
		btnNewButton.setFont(new Font("Century Gothic", Font.ITALIC, 22));
		btnNewButton.setBounds(249, 317, 333, 70);
		contentPane.add(btnNewButton);
		
		this.setResizable(false);
		this.setVisible(false);
	}
	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}
	public void setLblNewLabel(JLabel lblNewLabel) {
		this.lblNewLabel = lblNewLabel;
	}
	public JButton getBtnNewButton() {
		return btnNewButton;
	}
	public void setBtnNewButton(JButton btnNewButton) {
		this.btnNewButton = btnNewButton;
	}
	
}
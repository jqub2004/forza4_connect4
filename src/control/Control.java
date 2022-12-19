package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import scambio_informazioni.Dati;
import view.Interfaccia_user;
import view.Interfaccia_user2;
import view.SchermoVittoria;

public class Control implements ActionListener {
	private Socket socket;
	private ObjectOutputStream objectOutputStream;
	private OutputStream outputStream;
	private ObjectInputStream objectInputStream;
	private InputStream inputStream;
	private Dati dati;
	private Interfaccia_user interfaccia_user;
	private Interfaccia_user2 interfaccia_user2;
	private String catchValue;
	private String catchIP;
	boolean check_pausa = false;
	public String[] mosse = new String[42];
	private String operatore;
	private ExecutorService executorService;
	private Dati contr_dati;
	private SchermoVittoria schermoVittoria;
	private int porta = 10000;

	// inizializzo tutte le interfacce, e il buffer locale
	public Control(Interfaccia_user interfaccia_user, Interfaccia_user2 interfaccia_user2,
			SchermoVittoria schermoVittoria) {
		this.interfaccia_user = interfaccia_user;
		this.schermoVittoria = schermoVittoria;
		this.interfaccia_user2 = interfaccia_user2;
		int cont = 0;
		for (int i = 1; i <= 6; i++) {
			for (int t = 1; t <= 7; t++) {
				mosse[cont] = (i + t + "-n");
				cont++;
			}
		}
		listener();
	}

	@Override

	// tutti gli eventi quando vengono seguiti
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == interfaccia_user.getBtnNewButton()) {
			catchIP = interfaccia_user.getTextField().getText();
			System.out.println(catchIP);
			if (catchIP.equals("")) {
				System.out.println("errore");
			} else {
				executorService = Executors.newFixedThreadPool(3);
				interfaccia_user.setVisible(false);
				interfaccia_user2.setVisible(true);
				client(catchIP, porta);
				interfaccia_user2.setMosse(mosse);

			}
		}
		System.out.println("azione\n");
		// interfaccia di gioco principale
		if (e.getSource() == interfaccia_user2.getBtnNewButton()) {
			calcMossa(0);
		}
		if (e.getSource() == interfaccia_user2.getBtnNewButton_1()) {
			calcMossa(1);
		}
		if (e.getSource() == interfaccia_user2.getBtnNewButton_2()) {
			calcMossa(2);
		}
		if (e.getSource() == interfaccia_user2.getBtnNewButton_3()) {
			calcMossa(3);
		}
		if (e.getSource() == interfaccia_user2.getBtnNewButton_4()) {
			calcMossa(4);
		}
		if (e.getSource() == interfaccia_user2.getBtnNewButton_5()) {
			calcMossa(5);
		}
		if (e.getSource() == interfaccia_user2.getBtnNewButton_6()) {
			calcMossa(6);
		}

		// schermata sconfitta/vittoria/pareggio
		if (e.getSource() == schermoVittoria.getBtnNewButton()) {
			schermoVittoria.setVisible(false);
			interfaccia_user.setVisible(true);
		}
	}

	// preparo i dati da mandare al server
	private void preparazioneDati(String mossa) {
		Dati dato1 = new Dati(mossa, operatore, Dati.Operazione.MOS);
		invioDati(dato1);
	}

	// confronto il buffer locale con quello condiviso, e opportuna modifica
	private void calcMossa(int colonna) {
		for (int i = 0; i < 42; i++) {
			String controllo[] = mosse[i].split("-");
			int integer = Integer.parseInt(controllo[0]);
			if (integer % 10 == colonna && controllo[1].equals("n") == true) {
				preparazioneDati(integer + "-" + operatore);
				break;
			}
		}
	}

	// aggiungo tutti i listener ai bottoni del programma
	private void listener() {
		interfaccia_user.getBtnNewButton().addActionListener(this);
		schermoVittoria.getBtnNewButton().addActionListener(this);
		interfaccia_user2.getBtnNewButton().addActionListener(this);
		interfaccia_user2.getBtnNewButton_1().addActionListener(this);
		interfaccia_user2.getBtnNewButton_2().addActionListener(this);
		interfaccia_user2.getBtnNewButton_3().addActionListener(this);
		interfaccia_user2.getBtnNewButton_4().addActionListener(this);
		interfaccia_user2.getBtnNewButton_5().addActionListener(this);
		interfaccia_user2.getBtnNewButton_6().addActionListener(this);
	}

	public String getCatchValue() {
		return catchValue;
	}

	public void setCatchValue(String catchValue) {
		this.catchValue = catchValue;
	}

	public String getCatchIP() {
		return catchIP;
	}

	public void setCatchIP(String catchIP) {
		this.catchIP = catchIP;
	}

	// creo il client per la connessione
	public void client(String indirizzo, int port) {
		try {
			System.out.println("creazione socket");
			socket = new Socket(indirizzo, port);
			outputStream = socket.getOutputStream();
			objectOutputStream = new ObjectOutputStream(outputStream);
			System.out.println("funziona il socket!");
			executorService.submit(this::run);
			//executorService.submit(this::controlloConnessione);
		} catch (Exception e) {
			client(indirizzo, port + 1);
			e.printStackTrace();
		}
	}

	// invio i dati al server
	public void invioDati(Dati dato) {
		try {
			objectOutputStream.writeObject(dato);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// controllo in maniera ciclica le eventuali operazione effettuati dall'utente'
	public void run() {
		try {
			inputStream = socket.getInputStream();
			objectInputStream = new ObjectInputStream(inputStream);
			while (true) {
				System.out.println("lettura");
				Dati datoInput = (Dati) objectInputStream.readObject();
				contr_dati = datoInput;
				System.out.println(datoInput.getMossa() + datoInput.getOperatore());
				if (datoInput.getOp() == Dati.Operazione.GIO) {
					dati = datoInput;
				} else if (datoInput.getOp() == Dati.Operazione.ERR) {
					System.out.println(datoInput.getMessErrore());
				} else if (datoInput.getOp() == Dati.Operazione.PAR) {
					interfaccia_user2.setVisible(false);
					schermoVittoria.setVisible(true);
					schermoVittoria.getLblNewLabel().setText("pareggio");
				} else if (datoInput.getOp() == Dati.Operazione.PER) {
					interfaccia_user2.setVisible(false);
					schermoVittoria.setVisible(true);
					schermoVittoria.getLblNewLabel().setText("sconfitta");
				} else if (datoInput.getOp() == Dati.Operazione.VIT) {
					interfaccia_user2.setVisible(false);
					schermoVittoria.setVisible(true);
					schermoVittoria.getLblNewLabel().setText("vittoria");
				} else if (datoInput.getOp() == Dati.Operazione.NOM) {
					operatore = datoInput.getTesto();
					System.out.println(operatore);
				} else if (datoInput.getOp() == Dati.Operazione.MOS) {
					aggionaBuffer(datoInput);
					interfaccia_user2.setMosse(mosse);
				}
				Thread.sleep(250);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// controllo la connessione se è stato effettuato in caso contrario si connetta a un'altra porta
//	public void controlloConnessione() {
//		try {
//			System.out.println("errore connessione");
//			Thread.sleep(500);
//			if (contr_dati.getOperatore().equals("")) {
//				System.out.println("cambio connessione");
//				porta = porta++;
//				client(catchIP, porta);
//			}
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//
//	}

	// aggiorno il buffer locale nel caso che quello condiviso tra i server sono
	// modificati
	public void aggionaBuffer(Dati dato1) {
		for (int j = 0; j < mosse.length; j++) {
			String controllo[] = mosse[j].split("-");
			int integer = Integer.parseInt(controllo[0]);
			String controllo1[] = dato1.getMossa().split("-");
			int integer1 = Integer.parseInt(controllo[0]);
			if (integer % 10 == integer1 % 10 && integer / 10 == integer1 / 10 && controllo[1].equals("n") == true) {
				String buffer1[] = mosse;
				for (int i = 0; i < buffer1.length; i++) {
					if (i == j) {
						buffer1[j] = dato1.getMossa();
					}
				}
				break;
			}
		}
	}
}

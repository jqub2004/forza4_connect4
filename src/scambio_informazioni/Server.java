package scambio_informazioni;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Spliterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server extends Thread implements Runnable {
	private ExecutorService executorService;
	private ServerSocket server;
	private Socket richiestaClient;
	public int port;
	private char controllo;
	private BufferServer buffer;
	private BufferServer bufferLocal = new BufferServer();;
	private InputStream inputStream;
	private OutputStream outputStream;
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;
	private Dati dato;
	private String operatore;

	//creo il primo server
	public Server(BufferServer buffer, int num) {
		try {
			this.buffer = buffer;
			executorService = Executors.newFixedThreadPool(2);
			server = new ServerSocket(num,0);
			server.setReuseAddress(true);
			server.getInetAddress();
			System.out.println("Server" + num + " attivo " + InetAddress.getLocalHost());
			richiestaClient = server.accept();
			System.out.println("connesso con " + richiestaClient + "!");
			outputStream = richiestaClient.getOutputStream();
			objectOutputStream = new ObjectOutputStream(outputStream);
			invioDati1();
			this.start();
		} catch (IOException e) {
			server(buffer, num + 1);
			e.printStackTrace();
		}
	}
	//il secondo server
	public void server(BufferServer buffer, int num) {
		try {
			this.buffer = buffer;
			executorService = Executors.newFixedThreadPool(2);
			server = new ServerSocket(num);
			server.getInetAddress();
			System.out.println("Server" + num + " attivo " + InetAddress.getLocalHost());
			richiestaClient = server.accept();
			System.out.println("connesso con " + richiestaClient + "!");
			outputStream = richiestaClient.getOutputStream();
			invioDati1();
			executorService.submit(this::contrBufferServerCondiviso);
			executorService.submit(this::run);
		} catch (IOException e) {
			server(buffer, num + 1);
			e.printStackTrace();
		}
	}

	//controlla ciclicamente se ci sono nuovi dati se client abbia spedito
	public void run() {
		try {

			inputStream = richiestaClient.getInputStream();
			objectInputStream = new ObjectInputStream(inputStream);
			while (true) {
				dato = (Dati) objectInputStream.readObject();
				Dati dato1 = new Dati(dato.getMossa(), "server", dato.getOp());
				invioDatiGenerico(dato1);
				Thread.sleep(250);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//controllo il buffer condiviso con quello locale
	public void contrBufferServerCondiviso() {
		Dati dato1;
		try {
			while (true) {
				if (!bufferLocal.getmosse().equals(buffer.getmosse())) {
					String mosse1[] = buffer.getmosse();
					for (int i = 0; i < mosse1.length; i++) {
						if (!mosse1[i].equals(bufferLocal.getmosse()[i])) {
							invioDatiGenerico(dato1 = new Dati(mosse1[i], "server", Dati.Operazione.MOS));
						}
					}

				}

				Thread.sleep(250);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	//invio i dati iniziali al client(Control.java)
	public synchronized void invioDati1() {
		try {
			objectOutputStream.writeObject(dato = new Dati("server", Dati.Operazione.NOM, controlloUsers()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//invio i dati al client(Control.java)
	public synchronized void invioDatiGenerico(Dati dato) {
		try {
			objectOutputStream.writeObject(dato);
			System.out.println("mandato mesaggio\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//controllo se il turno è del giocatore 1 oppure 2 
	public String controlloUsers() {
		if (!buffer.getGiocatore1()) {
			operatore = "g";
			return "g";
		} else if (!buffer.getGiocatore2()) {
			operatore = "r";
			return "r";
		}
		operatore = "n";
		return "n";

	}
	//aggiornamento del buffer che contiene le mosse dei giocatori
	public void aggiornamentoBuffer(Dati dato) {
		for (int j = 0; j < buffer.getmosse().length; j++) {
			String controllo[] = buffer.getmosse()[j].split("-");
			int integer = Integer.parseInt(controllo[0]); // %10 = colonne,/10= righe
			String controllo1[] = dato.getMossa().split("-");
			int integer1 = Integer.parseInt(controllo[0]);
			if (integer % 10 == integer1 % 10 && integer / 10 == integer1 / 10 && controllo[1].equals("n") == true) {
				String buffer1[] = buffer.getmosse();
				buffer1[j] = dato.getMossa();
				bufferLocal.setmosse(buffer1);
				buffer.setmosse(buffer1);
				break;
			}
		}
		System.out.println(buffer.toString());

	}
	//confronto tra le celle per verificare le condizioni di vittoria
	public boolean confronto4Str(String a, String b, String c, String d) {
		if (a.compareTo(b) == 0 && b.compareTo(c) == 0 && c.compareTo(d) == 0 && a.compareTo("n") != 0) {
			return true;
		}
		return false;
	}
	//controllo delle varie situazioni che inducono alla vittora/sconfitta, oppure pareggio
	public void controlloVincita(String[] mosse) {
		Dati dato1;
		for (int j = 0; j < 42; j++) {
			Dati dati1;
			String[] cont = buffer.getmosse()[j].split("n");
			int asd = Integer.parseInt(cont[0]);
			// controllo vittoria in riga orizzontale
			if (j < 39) {
				String controllo[] = buffer.getmosse()[j].split("-");
				String controllo1[] = buffer.getmosse()[j + 1].split("-");
				String controllo2[] = buffer.getmosse()[j + 2].split("-");
				String controllo3[] = buffer.getmosse()[j + 3].split("-");
				if (confronto4Str(controllo[1], controllo1[1], controllo2[1], controllo3[1])) {
					if (controllo[1].compareTo(operatore) == 0) {
						invioDatiGenerico(dato1 = new Dati("server", Dati.Operazione.VIT));// in caso di vittoria di un
																							// giocatore 1 o 2;
					} else {
						invioDatiGenerico(dato1 = new Dati("server", Dati.Operazione.PER));// in caso di sconfitta
																							// dell'altro giocatore;
					}

				}

			} else {
				invioDatiGenerico(dato1 = new Dati("server", Dati.Operazione.PAR)); // nel caso in cui nessuno dei due																// giocatori vince
			}

			// controllo vittoria in riga verticale
			if (j < 21) {
				String contrColomn[] = buffer.getmosse()[j].split("-");
				String contrColomn1[] = buffer.getmosse()[j + 7].split("-");
				String contrColomn2[] = buffer.getmosse()[j + 14].split("-");
				String contrColomn3[] = buffer.getmosse()[j + 21].split("-");
				if (confronto4Str(contrColomn[1], contrColomn1[1], contrColomn2[1], contrColomn3[1])) {
					if (contrColomn[1].compareTo(operatore) == 0)
						invioDatiGenerico(dati1 = new Dati("server", Dati.Operazione.VIT));
					else
						invioDatiGenerico(dato1 = new Dati("server", Dati.Operazione.PER));
				}
			} else {
				invioDatiGenerico(dato1 = new Dati("server", Dati.Operazione.PAR));
			}

			// controllo della diagonale rivolto a destra
			if (asd % 10 <= 4) {
				String contrDia[] = buffer.getmosse()[j].split("-");
				String contrDia1[] = buffer.getmosse()[j + 8].split("-");
				String contrDia2[] = buffer.getmosse()[j + 16].split("-");
				String contrDia3[] = buffer.getmosse()[j + 24].split("-");
				if (confronto4Str(contrDia[1], contrDia1[1], contrDia2[1], contrDia3[1])
						&& contrDia[1].compareTo(operatore) == 0) {
					if (contrDia[1].compareTo(operatore) == 0)
						invioDatiGenerico(dati1 = new Dati("server", Dati.Operazione.VIT));
					else
						invioDatiGenerico(dato1 = new Dati("server", Dati.Operazione.PER));
				}
			} else {
				if (j >= 18) {
					invioDatiGenerico(dato1 = new Dati("server", Dati.Operazione.PAR));
				}
			}

			// controllo della diagonale rivolto a sinistra
			if (asd % 10 >= 4) {
				String contrSinistra[] = buffer.getmosse()[j].split("-");
				String contrSinistra1[] = buffer.getmosse()[j + 6].split("n");
				String contrSinistra2[] = buffer.getmosse()[j + 12].split("n");
				String contrSinistra3[] = buffer.getmosse()[j + 18].split("n");
				if (confronto4Str(contrSinistra[1], contrSinistra1[1], contrSinistra2[1], contrSinistra3[1])
						&& contrSinistra[1].compareTo(operatore) == 0) {
					if (contrSinistra[1].compareTo(operatore) == 0)
						invioDatiGenerico(dati1 = new Dati("server", Dati.Operazione.VIT));
					else
						invioDatiGenerico(dato1 = new Dati("server", Dati.Operazione.PER));
				}
			} else {
				if (j >= 21) {
					invioDatiGenerico(dati1 = new Dati("server", Dati.Operazione.PAR));
				}
			}
		}

	}

}

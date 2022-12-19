package scambio_informazioni;

//classe buffer condiviso
public class BufferServer {
	public String[] mosse = new String[42];
	private boolean giocatore1 = false;
	private boolean giocatore2 = false;

	public BufferServer(String[] mosse, boolean giocatore1, boolean giocatore2) {
		this.mosse = mosse;
		this.giocatore1 = giocatore1;
		this.giocatore2 = giocatore2;
	}
	//inizializzo il contenuto del buffer condiviso
	public BufferServer() {
		int cont=0;
		for (int i = 1; i <= 6; i++) {
			for (int t = 1; t <= 7; t++) {
				mosse[cont] = (i + t + "-n");
				cont++;
			}
		}
		
	}

	public String[] getmosse() {
		return mosse;
	}

	public void setmosse(String[] mosse) {
		this.mosse = mosse;
	}

	public Boolean getGiocatore1() {
		return giocatore1;
	}

	public Boolean getGiocatore2() {
		return giocatore2;
	}

	public void setGiocatore1(Boolean giocatore1) {
		this.giocatore1 = giocatore1;
	}

	public void setGiocatore2(Boolean giocatore2) {
		this.giocatore2 = giocatore2;
	}

}

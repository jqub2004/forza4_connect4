package scambio_informazioni;

import java.io.Serializable;

public class Dati implements Serializable {
	private String mossa;
	private String operatore;
	private Operazione op;
	private String testo;

	public static enum Operazione {
		MOS, VIT, PER, PAR, ERR, GIO, NOM, MES
	}

	public Dati(String mossa, String operatore, Operazione op, String testo) {
		this.mossa = mossa;
		this.operatore = operatore;
		this.op = op;
		this.testo = testo;
	}
	
	public Dati( String operatore, Operazione op, String testo) {
		this.operatore = operatore;
		this.op = op;
		this.testo = testo;
	}
	
	public Dati( String operatore, Operazione op) {
		this.operatore = operatore;
		this.op = op;
	}
	
	public Dati(String mossa, String operatore, Operazione op) {
		this.mossa = mossa;
		this.operatore = operatore;
		this.op = op;
	}

	public String getMossa() {
		return mossa;
	}

	public String getOperatore() {
		return operatore;
	}

	public Operazione getOp() {
		return op;
	};
	
	public String getMessErrore() {
		return testo;
	}

	public String getTesto() {
		return testo;
	}
	
}

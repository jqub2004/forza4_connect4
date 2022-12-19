package scambio_informazioni;

public class Server_main{
	//la classe crea una coppia di server per i due giocatori
	public static void main(String[] args) {
		
		BufferServer buffer = new BufferServer();
		Server server = new Server(buffer,10000);
		Server server1 = new Server(buffer,10000);
	}
}



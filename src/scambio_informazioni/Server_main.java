package scambio_informazioni;

import java.net.ServerSocket;

public class Server_main {
	// la classe crea una coppia di server per i due giocatori
	public static void main(String[] args) {

		BufferServer buffer = new BufferServer();
		ServerSocket serverS = creaServer(10000);
		Server server = new Server(buffer, serverS.getLocalPort(), serverS);
		Server server1 = new Server(buffer, serverS.getLocalPort(), serverS);

	}

	public static ServerSocket creaServer(int num) {
		ServerSocket serverS;
		try {
			serverS = new ServerSocket(num, 0);
			return serverS;
		} catch (Exception e) {
			e.printStackTrace();
			creaServer(num++);
		}
		return null;
	}
}

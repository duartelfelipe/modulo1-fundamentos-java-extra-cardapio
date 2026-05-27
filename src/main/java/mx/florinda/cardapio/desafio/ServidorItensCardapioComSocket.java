package mx.florinda.cardapio.desafio;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ServidorItensCardapioComSocket {

    public static void main(String[] args) throws Exception {

        Executor executor = Executors.newFixedThreadPool(50);

        try (ServerSocket serverSocket = new ServerSocket(8000)) {
            System.out.println("Subiu servidor!");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                executor.execute(() -> TrataRequisicaoSocket.tratar(clientSocket));
            }
        }
    }
}

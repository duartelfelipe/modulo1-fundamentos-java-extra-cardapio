package mx.florinda.cardapio.desafio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class TrataRequisicaoSocket {

    public static void tratar(Socket clientSocket) {

        PrintStream clientOut = null;
        String requestURI;
        String method;
        String httpVersion;

        try (clientSocket) {
            String request = getRequest(clientSocket);
            String[] requestChunks = getRequestLineChunks(request);

            method = requestChunks[0];
            requestURI = requestChunks[1];
            httpVersion = requestChunks[2];

            System.out.println("Method: " + method);
            System.out.println("Request URI: " + requestURI);
            System.out.println("HTTP Version: " + httpVersion);

            Thread.sleep(250);

            OutputStream clientOS = clientSocket.getOutputStream();

            TrataRequisicao
                    .criar(method)
                    .tratar(requestURI, clientOS, requestChunks);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static String getRequest(Socket clientSocket) throws IOException {
        InputStream clientIS = clientSocket.getInputStream();

        StringBuilder requestBuilder = new StringBuilder();
        int data;
        do {
            data = clientIS.read();
            requestBuilder.append((char) data);
        } while (clientIS.available() > 0);

        String request = requestBuilder.toString();

        System.out.println(request);
        System.out.println("\n\nChegou um novo request");
        return request;
    }

    private static String[] getRequestLineChunks(String request) {
        String[] requestChunks = request.split("\r\n\r\n");
        String requestLineAndHeaders = requestChunks[0];
        String[] requestLineAndHeadersChunks = requestLineAndHeaders.split("\r\n");
        String requestLine = requestLineAndHeadersChunks[0];

        return requestLine.split(" ");
    }

}

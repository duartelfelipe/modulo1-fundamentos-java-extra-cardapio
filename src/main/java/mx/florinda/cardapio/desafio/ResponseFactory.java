package mx.florinda.cardapio.desafio;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Objects;

public class ResponseFactory {

    public static void ok(OutputStream clientOS, String conteudo, boolean isJson) {
        buildResponse(clientOS, "200", "OK", conteudo, isJson);
    }

    public static void notFound(OutputStream clientOS) {
        buildResponse(clientOS, "404", "NOT_FOUND", "", false);
    }

    public static void internalServerError(OutputStream clientOS) {
        buildResponse(clientOS, "500", "INTERNAL_SERVER_ERROR", "", false);
    }


    private static void buildResponse(OutputStream clientOS,
                                      String status,
                                      String statusMessage,
                                      String conteudo,
                                      boolean isJson) {
        PrintStream clientOut = new PrintStream(clientOS);

        clientOut.println("HTTP/1.1 " + status + " " + statusMessage);

        if (isJson) {
            clientOut.println("Content-type: application/json; charset=UTF-8");
        }

        if (!Objects.isNull(conteudo)) {
            clientOut.println();
            clientOut.println(conteudo);
        }
    }
}

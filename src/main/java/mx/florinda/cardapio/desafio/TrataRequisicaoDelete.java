package mx.florinda.cardapio.desafio;

import java.io.OutputStream;

public class TrataRequisicaoDelete extends TrataRequisicaoBase {

    @Override
    protected void trataUri(OutputStream clientOS, String requestURI, String[] requestChunks) {
        if (requestURI.contains("/itens-cardapio/")) {
            String[] itensCardapioChunks = requestURI.split("/");

            if (itensCardapioChunks.length == 3) {
                System.out.println("Chamou listagem de itens de cardápio");

                Long id = Long.parseLong(itensCardapioChunks[2]);

                if (database.removeItemCardapio(id)) {
                    ResponseFactory.ok(clientOS, null, false);
                    return;
                } else {
                    ResponseFactory.internalServerError(clientOS);
                    return;
                }
            }
        }
        ResponseFactory.notFound(clientOS);
    }
}

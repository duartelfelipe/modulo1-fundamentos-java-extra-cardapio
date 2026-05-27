package mx.florinda.cardapio.desafio;

import com.google.gson.Gson;
import mx.florinda.cardapio.ItemCardapio;

import java.io.OutputStream;

public class TrataRequisicaoPost extends TrataRequisicaoBase {

    @Override
    protected void trataUri(OutputStream clientOS, String requestURI, String[] requestChunks) {
        if ("/itens-cardapio".equals(requestURI)) {
            System.out.println("Chamou inclusão de itens de cardápio");

            if (requestChunks.length == 2) {
                String body = requestChunks[1];

                Gson gson = new Gson();
                ItemCardapio item = gson.fromJson(body, ItemCardapio.class);

                database.adicionaItemCardapio(item);

                ResponseFactory.ok(clientOS, null, false);
                return;
            }
        }
        ResponseFactory.notFound(clientOS);
    }
}

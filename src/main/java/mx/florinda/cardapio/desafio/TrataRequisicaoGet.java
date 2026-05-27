package mx.florinda.cardapio.desafio;

import com.google.gson.Gson;
import mx.florinda.cardapio.ItemCardapio;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TrataRequisicaoGet extends TrataRequisicaoBase {

    @Override
    protected void trataUri(OutputStream clientOS, String requestURI, String[] requestChunks) {
        if ("/itensCardapio.json".equals(requestURI)) {
            System.out.println("Chamou arquivo itensCardapio.json");

            try {
                Path path = Path.of("itensCardapio.json");
                String json = Files.readString(path);
                ResponseFactory.ok(clientOS, json, true);
                return;
            } catch (Exception ex) {
                ResponseFactory.internalServerError(clientOS);
                return;
            }

        }

        if ("/itens-cardapio".equals(requestURI)) {
            System.out.println("Chamou listagem de itens de cardápio");
            List<ItemCardapio> listaItensCardapio = database.listaItensCardapio();

            Gson gson = new Gson();
            String json = gson.toJson(listaItensCardapio);

            ResponseFactory.ok(clientOS, json, true);
            return;
        }

        if ("/itens-cardapio/total".equals(requestURI)) {
            System.out.println("Chamou total de itens de cardápio");
            int totalItens = database.totalItensCardapio();
            ResponseFactory.ok(clientOS, String.valueOf(totalItens), false);
            return;
        }

        if (requestURI.contains("/itens-cardapio/")) {
            String[] itensCardapioChunks = requestURI.split("/");

            if (itensCardapioChunks.length == 3) {

                Long id = null;
                try {
                    id = Long.parseLong(itensCardapioChunks[2]);
                } catch (Exception ex) {
                    System.out.println("Erro ao converter id.");
                }

                if (!Objects.isNull(id)) {
                    System.out.println("Chamou listagem de itens de cardápio por id");

                    Optional<ItemCardapio> opt = database.itemCardapioPorId(id);
                    Gson gson = new Gson();
                    String json = "";
                    if (opt.isPresent()) {
                        json = gson.toJson(opt.get());
                    }

                    ResponseFactory.ok(clientOS, json, true);
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

package mx.florinda.cardapio.desafio;

import java.io.OutputStream;
import java.math.BigDecimal;

public class TrataRequisicaoPatch extends TrataRequisicaoBase {

    @Override
    protected void trataUri(OutputStream clientOS, String requestURI, String[] requestChunks) {
        if (requestURI.contains("/itens-cardapio/")) {
            String[] itensCardapioChunks = requestURI.split("/");

            if (itensCardapioChunks.length == 3) {
                System.out.println("Chamou alteração de item de cardápio");

                String idComParametros = itensCardapioChunks[2];
                if (idComParametros.contains("?novoPreco=")) {
                    String[] idComParametrosChunks = idComParametros.split("\\?");

                    Long id = Long.parseLong(idComParametrosChunks[0]);
                    String parametroNovoPreco = idComParametrosChunks[1];
                    String[] parametroNovoPrecoChunks = parametroNovoPreco.split("=");
                    BigDecimal novoPreco = new BigDecimal(parametroNovoPrecoChunks[1]);

                    if (database.alteraPrecoItemCardapio(id, novoPreco)) {
                        ResponseFactory.ok(clientOS, "", false);
                        return;
                    } else {
                        ResponseFactory.internalServerError(clientOS);
                        return;
                    }
                }
            }
        }
        ResponseFactory.notFound(clientOS);
    }
}

package mx.florinda.cardapio.desafio;

import java.io.OutputStream;

public abstract class TrataRequisicaoBase implements TrataRequisicao {

    public final ItemCardapioDAO database;

    public TrataRequisicaoBase() {
        database = new ItemCardapioDAOJdbc();
    }

    public void tratar(String requestURI, OutputStream clientOS, String[] requestChunks) {
        try {
            trataUri(clientOS, requestURI, requestChunks);
        } catch (Exception ex) {
            ResponseFactory.internalServerError(clientOS);
        }
    }

    protected abstract void trataUri(OutputStream clientOS, String requestURI, String[] requestChunks);
}

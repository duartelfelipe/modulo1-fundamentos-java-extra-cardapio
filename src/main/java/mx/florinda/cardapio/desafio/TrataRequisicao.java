package mx.florinda.cardapio.desafio;

import java.io.OutputStream;

public interface TrataRequisicao {

    void tratar(String requestURI, OutputStream clientOS, String[] requestChunks);

    static TrataRequisicao criar(String method) {
        return MethodEnum.valueOf(method).getTrataRequisicao();
    }
}

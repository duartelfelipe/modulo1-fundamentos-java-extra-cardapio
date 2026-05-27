package mx.florinda.cardapio.desafio;

public enum MethodEnum {
    GET(new TrataRequisicaoGet()),
    POST(new TrataRequisicaoPost()),
    DELETE(new TrataRequisicaoDelete()),
    PATCH(new TrataRequisicaoPatch());

    private final TrataRequisicao trataRequisicao;

    MethodEnum(TrataRequisicao trataRequisicao) {
        this.trataRequisicao = trataRequisicao;
    }

    public TrataRequisicao getTrataRequisicao() {
        return trataRequisicao;
    }
}

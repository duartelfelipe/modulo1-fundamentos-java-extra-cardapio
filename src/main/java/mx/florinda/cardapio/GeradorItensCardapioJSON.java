package mx.florinda.cardapio;

import com.google.gson.Gson;
import mx.florinda.cardapio.desafio.ItemCardapioDAO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class GeradorItensCardapioJSON {

    public static void main(String[] args) throws IOException {
        ItemCardapioDAO database = new InMemoryDatabase();
        List<ItemCardapio> listaItensCardapio = database.listaItensCardapio();

        Gson gson = new Gson();
        String json = gson.toJson(listaItensCardapio);

        Path path = Path.of("itensCardapio.json");
        Files.writeString(path, json);

    }

}

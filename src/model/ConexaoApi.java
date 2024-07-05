package model;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class ConexaoApi {
    private final String base_code;
    private final String target_code;
    private final double amount;

    public ConexaoApi(Conversor valores) {
        this.base_code = valores.moedaEscolhida();
        this.target_code = valores.moedaAConverter();
        this.amount = valores.valorCotacao();
    }

    public String ConsumoApi() {
        String apikEY = "77c85ff01d1ee5d9f45b5881";
        String url = STR."https://v6.exchangerate-api.com/v6/\{apikEY}/pair/\{base_code}/\{target_code}/\{amount}";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException();
        }
        var json = response.body();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Conversor conversor = gson.fromJson(json, Conversor.class);
        String mensagemUsuario = STR."Resposta: \{amount} \{conversor.moedaEscolhida()} equivalem a \{conversor.valorCotacao()} \{conversor.moedaAConverter()}";
        return mensagemUsuario;
    }

}


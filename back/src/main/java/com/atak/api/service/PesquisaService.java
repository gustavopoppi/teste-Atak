package com.atak.api.service;

import com.atak.api.dto.DadosPesquisa;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Service
public class PesquisaService {

    public DadosPesquisa buscaGoogle(String termoBusca) throws InterruptedException {
        try {
            HttpResponse<String> response = getResponse(termoBusca);
            return getDadosPesquisaDaRequisicao(response);
        }
        catch (IOException ex){
            throw new RuntimeException(ex);
        }
    }

    private static HttpResponse<String> getResponse(String nome) throws IOException, InterruptedException {
        HttpRequest request = buildHttpRequest(nome);
        HttpClient client = buildHttpClient();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private static HttpRequest buildHttpRequest(String nome) {
        String REQUISICAO_BUSCA_GOOGLE = "https://www.google.com.br/search?q=";

        return HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(REQUISICAO_BUSCA_GOOGLE + nome))
                .timeout(Duration.ofSeconds(3))
                .build();
    }

    private static HttpClient buildHttpClient() {
        return HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(3))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
    }

    private DadosPesquisa getDadosPesquisaDaRequisicao(HttpResponse<String> response) {
        String respostaRequisicao = response.body();
        return new DadosPesquisa(getLinkRequisicao(respostaRequisicao), getTituloRequisicao(respostaRequisicao));
    }

    private static String getTituloRequisicao(String respostaRequisicao) {
        String PADRAO_NOMENCLATURA_TITULO_SITE_GOOGLE = "<div class=\\\"yuRUbf\\\"><a href=\\";

        int primeiraParteTitulo = respostaRequisicao.indexOf(PADRAO_NOMENCLATURA_TITULO_SITE_GOOGLE) + PADRAO_NOMENCLATURA_TITULO_SITE_GOOGLE.length();
        int ultimaParteTitulo = respostaRequisicao.indexOf("\\\" jsname=\\\"ACyKwe\\\"", primeiraParteTitulo);

        return respostaRequisicao.substring(primeiraParteTitulo, ultimaParteTitulo);
    }

    private static String getLinkRequisicao(String respostaRequisicao) {

        String PADRAO_NOMENCLATURA_LINK_SITE_GOOGLE = "<h3 class=\\\"LC20lb MBeuO DKV0Md\\\">";

        int primeiraParteLink = respostaRequisicao.indexOf(PADRAO_NOMENCLATURA_LINK_SITE_GOOGLE) + PADRAO_NOMENCLATURA_LINK_SITE_GOOGLE.length();
        int ultimaParteLink = respostaRequisicao.indexOf("</h3>", primeiraParteLink);

        return respostaRequisicao.substring(primeiraParteLink, ultimaParteLink);
    }
}
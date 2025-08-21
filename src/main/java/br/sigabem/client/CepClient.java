package br.sigabem.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CepClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public CepResponse buscar(String cep) {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        return restTemplate.getForObject(url, CepResponse.class);
    }
}
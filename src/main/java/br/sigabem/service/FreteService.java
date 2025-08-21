package br.sigabem.service;

import br.sigabem.client.CepClient;
import br.sigabem.client.CepResponse;
import br.sigabem.dto.FreteRequestDto;
import br.sigabem.dto.FreteResponseDto;
import br.sigabem.exception.CepNotFoundException;
import br.sigabem.model.Cotacao;
import br.sigabem.repository.CotacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class FreteService {

    @Autowired
    private CepClient cepClient;

    @Autowired
    private FreteCalculator calculator;

    @Autowired
    private CotacaoRepository repository;

    public FreteResponseDto calcularFrete(FreteRequestDto req) {

        CepResponse origem = cepClient.buscar(req.getCepOrigem());
        CepResponse destino = cepClient.buscar(req.getCepDestino());

        if (origem == null || destino == null) {
            throw new CepNotFoundException("CEP de origem ou destino inválido.");
        }

        double valor = calculator.calcular(req.getPeso(), origem.getUf(), destino.getUf());

        Cotacao cotacao = new Cotacao();
        cotacao.setNomeDestinatario(req.getNomeDestinatario());
        cotacao.setCepOrigem(req.getCepOrigem());
        cotacao.setCepDestino(req.getCepDestino());
        cotacao.setPeso(req.getPeso());
        cotacao.setVlTotalFrete(valor);
        cotacao.setDataPrevistaEntrega(LocalDate.now().plusDays(1));
        cotacao.setDataConsulta(LocalDate.now());

        repository.save(cotacao);

        FreteResponseDto response = new FreteResponseDto();
        response.setCepOrigem(req.getCepOrigem());
        response.setCepDestino(req.getCepDestino());
        response.setVlTotalFrete(valor);
        response.setDataPrevistaEntrega(LocalDate.now().plusDays(1));

        return response;
    }
}
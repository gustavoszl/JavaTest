package br.sigabem.service;

import org.springframework.stereotype.Component;

@Component
public class FreteCalculator {

    public double calcular(double peso, String ufOrigem, String ufDestino) {
        double desconto = 0.0;

        if (ufOrigem.equalsIgnoreCase(ufDestino)) {
            desconto = 0.5;
        } else if (ufOrigem.substring(0, 1).equalsIgnoreCase(ufDestino.substring(0, 1))) {
            desconto = 0.75;
        }

        double valorFrete = peso * 1.00;
        return valorFrete - (valorFrete * desconto);
    }
}
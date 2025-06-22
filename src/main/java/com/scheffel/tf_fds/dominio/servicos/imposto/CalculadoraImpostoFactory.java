package com.scheffel.tf_fds.dominio.servicos.imposto;

import org.springframework.stereotype.Component;

@Component
public class CalculadoraImpostoFactory {
    public CalculadoraImposto getCalculadora(String estado) {
        switch (estado.toUpperCase()) {
            case "RS":
                return new CalculadoraImpostoRS();
            case "SP":
                return new CalculadoraImpostoSP();
            case "PE":
                return new CalculadoraImpostoPE();
            default:
                throw new IllegalArgumentException("Estado não atendido pela transportadora: " + estado);
        }
    }
}
package com.eventoapp.UseCases;

import com.eventoapp.Entity.Evento;

import org.springframework.stereotype.Component;

@Component
// Implementando a classe de ValoresDiasDaSemana usando Strategy
public class ValoresPorDiasDaSemana implements ValoresPorDiasDaSemanaImplements {
    private final double VALOR_INICIO_SEMANA = 400.00;
    private final double VALOR_MEIO_SEMANA = 550.00;
    private final double VALOR_FIM_SEMANA = 900.00;

    @Override
    public double valor(Evento evento) {
        if (evento.getDiaDaSemana().equalsIgnoreCase("Segunda") || evento.getDiaDaSemana().equalsIgnoreCase("Terça")) {
            return VALOR_INICIO_SEMANA;
        }
        if (evento.getDiaDaSemana().equalsIgnoreCase("Quarta") || evento.getDiaDaSemana().equalsIgnoreCase("Quinta")
                || evento.getDiaDaSemana().equalsIgnoreCase("Sexta")) {
            return VALOR_MEIO_SEMANA;
        }
        if (evento.getDiaDaSemana().equalsIgnoreCase("Sabado") || evento.getDiaDaSemana().equalsIgnoreCase("Domingo")) {
            return VALOR_FIM_SEMANA;
        } else {
            return 0;
        }
    }
}
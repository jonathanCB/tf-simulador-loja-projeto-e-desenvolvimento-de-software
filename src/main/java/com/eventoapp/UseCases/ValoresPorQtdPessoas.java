package com.eventoapp.UseCases;

import com.eventoapp.Entity.Evento;

import org.springframework.stereotype.Component;

@Component
// Implementando a classe de ValoresQtdPessoas usando Strategy
public class ValoresPorQtdPessoas implements ValoresPorQtdPessoasImplements {
    private final double POUCAS_PESSOAS = 350.00;
    private final double MEDIO_NPESSOAS = 550.00;
    private final double MUITAS_PESSOAS = 800.00;

    @Override
    public double valor(Evento evento) {
        if (evento.getQtdPessoas() > 0 && evento.getQtdPessoas() < 100) {
            return POUCAS_PESSOAS;
        }
        if (evento.getQtdPessoas() >= 100 && evento.getQtdPessoas() < 350) {
            return MEDIO_NPESSOAS;
        }
        if (evento.getQtdPessoas() >= 350) {
            return MUITAS_PESSOAS;
        } else {
            return 0;
        }
    }
}
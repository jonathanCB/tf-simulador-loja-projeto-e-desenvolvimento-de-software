package com.eventoapp.UseCases;

import com.eventoapp.Entity.Evento;

import org.springframework.stereotype.Component;

@Component
// Implementando a classe de Desconto usando Strategy
public class DescontoPromocional implements Desconto {
    public static final double TAXA = 0.10;

    @Override
    public double desconto(Evento evento) {
            return evento.getCustoDoEvento() * TAXA;
    }
}
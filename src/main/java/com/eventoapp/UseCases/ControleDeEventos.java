package com.eventoapp.UseCases;

import com.eventoapp.Entity.Evento;
import com.eventoapp.Interfaces.EventoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ControleDeEventos {
    private EventoRepository listaEventos;
    private Desconto desconto;
    private ValoresPorDiasDaSemanaImplements valorPorDiaSemana;
    private ValoresPorQtdPessoasImplements valorPorQtdPessoas;
    private Evento evento;

    @Autowired
    public ControleDeEventos(EventoRepository listaEventos, Desconto desconto,
            ValoresPorDiasDaSemanaImplements valoresDiaSemana, ValoresPorQtdPessoasImplements valoresQtdPessoas) {
        this.listaEventos = listaEventos;
        this.desconto = desconto;
        this.valorPorDiaSemana = valoresDiaSemana;
        this.valorPorQtdPessoas = valoresQtdPessoas;
        this.evento = null;
    }

    public Evento saveEvento(Evento evento) {
        // Salvando o evento no banco de dados:
        listaEventos.save(evento);

        /*
         * Setando o valor do evento de acordo com o dia da semana escolhido: OBS: para
         * fazermos isso, estamos chamando o método setValoresPorDiasDaSemana() passando
         * outro método, o getValoresPorDiasDaSemana() passando o código do evento para
         * chamar OUTRO método, o método valor(), agora da classe
         * ValoresPorDiasDaSemana.
         */
        evento.setValoresPorDiaDaSemana(calculaValorPorDiaDaSemana(evento.getCodigo()));

        /*
         * Setando o valor do evento de acordo com a quantidade de pessoas: OBS: para
         * fazermos isso, estamos chamando o método setValoresPorQtdDePessoas() passando
         * outro método, o getValorPorQtdDePessoas() passando o código do evento para
         * chamar OUTRO método, o método valor(), agora da classe ValoresPorQtdPessoas.
         */
        evento.setValoresPorQtdDePessoas(calculaValorPorQtdDePessoas(evento.getCodigo()));

        /*
         * Setando o custo total do evento (sem aplicar o desconto): OBS: para fazermos
         * isso, estamos chamando o método getValorTotalEvento().
         */
        evento.setCustoDoEvento(calculaValorTotalDoEvento());

        /*
         * Setando o valor do desconto (se houver). OBS: para fazermos isso, estamos
         * chamando o método setDesconto() passando o método getDesconto() e passando o
         * código do evento.
         */
        evento.setDesconto(calculaDesconto(evento.getCodigo()));

        /*
         * Aqui estamos atualizando o custo total do evento com o desconto já aplicado.
         */
        evento.setCustoDoEvento(calculaValorTotalDoEvento());

        /*
         * Por fim, estamos atualizando todos os custos do evento com os descontos já
         * aplicados.
         */
        listaEventos.save(evento);
        return evento;
    }

    private boolean validaAtributo(int codigo) {
        if (codigo > 0) {
            return true;
        }
        return false;
    }

    public Evento getEvento(int codigo) {
        if (validaAtributo(codigo)) {

            Evento newEvento = listaEventos.findByCodigo(codigo);

            if (newEvento != null) {
                this.evento = newEvento;
                return newEvento;
            } else {
                throw new IllegalArgumentException("Evento nao encontrado.");
            }
        }
        throw new IllegalArgumentException("O codigo tem que ser maior que zero");
    }

    public double calculaDesconto(int codigo) {
        if (validaAtributo(codigo)) {
            if (this.evento == null || this.evento.getCodigo() != codigo) {
                this.evento = getEvento(codigo);
            }
            return this.desconto.desconto(this.evento);
        }
        throw new IllegalArgumentException("O codigo tem que ser maior que zero");
    }

    public double calculaValorPorDiaDaSemana(int codigo) {
        if (validaAtributo(codigo)) {
            if (this.evento == null || this.evento.getCodigo() != codigo) {
                this.evento = getEvento(codigo);
            }
            return this.valorPorDiaSemana.valor(evento);
        }
        throw new IllegalArgumentException("O codigo tem que ser maior que zero");
    }

    public double calculaValorPorQtdDePessoas(int codigo) {
        if (validaAtributo(codigo)) {
            if (this.evento == null || this.evento.getCodigo() != codigo) {
                this.evento = getEvento(codigo);
            }
            return this.valorPorQtdPessoas.valor(evento);
        }
        throw new IllegalArgumentException("O codigo tem que ser maior que zero");
    }

    public double calculaValorTotalDoEvento() {
        double valorTotalDoEvento = 0;
        if ((this.valorPorDiaSemana.valor(evento) > 0) && (this.valorPorQtdPessoas.valor(evento) > 0) && (this.evento.getDescontoPromocional().equals("sim"))) {
            valorTotalDoEvento = (this.valorPorDiaSemana.valor(this.evento) + this.valorPorQtdPessoas.valor(this.evento)
                    - this.desconto.desconto(evento));
        } else {
            valorTotalDoEvento = (this.valorPorDiaSemana.valor(this.evento) + this.valorPorQtdPessoas.valor(this.evento)
                    + this.desconto.desconto(evento));
        } 
        return valorTotalDoEvento;
    }
}
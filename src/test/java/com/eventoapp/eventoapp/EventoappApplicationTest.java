package com.eventoapp.eventoapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.eventoapp.Entity.Evento;
import com.eventoapp.UseCases.Desconto;
import com.eventoapp.UseCases.DescontoPromocional;
import com.eventoapp.UseCases.ValoresPorDiasDaSemana;
import com.eventoapp.UseCases.ValoresPorDiasDaSemanaImplements;
import com.eventoapp.UseCases.ValoresPorQtdPessoas;
import com.eventoapp.UseCases.ValoresPorQtdPessoasImplements;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EventoappApplicationTest {
    private Evento evento;
    private Desconto desconto;
    private ValoresPorDiasDaSemanaImplements valorPeloDiaDaSemana;
    private ValoresPorQtdPessoasImplements valorPorQtdDePessoas;

    // Setando valores padrão na criação do objeto Evento para uso em todos os
    // testes.
    private String nomeEvento = "Teste";
    private String descontoPromocional = "nao";
    private String diaDaSemana = "Segunda";
    private int qtdPessoas = 0;

    @BeforeEach
    public void setup() {
        this.desconto = new DescontoPromocional();
        this.valorPeloDiaDaSemana = new ValoresPorDiasDaSemana();
        this.valorPorQtdDePessoas = new ValoresPorQtdPessoas();
    }

    // Fazendo testes parametrizados para a classe DescontoPromocional:
    @ParameterizedTest
    // Setando os valores:
    @CsvSource({ "sim, 0.00, 0.00", "sim, 50.00, 5.00", "sim, 1000.00, 100.00", "sim, 2000.00, 200.00" })
    public void testeComDesconto(String descontoPromocional, double custoTotalEvento, double resp) {
        // Criando o evento com desconto promocional como "sim":
        this.evento = new Evento(this.nomeEvento, this.qtdPessoas, this.diaDaSemana, descontoPromocional);
        // Setando um custo total fixo para o evento:
        this.evento.setCustoDoEvento(custoTotalEvento);
        // Testando a classe DescontoPromocional.java:
        // valorTaxa sera um desconto ou uma taxa a mais no custo total do evento.
        double valorDesconto = desconto.desconto(this.evento);
        // Comparando os resultados:
        assertEquals(resp, valorDesconto);
    }

    // Fazendo testes parametrizados eventos sem desconto, onde o custo total aumentara 10%.
    @ParameterizedTest
    @CsvSource({ "nao, 0.00, 0.00", "nao, 50.00, 5.00", "nao, 1000.00, 100.00", "nao, 2000.00, 200.00" })
    public void testeSemDesconto(String descontoPromocional, double custoTotalEvento, double resp) {
        // Criando o evento com desconto promocional como "nao":
        this.evento = new Evento(this.nomeEvento, this.qtdPessoas, this.diaDaSemana, descontoPromocional);
        this.evento.setCustoDoEvento(custoTotalEvento);
        double valorTaxa = desconto.desconto(this.evento);
        assertEquals(resp, valorTaxa);
    }

    // Fazendo testes parametrizados para a classe ValoresPorDiaDeSemana:
    @ParameterizedTest
    @CsvSource({ "Segunda, 400.00", "Terça, 400.00", "Quarta, 550.00", "Quinta, 550.00", "Sexta, 550.00",
            "Sabado, 900.00", "Domingo, 900.00", "QualquerCoisa, 0.00" })
    public void testePorDiaDaSemana(String diaSemana, double resp) {
        // Criando um evento:
        this.evento = new Evento(this.nomeEvento, this.qtdPessoas, diaSemana, this.descontoPromocional);
        double valorPeloDiaDaSemanaEscolhido = this.valorPeloDiaDaSemana.valor(this.evento);
        assertEquals(resp, valorPeloDiaDaSemanaEscolhido);
    }

    // Fazendo testes parametrizados para a classe ValoresPorQtdDePessoas:
    @ParameterizedTest
    @CsvSource({ "1, 350.00", "50, 350.00", "99, 350.00", "100, 550.00", "200, 550.00", "349, 550.00", "350, 800.00",
            "500, 800.00", "1000, 800.00", "0, 0.00", "-1, 0.00" })
    public void testePorQtdPessoas(int qtdPessoas, double resp) {
        // Criando um evento:
        this.evento = new Evento(this.nomeEvento, qtdPessoas, this.diaDaSemana, this.descontoPromocional);
        double valorPorQtdDePessoas = this.valorPorQtdDePessoas.valor(this.evento);
        assertEquals(resp, valorPorQtdDePessoas);
    }

}
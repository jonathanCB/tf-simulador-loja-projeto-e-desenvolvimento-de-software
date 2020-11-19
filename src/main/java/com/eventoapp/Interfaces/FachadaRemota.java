package com.eventoapp.Interfaces;

import com.eventoapp.Entity.Evento;
import com.eventoapp.Entity.OrgBeneficente;
import com.eventoapp.UseCases.ControleDeEventos;
import com.eventoapp.UseCases.ControleOrgBeneficentes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/detalhesevento")
public class FachadaRemota {
    private ControleDeEventos controleEventos;
    private ControleOrgBeneficentes controleOrgBeneficentes;

    @Autowired
    public FachadaRemota(ControleDeEventos controleEventos, ControleOrgBeneficentes controleOrgBeneficentes) {
        this.controleEventos = controleEventos;
        this.controleOrgBeneficentes = controleOrgBeneficentes;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/persisteevento")
    public Evento recebeEvento(@RequestParam String nomeEvento, @RequestParam int qtdPessoas,
            @RequestParam String diaSemana, @RequestParam String descPromocional) {
        return controleEventos.saveEvento(new Evento(nomeEvento, qtdPessoas, diaSemana, descPromocional));
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/persisteorgbeneficente")
    public OrgBeneficente recebeOrgBeneficente(@RequestParam String nomeOrgBeneficente) {
        return controleOrgBeneficentes.saveOrgBeneficente(new OrgBeneficente(nomeOrgBeneficente));
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/dadosevento")
    public Evento getDadosEvento(@RequestParam int codigo) {
        return controleEventos.getEvento(codigo);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/dadosorgbeneficente")
    public OrgBeneficente getDadosOrgBeneficente(@RequestParam int codigo) {
        return controleOrgBeneficentes.getOrgBeneficente(codigo);
    }

    // TO-DO versão 2.0
    // @CrossOrigin(origins = "*")
    // @GetMapping("/dadoseventoall")
    // public List<Evento> getDadosEvento() {
    // return sCalculaValores.getEvento();
    // }

    @CrossOrigin(origins = "*")
    @GetMapping("/desconto")
    public double getValorDesconto(@RequestParam int codigo) {
        return controleEventos.calculaDesconto(codigo);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/valorpordiadasemana")
    public double getValorPorDiaDaSemana(@RequestParam int codigo) {
        return controleEventos.calculaValorPorDiaDaSemana(codigo);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/valorporqtddepessoas")
    public double getValorPorQtdDePessoas(@RequestParam int codigo) {
        return controleEventos.calculaValorPorQtdDePessoas(codigo);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/valortotaldoevento")
    public double getValorTotalDoEvento(@RequestParam int codigo) {
        return controleEventos.calculaValorTotalDoEvento();
    }
}
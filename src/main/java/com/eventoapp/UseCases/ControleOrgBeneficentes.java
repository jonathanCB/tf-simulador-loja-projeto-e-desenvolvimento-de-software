package com.eventoapp.UseCases;

import com.eventoapp.Entity.OrgBeneficente;
import com.eventoapp.Interfaces.OrgBeneficenteRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ControleOrgBeneficentes {
    private OrgBeneficenteRepository listaOrgBeneficentes;
    private OrgBeneficente orgBeneficente;

    @Autowired
    public ControleOrgBeneficentes(OrgBeneficenteRepository listaOrgBeneficentes) {
        this.listaOrgBeneficentes = listaOrgBeneficentes;
        this.orgBeneficente = null;
    }

    public OrgBeneficente saveOrgBeneficente(OrgBeneficente orgBeneficente) {
        // Salvando o Organizacao beneficente no banco de dados:
        listaOrgBeneficentes.save(orgBeneficente);
        return orgBeneficente;
    }

    private boolean validaAtributo(int codigo) {
        if (codigo > 0) {
            return true;
        }
        return false;
    }

    public OrgBeneficente getOrgBeneficente(int codigo) {
        if (validaAtributo(codigo)) {

            OrgBeneficente newOrgBeneficente = listaOrgBeneficentes.findByCodigo(codigo);

            if (newOrgBeneficente != null) {
                this.orgBeneficente = newOrgBeneficente;
                return newOrgBeneficente;
            } else {
                throw new IllegalArgumentException("Organizacao beneficente nao encontrada.");
            }
        }
        throw new IllegalArgumentException("O codigo tem que ser maior que zero.");
    }

}
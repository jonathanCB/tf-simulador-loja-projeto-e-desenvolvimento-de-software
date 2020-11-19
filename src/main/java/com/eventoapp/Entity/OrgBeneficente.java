package com.eventoapp.Entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrgBeneficente implements Serializable {

    // Necessário para a classe Serializable funcionar:
    private static final long serialVersionUID = 1L;

    // Definindo id para o banco de dados com "@Id":
    @Id
    // Gerando o id automaticamente com "@GeneratedValue":
    // OBS: Para funcionar tempos que implementar a classe "Serializable" ↑.
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int codigo;

    private String nomeOrgBeneficente;

    public OrgBeneficente(){
        
    }

    public OrgBeneficente(String nomeOrgBeneficente){
        this.nomeOrgBeneficente = nomeOrgBeneficente;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNomeOrgBeneficente() {
        return nomeOrgBeneficente;
    }

    public void setNomeOrgBeneficente(String nomeOrgBeneficente) {
        this.nomeOrgBeneficente = nomeOrgBeneficente;
    }

    
}
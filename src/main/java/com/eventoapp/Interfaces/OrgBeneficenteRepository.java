package com.eventoapp.Interfaces;

import com.eventoapp.Entity.OrgBeneficente;

import org.springframework.data.repository.CrudRepository;

public interface OrgBeneficenteRepository extends CrudRepository<OrgBeneficente, String>{
    OrgBeneficente findByCodigo(int codigo);
}
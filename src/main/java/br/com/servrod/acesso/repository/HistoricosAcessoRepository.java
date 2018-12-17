/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.servrod.acesso.repository;

import br.com.servrod.acesso.model.HistoricosAcesso;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author rodrigo
 */
public interface HistoricosAcessoRepository extends MongoRepository<HistoricosAcesso, String> {
    
    
}

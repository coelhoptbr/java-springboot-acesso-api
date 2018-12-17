/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.servrod.acesso.repository;

import br.com.servrod.acesso.model.Usuarios;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 *
 * @author rodrigo
 */
public interface UsuariosRepository extends MongoRepository<Usuarios, String> {
    
    Usuarios findBy_id(ObjectId _id);
    
    @Query(value = "{ 'email' : ?0 }")
    List<Usuarios> buscarPorEmail(String email);

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ibero.crudmongodb.api;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.ibero.crudmongodb.model.Persona;
/**
 *
 * @author cdmrr
 */
public interface PersonaRepository extends MongoRepository<Persona, Long>{
    
}

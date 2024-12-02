/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ibero.crudmongodb.service;

import com.ibero.crudmongodb.model.Persona;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author cdmrr
 */
public interface PersonaService {
    
    List<Persona> getAllPersonas();
    Optional<Persona>getPersonaById(Long id);
    Persona savePersona(Persona persona);
    void deletePersonaById(Long id);
}

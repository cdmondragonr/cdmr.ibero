/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ibero.crudmongodb.service;

import com.ibero.crudmongodb.api.PersonaRepository;
import com.ibero.crudmongodb.model.Persona;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author cdmrr
 */

@Service
public class PersonaServiceImpl implements PersonaService{
    
    @Autowired
    private PersonaRepository personaRepository;
    
    @Override
    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }

    @Override
    public Optional<Persona> getPersonaById(Long id) {
        return personaRepository.findById(id);
    }

    @Override
    public Persona savePersona(Persona persona) {
        return personaRepository.save( persona);
    }

    @Override
    public void deletePersonaById(Long id) {
        personaRepository.deleteById(id);
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ibero.crudmongodb.controller;

import com.ibero.crudmongodb.model.Persona;
import com.ibero.crudmongodb.service.PersonaService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author cdmrr
 */
@RestController
@RequestMapping("/api/personas")
public class PersonaController {
    
    @Autowired
    private PersonaService personaService;
    
    @GetMapping("all")
    public ResponseEntity<List<Persona>> getAllPersonas(){
        List<Persona> personas = personaService.getAllPersonas();    
        return ResponseEntity.ok().body(personas);
    }    
    
    @GetMapping("/byid/{id}")
    public ResponseEntity<Persona> getPersonaById(@PathVariable Long id){
        Optional<Persona> persona=personaService.getPersonaById(id);  
        if(persona.isPresent()){
            return ResponseEntity.ok().body(persona.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/Save")
    public ResponseEntity<Persona> savePesona(@RequestBody @Validated Persona persona){
        Persona savedPersona = personaService.savePersona(persona);    
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPersona);
    }

    
    @PutMapping("/update/{id}")
    public ResponseEntity<Persona> updatePersona(@PathVariable Long id, @RequestBody @Validated Persona persona){
        Optional<Persona> personaOptional=personaService.getPersonaById(id);  
        if(personaOptional.isPresent()){
            persona.setId(id);
            Persona updatedPersona = personaService.savePersona(persona);
            return ResponseEntity.ok().body(updatedPersona);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePersonaById(@PathVariable Long id){
        personaService.deletePersonaById(id);
        return ResponseEntity.noContent().build();
    }
}

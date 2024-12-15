/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.model.User;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author cdmrr
 */
public interface UserRepository extends MongoRepository<User, String>{
    
    Optional<User> findByEmail (String email);
    boolean existByEmail(String email);
}

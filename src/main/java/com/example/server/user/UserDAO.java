/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.server.user;

import org.springframework.stereotype.Repository;

/**
 *
 * @author User
 */
public interface UserDAO {

    User create(User user);

    User findByID(long id);
    
    User findByUserName(String name);

    void update(User user);

    void delete(User user);
}

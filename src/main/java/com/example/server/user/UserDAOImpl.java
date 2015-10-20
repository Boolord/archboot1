/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.server.user;

import javax.persistence.EntityManagerFactory;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author User
 */
@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    EntityManagerFactory emFactory;

    @Override
    public User create(User user) {
        Session session = emFactory.createEntityManager().unwrap(Session.class);
        user.setPassword(encoder.encode(user.getPassword()));
        session.save(user);
        if (session.isOpen()) {
            session.flush();
            session.close();
        }
        return user;
    }

    @Override
    public User findByID(long id) {
        Session session = emFactory.createEntityManager().unwrap(Session.class);
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("id", id));
        User result = (User) criteria.uniqueResult();
        Hibernate.initialize(result);
        if (session.isOpen()) {
            session.flush();
            session.close();
        }
        return result;
    }

    @Override
    public User findByUserName(String name) {
        Session session = emFactory.createEntityManager().unwrap(Session.class);
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("username", name));
        User result = (User) criteria.uniqueResult();
        Hibernate.initialize(result);
        if (session.isOpen()) {
            session.flush();
            session.close();
        }
        return result;
    }

    @Override
    public void update(User user) {
        Session session = emFactory.createEntityManager().unwrap(Session.class);
        session.update(user);
        if (session.isOpen()) {
            session.flush();
            session.close();
        }
    }

    @Override
    public void delete(User user) {
        Session session = emFactory.createEntityManager().unwrap(Session.class);
        session.delete(user);
        if (session.isOpen()) {
            session.flush();
            session.close();
        }
    }

}

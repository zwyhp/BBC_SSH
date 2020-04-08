package com.ssh.bbc.user.Repository.impl;

import com.ssh.bbc.user.Repository.TuserRepository;
import com.ssh.bbc.user.domain.Tuser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.xml.registry.infomodel.User;
import java.util.List;

@Repository
public class TuserRepositoryImpl implements TuserRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public TuserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public int deleteTuserById(int userId) {
        Tuser tuser = queryTuserByID(userId);
        currentSession().delete(tuser);
        return userId;
    }

    @Override
    public List<Tuser> queryTUserByPage(int pageNum,int pageSize,String query) {
        String sql = "SELECT * FROM tuser where USerName like :name";
        List users = currentSession().createNativeQuery(sql)
                .addEntity(Tuser.class)
                .setParameter("name", "%"+query+"%")
                .setFirstResult((pageNum - 1)*pageSize)
                .setMaxResults(pageSize)
                .list();
        return (List<Tuser>)users;
    }
    @Override
    public List<Tuser> queryTUserByname(String query) {
        String sql = "SELECT * FROM tuser where UserName like :name";
        List users = currentSession().createNativeQuery(sql)
                .addEntity(Tuser.class)
                .setParameter("name", "%"+query+"%")
                .list();
        return (List<Tuser>)users;
    }



    @Override
    public int addTUser(Tuser user) {
        return (int)currentSession().save(user);
    }

    @Override
    public int updateTuser(Tuser user) {
        currentSession().update(user);
        return user.getUserId();
    }

    @Override
    public Tuser queryTuserByName(String name) {
        String sql = "SELECT * FROM tuser where USerName = :name";
        List users = currentSession().createNativeQuery(sql)
                .addEntity(Tuser.class)
                .setParameter("name", name)
                .list();
        return users.isEmpty() ? null : (Tuser) users.get(0);
    }

    @Override
    public Tuser queryTuserByEmail(String email) {
        String sql = "SELECT * FROM tuser where USerEmail = :email";
        List users = currentSession().createNativeQuery(sql)
                .addEntity(Tuser.class)
                .setParameter("email", email)
                .list();
        return users.isEmpty() ? null : (Tuser) users.get(0);
    }

    @Override
    public Tuser queryTuserByID(int userId) {
        return currentSession().get(Tuser.class, userId);
    }
}

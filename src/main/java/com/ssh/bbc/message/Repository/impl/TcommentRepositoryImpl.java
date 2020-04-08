package com.ssh.bbc.message.Repository.impl;

import com.ssh.bbc.message.Repository.TcommentRepository;
import com.ssh.bbc.message.domain.Tcomment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TcommentRepositoryImpl implements TcommentRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public TcommentRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }
    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public int addTcomment(Tcomment tcomment) {
        return (int)currentSession().save(tcomment);
    }

    @Override
    public int updateTcommentById(Tcomment tcomment) {
        currentSession().update(tcomment);
        return tcomment.getCommentId();
    }

    @Override
    public int deleteTcommentById(int commentId) {
        Tcomment tcomment = queryTcommentById(commentId);
        currentSession().delete(tcomment);
        return commentId;
    }

    @Override
    public List<Tcomment> queryTcommentByPage(int pageNum,int pageSize,int messageId) {
        String sql = "SELECT * FROM Tcomment where messageId = :messageId";
        List list = currentSession().createNativeQuery(sql)
                .addEntity(Tcomment.class)
                .setParameter("messageId", messageId)
                .setFirstResult((pageNum - 1)*pageSize)
                .setMaxResults(pageSize)
                .list();
        return (List<Tcomment>)list;
    }

    @Override
    public List<Tcomment> queryTcommentAll(int messageId) {
        String sql = "SELECT * FROM Tcomment where messageId = :messageId";
        List list = currentSession().createNativeQuery(sql)
                .addEntity(Tcomment.class)
                .setParameter("messageId", messageId)
                .list();
        return (List<Tcomment>)list;
    }

    @Override
    public Tcomment queryTcommentById(int commentId) {
        return currentSession().get(Tcomment.class,commentId);
    }
}

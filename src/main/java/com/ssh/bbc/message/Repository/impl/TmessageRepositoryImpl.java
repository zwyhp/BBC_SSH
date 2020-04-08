package com.ssh.bbc.message.Repository.impl;

import com.ssh.bbc.message.Repository.TmessageRepository;
import com.ssh.bbc.message.domain.Tmessage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TmessageRepositoryImpl implements TmessageRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public TmessageRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }
    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public int addTmessage(Tmessage tmessage) {
        return (int)currentSession().save(tmessage);
    }

    @Override
    public int updateTmessageById(Tmessage tmessage) {
        currentSession().update(tmessage);
        return tmessage.getMessageId();
    }

    @Override
    public int deleteTmessageById(int messageId) {
        Tmessage tmessage = queryTmessageById(messageId);
        currentSession().delete(tmessage);
        return tmessage.getMessageId();
    }

    @Override
    public Tmessage queryTmessageById(int messageId) {
        return currentSession().get(Tmessage.class,messageId);
    }

    @Override
    public List<Tmessage> queryTmessageTop10(int pageNum, int pageSize, String query) {
        List list = null;
        String sql = "";
        if (query != null && !query.equals("")){
             sql = "SELECT * FROM Tmessage where Messkind = :messkind  order by ClickNumber desc ";
        }else{
             sql = "SELECT * FROM Tmessage order by ClickNumber desc ";
        }
        NativeQuery nativeQuery = currentSession().createNativeQuery(sql)
                .addEntity(Tmessage.class)
                .setFirstResult((pageNum - 1)*pageSize)
                .setMaxResults(pageSize);
        if (query != null && !query.equals("")){
            list = nativeQuery.setParameter("messkind", query).list();
        }else {
            list = nativeQuery.list();
        }
        return (List<Tmessage>)list;
    }

    @Override
    public List<Tmessage> queryTmessageByPage(int pageNum, int pageSize, String query, String plate) {
        List list = null;
        String sql = "";
        String order = "order by istop desc, MessageTime desc ";
        sql = queryMessageSql(order,plate);
        return (List<Tmessage>)getPageList(pageNum,pageSize,query,plate,sql);
    }
    @Override
    public List<Tmessage> queryTmessageByAll(String query, String plate) {
        List list = null;
        String sql = "";
        String order = "order by istop desc, MessageTime desc ";
        sql = queryMessageSql(order,plate);
        return (List<Tmessage>)getAllList(query,plate,sql);
    }

    @Override
    public List<Tmessage> queryNotPassMessageByPage(int pageNum, int pageSize, String query, String plate) {
        String sql = "";
        String order = "order by (CASE t.IsPass WHEN 0 THEN 10 WHEN 1 THEN 20 ELSE 30 END) ASC,  t.MessageTime  desc ";
        sql = queryMessageSql(order,plate);
        return (List<Tmessage>)getPageList(pageNum,pageSize,query,plate,sql);
    }

    @Override
    public List<Tmessage> queryNotPassMessageAll(String query, String plate) {
        String sql = "";
        String order = "order by (CASE t.IsPass WHEN 0 THEN 10 WHEN 1 THEN 20 ELSE 30 END) ASC,  t.MessageTime  desc ";
        sql = queryMessageSql(order,plate);
        return (List<Tmessage>)getAllList(query,plate,sql);
    }

    private String queryMessageSql(String order,String plate){
        String sql = "";
        if (plate != null && !plate.equals("")){
            sql = "SELECT * FROM Tmessage as t where Messkind = :plate AND MessageTitle like :query "+ order;
        }else{
            sql = "SELECT * FROM Tmessage as t where MessageTitle like :query " +order;
        }
        return sql;
    }

    private List getPageList(int pageNum, int pageSize, String query, String plate,String sql){
        NativeQuery nativeQuery = currentSession().createNativeQuery(sql)
                .addEntity(Tmessage.class)
                .setFirstResult((pageNum - 1)*pageSize)
                .setMaxResults(pageSize);
        if (plate != null && !plate.equals("")){
            return  nativeQuery.setParameter("plate", plate).setParameter("query","%"+query+"%").list();
        }else {
            return  nativeQuery.setParameter("query","%"+query+"%").list();
        }
    }

    private List getAllList(String query, String plate,String sql){
        NativeQuery nativeQuery = currentSession().createNativeQuery(sql)
                .addEntity(Tmessage.class);
        if (plate != null && !plate.equals("")){
            return  nativeQuery.setParameter("plate", plate).setParameter("query","%"+query+"%").list();
        }else {
            return  nativeQuery.setParameter("query","%"+query+"%").list();
        }
    }
}

package com.ssh.bbc.messcategory.Repository.impl;

import com.ssh.bbc.messcategory.Repository.TmesscategoryRepository;
import com.ssh.bbc.messcategory.domain.TmessCategory;
import com.ssh.bbc.user.domain.Tuser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TmesscategoryRepositoryImpl implements TmesscategoryRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public TmesscategoryRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }
    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public int addTMessCategory(TmessCategory category) {
        return (int)currentSession().save(category);
    }

    @Override
    public int updateTMessCategoryById(TmessCategory category) {
        currentSession().update(category);
        return category.getCategoryId();
    }

    @Override
    public int deleteTMessCategoryById(int categoryId) {
        TmessCategory tmessCategory = queryTMessCategoryById(categoryId);
        currentSession().delete(tmessCategory);
        return categoryId;
    }

    @Override
    public List<TmessCategory> queryTMessCategory(String query) {
        String sql = "SELECT * FROM tmesscategory where Category like :name";
        List list = currentSession().createNativeQuery(sql)
                .addEntity(TmessCategory.class)
                .setParameter("name", "%"+query+"%")
                .list();
        return (List<TmessCategory>)list;
    }

    @Override
    public List<TmessCategory> queryTMessCategoryByPage(int pageNum, int pageSize, String query) {
        String sql = "SELECT * FROM tmesscategory where Category like :name";
        List list = currentSession().createNativeQuery(sql)
                .addEntity(TmessCategory.class)
                .setParameter("name", "%"+query+"%")
                .setFirstResult((pageNum - 1)*pageSize)
                .setMaxResults(pageSize)
                .list();
        return (List<TmessCategory>)list;
    }

    @Override
    public TmessCategory queryTMessCategoryById(int categoryId) {
        return currentSession().get(TmessCategory.class,categoryId);
    }

    @Override
    public TmessCategory queryTMessCategoryByName(String category) {
        String sql = "SELECT * FROM tmesscategory where Category = :name";
        List list = currentSession().createNativeQuery(sql)
                .addEntity(TmessCategory.class)
                .setParameter("name", category)
                .list();
        return list.isEmpty() ? null : (TmessCategory) list.get(0);
    }

    @Override
    public TmessCategory queryTMessCategoryByOwner(String owner) {
        String sql = "SELECT * FROM tmesscategory where CategoryOwner = :name";
        List list = currentSession().createNativeQuery(sql)
                .addEntity(TmessCategory.class)
                .setParameter("name", owner)
                .list();
        return list.isEmpty() ? null : (TmessCategory) list.get(0);
    }
}

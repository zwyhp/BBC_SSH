package com.ssh.bbc.messcategory.Repository;

import com.ssh.bbc.messcategory.domain.TmessCategory;

import java.util.List;

public interface TmesscategoryRepository {
    int addTMessCategory(TmessCategory category);
    int updateTMessCategoryById(TmessCategory category);
    int deleteTMessCategoryById(int categoryId);
    List<TmessCategory> queryTMessCategory(String query);
    List<TmessCategory> queryTMessCategoryByPage(int pageNum,int pageSize,String query);
    TmessCategory queryTMessCategoryById(int categoryId);
    TmessCategory queryTMessCategoryByName(String category);
    TmessCategory queryTMessCategoryByOwner(String owner);
}

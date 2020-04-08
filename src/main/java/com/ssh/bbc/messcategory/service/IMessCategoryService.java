package com.ssh.bbc.messcategory.service;


import com.ssh.bbc.messcategory.domain.TmessCategory;
import com.ssh.bbc.util.PageUtil;

import java.util.List;

public interface IMessCategoryService {
    int addMessCategory(TmessCategory category);
    int updateMessCategory(TmessCategory category);
    int deleteMessCategoryById(int categoryId);
    PageUtil queryMessCategoryByPage(int pageNum, int pageSize, String query);
    List<TmessCategory> queryMessCategoryByPage(String query);
    TmessCategory queryMessCategoryById(int categoryId);
    TmessCategory queryMessCategoryByOwner(String ownerName);

}

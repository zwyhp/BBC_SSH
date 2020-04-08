package com.ssh.bbc.messcategory.service.impl;


import com.ssh.bbc.messcategory.Repository.TmesscategoryRepository;
import com.ssh.bbc.user.Repository.TuserRepository;
import com.ssh.bbc.user.domain.Tuser;
import com.ssh.bbc.util.BussinessUtil;
import com.ssh.bbc.messcategory.domain.TmessCategory;
import com.ssh.bbc.messcategory.service.IMessCategoryService;
import com.ssh.bbc.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MessCategoryService implements IMessCategoryService {

    @Autowired
    private TmesscategoryRepository tmesscategoryRepository;
    @Autowired
    private TuserRepository tuserRepository;

    /**
     * 增加版块时，需要把版主的是否为版主更新
     * @param category
     * @return
     */
    @Override
    public int addMessCategory(TmessCategory category) {
        verifyCategory(category,0);
        Tuser tuserByName = tuserRepository.queryTuserByName(category.getCategoryOwner());
        tuserByName.setIsOwner(1);
        tuserRepository.updateTuser(tuserByName);
        return tmesscategoryRepository.addTMessCategory(category);
    }

    @Override
    public int updateMessCategory(TmessCategory category) {
        verifyCategory(category,1);
        return tmesscategoryRepository.updateTMessCategoryById(category);
    }

    @Override
    public int deleteMessCategoryById(int categoryId) {
        return tmesscategoryRepository.deleteTMessCategoryById(categoryId);
    }

    @Override
    public PageUtil queryMessCategoryByPage(int pageNum, int pageSize, String query) {
        List<TmessCategory> users = tmesscategoryRepository.queryTMessCategoryByPage(pageNum,pageSize,query);
        return new PageUtil(pageNum,pageSize,queryMessCategoryByPage(query).size(),users);
    }

    @Override
    public List<TmessCategory> queryMessCategoryByPage(String query) {
        return tmesscategoryRepository.queryTMessCategory(query);
    }

    @Override
    public TmessCategory queryMessCategoryById(int categoryId) {
        return tmesscategoryRepository.queryTMessCategoryById(categoryId);
    }
    @Override
    public TmessCategory queryMessCategoryByOwner(String ownerName){
        return tmesscategoryRepository.queryTMessCategoryByOwner(ownerName);
    }

    /**
     * 板块名称不能重复，一个版主不能管理2个板块，增加与修改时需要验证
     * x   来判断  增加  还是修改
     * @param category
     */
    private void verifyCategory(TmessCategory category,int x){
        TmessCategory tmessCategory = tmesscategoryRepository.queryTMessCategoryByName(category.getCategory());
        BussinessUtil.isnotNull(tmessCategory,BussinessUtil.CATEGORY_REPETITION);
        TmessCategory tMessCategoryByOwner = tmesscategoryRepository.queryTMessCategoryByOwner(category.getCategoryOwner());
        if (x == 0){
            BussinessUtil.isnotNull(tMessCategoryByOwner, BussinessUtil.OWNER_REPETITION);
        }
        if(x == 1 && category.getCategoryId() != tMessCategoryByOwner.getCategoryId()) {
            BussinessUtil.isnotNull(tMessCategoryByOwner, BussinessUtil.OWNER_REPETITION);
        }
    }

    /**
     * 处理新旧版主的   权限问题
     * 旧的取消     新的加上
     * @param category
     */
    private void setCategoryOwner(TmessCategory category){
        TmessCategory tmessCategory = tmesscategoryRepository.queryTMessCategoryById(category.getCategoryId());
        Tuser oldUser = tuserRepository.queryTuserByName(tmessCategory.getCategoryOwner());
        oldUser.setIsOwner(0);
        tuserRepository.updateTuser(oldUser);
        Tuser newUser = tuserRepository.queryTuserByName(category.getCategoryOwner());
        newUser.setIsOwner(1);
        tuserRepository.updateTuser(newUser);
    }

}

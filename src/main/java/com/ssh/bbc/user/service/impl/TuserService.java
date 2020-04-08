package com.ssh.bbc.user.service.impl;


import com.ssh.bbc.user.Repository.TuserRepository;
import com.ssh.bbc.user.domain.Tuser;
import com.ssh.bbc.user.service.ItuserService;
import com.ssh.bbc.util.BussinessUtil;
import com.ssh.bbc.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TuserService implements ItuserService {
    @Autowired
    private TuserRepository tuserRepository;

    /**
     * 用户名的  id  inblac  inowner为默认的
     * @param user
     * @return
     */
    @Override
    public int addTuser(Tuser user) {
        Tuser userByName;
        userByName = tuserRepository.queryTuserByName(user.getUserName());
        BussinessUtil.isnotNull(userByName,BussinessUtil.USERNAME_REPETITION);
        Tuser userByEmail = tuserRepository.queryTuserByEmail(user.getUserEmail());
        BussinessUtil.isnotNull(userByEmail,BussinessUtil.EMAIL_REPETITION);
        user.setInBlack(0);
        user.setIsOwner(0);
        return tuserRepository.addTUser(user);
    }

    /**
     * 只允许修改密码
     * @param user
     * @return
     */
    @Override
    public int updateTuser(Tuser user) {
        Tuser tuser = queryTuserByName(user.getUserName());
        tuser.setUserPwd(user.getUserPwd());
        return tuserRepository.updateTuser(tuser);
    }

    @Override
    public int updateTuserBlackById(int userId) {
        Tuser userByID = tuserRepository.queryTuserByID(userId);
        BussinessUtil.isNull(userByID,BussinessUtil.USER_NOT_EXIST);
        if (userByID.getInBlack() == 0) {
            userByID.setInBlack(1);
        } else {
            userByID.setInBlack(0);
        }
        return tuserRepository.updateTuser(userByID);
    }

    @Override
    public int updateTuserOwnerById(int userId) {
        Tuser userByID = tuserRepository.queryTuserByID(userId);
        BussinessUtil.isNull(userByID,BussinessUtil.USER_NOT_EXIST);
        if (userByID.getIsOwner() == 0) {
            userByID.setIsOwner(1);
        } else {
            userByID.setIsOwner(0);
        }
        return tuserRepository.updateTuser(userByID);
    }

    @Override
    public int deleteTuserById(int userId) {
        return tuserRepository.deleteTuserById(userId);
    }

    @Override
    public Tuser queryTuserByName(String name) {
        Tuser userByName = tuserRepository.queryTuserByName(name);
        BussinessUtil.isNull(userByName,BussinessUtil.USER_NOT_EXIST);
        return userByName;
    }

    @Override
    public Tuser queryTuserByID(int userId) {
        return tuserRepository.queryTuserByID(userId);
    }

    /**
     * 使用分页插件
     * @param pageNum  当前页数
     * @param pageSize 每页显示数量
     * @param query  按名字查询
     * @return
     */
    @Override
    public PageUtil queryTUserByPage(int pageNum, int pageSize, String query) {
        List<Tuser> alluser = tuserRepository.queryTUserByname(query);
        List<Tuser> users = tuserRepository.queryTUserByPage(pageNum,pageSize,query);
        return new PageUtil(pageNum,pageSize,alluser.size(),users);
    }
}

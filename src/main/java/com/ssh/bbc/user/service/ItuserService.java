package com.ssh.bbc.user.service;

import com.ssh.bbc.user.domain.Tuser;
import com.ssh.bbc.util.PageUtil;

import java.util.List;

public interface ItuserService {
    int addTuser(Tuser user);
    int updateTuser(Tuser user);
    int updateTuserBlackById(int userId);
    int updateTuserOwnerById(int userId);
    int deleteTuserById(int userId);
    Tuser queryTuserByName(String name);
    Tuser queryTuserByID(int userId);
    PageUtil queryTUserByPage(int pageNum, int pageSize, String query);
}

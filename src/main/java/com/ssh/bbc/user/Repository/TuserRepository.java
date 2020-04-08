package com.ssh.bbc.user.Repository;

import com.ssh.bbc.user.domain.Tuser;

import java.util.List;

public interface TuserRepository {
    int deleteTuserById(int userId);
    List<Tuser> queryTUserByPage(int pageNum,int pageSize,String query);
    List<Tuser> queryTUserByname(String query);
    int addTUser(Tuser user);
    int updateTuser(Tuser user);
    Tuser queryTuserByName(String name);
    Tuser queryTuserByEmail(String email);
    Tuser queryTuserByID(int userId);
}

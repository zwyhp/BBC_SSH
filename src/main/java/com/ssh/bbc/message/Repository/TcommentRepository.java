package com.ssh.bbc.message.Repository;

import com.ssh.bbc.message.domain.Tcomment;

import java.util.List;

public interface TcommentRepository {
    int addTcomment(Tcomment tcomment);
    int updateTcommentById(Tcomment tcomment);
    int deleteTcommentById(int commentId);
    List<Tcomment> queryTcommentByPage(int pageNum,int pageSize,int messageId);
    List<Tcomment> queryTcommentAll(int messageId);
    Tcomment queryTcommentById(int commentId);
}

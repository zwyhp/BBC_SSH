package com.ssh.bbc.message.service;

import com.ssh.bbc.message.domain.Tcomment;
import com.ssh.bbc.message.domain.Tmessage;
import com.ssh.bbc.util.PageUtil;

import java.util.List;

public interface ImessageService {
    int addMessage(Tmessage tmessage);
    int addComment(Tcomment tcomment);
    int updateMessage(Tmessage tmessage);
    int deleteMessage(int messageId);

    int checkMessage(int messageId);
    int stickMessage(int messageId);
    List<Tmessage> queryMessageTop10(String plate);
    PageUtil queryMessageByPage(int pageNum,int pageSize,String query,String plate);

    PageUtil queryNotPassMessageByPage(int pageNum, int pageSize, String query , String plate);

    Tmessage queryMessageById(int messageId);

    PageUtil queryCommentByPage(int pageNum,int pageSize,int messageId);
}

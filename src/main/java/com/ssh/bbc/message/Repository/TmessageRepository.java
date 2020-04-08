package com.ssh.bbc.message.Repository;

import com.ssh.bbc.message.domain.Tmessage;

import java.util.List;
import java.util.Map;

public interface TmessageRepository {
    int addTmessage(Tmessage tmessage);
    int updateTmessageById(Tmessage tmessage);
    int deleteTmessageById(int messageId);
    List<Tmessage> queryTmessageTop10(int pageNum, int pageSize,String query);
    List<Tmessage> queryTmessageByPage(int pageNum, int pageSize,String query,String plate);
    List<Tmessage> queryTmessageByAll(String query, String plate);
    Tmessage queryTmessageById(int messageId);
    List<Tmessage> queryNotPassMessageByPage(int pageNum, int pageSize, String query , String plate);
    List<Tmessage> queryNotPassMessageAll(String query, String plate);
}

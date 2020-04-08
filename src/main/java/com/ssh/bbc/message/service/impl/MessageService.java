package com.ssh.bbc.message.service.impl;

import com.ssh.bbc.message.Repository.TcommentRepository;
import com.ssh.bbc.message.Repository.TmessageRepository;
import com.ssh.bbc.util.BussinessUtil;

import com.ssh.bbc.message.domain.Tcomment;
import com.ssh.bbc.message.domain.Tmessage;
import com.ssh.bbc.message.service.ImessageService;
import com.ssh.bbc.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MessageService implements ImessageService {
    @Autowired
    private TcommentRepository tcommentRepository;
    @Autowired
    private TmessageRepository tmessageRepository;


    @Override
    public int addMessage(Tmessage tmessage) {
        tmessage.setIsPass(0);
        tmessage.setIsTop(0);
        tmessage.setClickNumber(0);
        tmessage.setMessageTime(LocalDateTime.now());
        tmessage.setLastCommentTime(LocalDateTime.now());
        return tmessageRepository.addTmessage(tmessage);
    }

    @Override
    public int addComment(Tcomment tcomment) {
        Tmessage tmessage = tmessageRepository.queryTmessageById(tcomment.getMessageId());
        BussinessUtil.isNull(tmessage,BussinessUtil.MESSAGE_IS_NULL);
        tmessage.setLastCommentTime(LocalDateTime.now());
        tcomment.setCommentTime(LocalDateTime.now());
        updateMessage(tmessage);
        return tcommentRepository.addTcomment(tcomment);
    }

    @Override
    public int updateMessage(Tmessage tmessage) {
        return tmessageRepository.updateTmessageById(tmessage);
    }

    @Override
    public int deleteMessage(int messageId) {
        return tmessageRepository.deleteTmessageById(messageId);
    }

    @Override
    public int checkMessage(int messageId) {
        Tmessage tmessage = tmessageRepository.queryTmessageById(messageId);
        if (tmessage.getIsPass()==0) {
            tmessage.setIsPass(1);
        } else{
            tmessage.setIsPass(0);
        }
        return updateMessage(tmessage);
    }

    /**
     * 置顶之前需要    这个帖子 审核通过
     * @param messageId
     * @return
     */
    @Override
    public int stickMessage(int messageId) {
        Tmessage tmessage = tmessageRepository.queryTmessageById(messageId);
        if (tmessage.getIsPass() == 0){
            BussinessUtil.error("置顶失败，帖子没有审核");
        }
        if (tmessage.getIsTop()==0) {
            tmessage.setIsTop(1);
        } else{
            tmessage.setIsTop(0);
        }
        return updateMessage(tmessage);
    }


    @Override
    public List<Tmessage> queryMessageTop10(String plate) {
        return tmessageRepository.queryTmessageTop10(1,10,plate);
    }

    @Override
    public PageUtil queryMessageByPage(int pageNum, int pageSize,String query,String plate) {
        List<Tmessage> allMessages = tmessageRepository.queryTmessageByAll(query, plate);
        List<Tmessage> messages = tmessageRepository.queryTmessageByPage(pageNum, pageSize, query, plate);
        return new PageUtil(pageNum,pageSize,allMessages.size(),messages);
    }

    @Override
    public PageUtil queryNotPassMessageByPage(int pageNum, int pageSize, String query ,String plate) {
        List<Tmessage> allMessages = tmessageRepository.queryNotPassMessageAll(query, plate);
        List<Tmessage> messages = tmessageRepository.queryNotPassMessageByPage(pageNum, pageSize, query, plate);
        return new PageUtil(pageNum,pageSize,allMessages.size(),messages);
    }

    /**
     * @param messageId
     * @return
     */
    @Override
    public Tmessage queryMessageById(int messageId) {
        Tmessage tmessage = tmessageRepository.queryTmessageById(messageId);
        if (tmessage != null){
            tmessage.setClickNumber(tmessage.getClickNumber()+1);
            tmessageRepository.updateTmessageById(tmessage);
        }
        return tmessage;
    }

    @Override
    public PageUtil queryCommentByPage(int pageNum, int pageSize ,int messageId) {
        List<Tcomment> allComments = tcommentRepository.queryTcommentAll(messageId);
        List<Tcomment> comments = tcommentRepository.queryTcommentByPage(pageNum, pageSize, messageId);
        return new PageUtil(pageNum,pageSize,allComments.size(),comments);
    }
}

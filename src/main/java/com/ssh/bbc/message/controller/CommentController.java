package com.ssh.bbc.message.controller;

import com.ssh.bbc.message.domain.Tcomment;
import com.ssh.bbc.message.service.ImessageService;
import com.ssh.bbc.user.domain.Tuser;
import com.ssh.bbc.util.BussinessUtil;
import com.ssh.bbc.util.PageUtil;
import com.ssh.bbc.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/tourist")
public class CommentController {
    @Autowired
    private ImessageService imessageService;

    /**
     * @param tcomment
     * @return
     */
    @PostMapping("/comment")
    public Object addComment(@RequestBody @Validated Tcomment tcomment,
                             HttpServletRequest request){
        Tuser user = (Tuser) request.getSession().getAttribute("user");
        if (user!=null){
            tcomment.setCommentUser(user.getUserName());
        }else {
            tcomment.setCommentUser("游客"+request.getRemoteAddr());
        }
        int i = imessageService.addComment(tcomment);
        return i>0? ResponseUtil.ok():ResponseUtil.afterError(BussinessUtil.ADD_FAILED);
    }



    @GetMapping("/comment")
    public Object getComment(@RequestParam(value = "messageId",required = true) int messageId,
            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        PageUtil pageUtil = imessageService.queryCommentByPage(pageNum, pageSize, messageId);
        return ResponseUtil.okList(pageUtil);
    }

}

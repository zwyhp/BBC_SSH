package com.ssh.bbc.user.controller;

import com.ssh.bbc.messcategory.domain.TmessCategory;
import com.ssh.bbc.messcategory.service.IMessCategoryService;
import com.ssh.bbc.user.domain.Tuser;
import com.ssh.bbc.user.service.ItuserService;
import com.ssh.bbc.util.BussinessUtil;
import com.ssh.bbc.util.MD5hexUtil;
import com.ssh.bbc.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private ItuserService ituserService;
    @Autowired
    private IMessCategoryService messCategoryService;

    /**
     * 对密码和安全问题答案   进行 md5加密后在进行注册
     * @param tuser
     * @return
     */
    @PostMapping("/register")
    public Object addUser(@RequestBody @Validated Tuser tuser){
        tuser.setUserPwd(MD5hexUtil.getMd5hex(tuser.getUserName(),tuser.getUserPwd()));
        tuser.setAnswer(MD5hexUtil.getMd5hex(tuser.getUserName(),tuser.getAnswer()));
        int i = ituserService.addTuser(tuser);
        return i>0? ResponseUtil.ok() : ResponseUtil.afterError(BussinessUtil.ADD_FAILED);
    }

    /**
     * 查看用户的安全问题 用于修改密码
     * @param userName
     * @return
     */
    @GetMapping("/question")
    public Object question(@RequestParam(value = "userName") String userName){
        Tuser userByName = ituserService.queryTuserByName(userName);
        return ResponseUtil.ok(userByName.getQuestion());
    }

    /**
     * 验证安全问题   然后修改密码
     * @param user
     * @return
     */
    @PutMapping("/update")
    public Object updatePwd(@RequestBody Tuser user){
        int i = 0;
        String answer = MD5hexUtil.getMd5hex(user.getUserName(),user.getAnswer());
        Tuser userByName = ituserService.queryTuserByName(user.getUserName());
        if (!userByName.getAnswer().equals(answer)){
          return ResponseUtil.afterError(BussinessUtil.ANSWER_FAILED);
        }
        user.setUserPwd(MD5hexUtil.getMd5hex(user.getUserName(),user.getUserPwd()));
        i = ituserService.updateTuser(user);
        return i>0? ResponseUtil.ok() : ResponseUtil.afterError(BussinessUtil.UPDATE_FAILED);
    }

    @PostMapping(value = "/login")
    public Object login(@RequestBody Map<String,String> map,
                        HttpServletRequest request){
        String username = map.get("username");
        String password = map.get("password");
        Tuser userByName = ituserService.queryTuserByName(username);
        if (userByName == null){
            return ResponseUtil.unsupport(BussinessUtil.USER_NOT_EXIST);
        }
        String pwd = MD5hexUtil.getMd5hex(username,password);
        if(userByName.getUserPwd().equals(pwd)){
            request.getSession().setAttribute("user",userByName);
            Map<Object, Object> result = new HashMap<Object, Object>();
            result.put("token", request.getSession().getId());
            result.put("user", userByName);
            if (userByName.getIsOwner() == 1){
                TmessCategory tmessCategory = messCategoryService.queryMessCategoryByOwner(username);
                result.put("role",tmessCategory.getCategory());
                request.getSession().setAttribute("role",tmessCategory.getCategory());
            }else if (userByName.getIsOwner() == 2){
                result.put("role","*");
            }
            return ResponseUtil.ok(result);
        }else {
            return ResponseUtil.unsupport(BussinessUtil.PWD_ERROR);
        }
    }

    /**
     * 获得用户信息     session与cookie 没有处理好
     * @param id
     * @param request
     * @return
     */
    @GetMapping("/info")
    public Object info(@RequestParam(value = "token", required = false) String id,HttpServletRequest request){
            Map<Object, Object> result = new HashMap<Object, Object>();
            Tuser user = (Tuser)request.getSession().getAttribute("user");
            if (user!=null){
                result.put("name",user.getUserName());
                if (user.getInBlack() != 1) {
                    result.put("role", user.getIsOwner());
                }
            }else {
                result.put("name","游客" + request.getRemoteAddr() );
            }
            return ResponseUtil.ok(result);
    }

    @GetMapping("/logout")
    public Object logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("role");
        request.getSession().removeAttribute("name");
        return ResponseUtil.ok();
    }


}

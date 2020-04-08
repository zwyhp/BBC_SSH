package com.ssh.bbc.user.controller;

import com.ssh.bbc.user.service.ItuserService;
import com.ssh.bbc.util.BussinessUtil;
import com.ssh.bbc.util.PageUtil;
import com.ssh.bbc.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 管理员 对用户的操作
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ItuserService ituserService;

    @GetMapping("/users")
    public Object users(@RequestParam(value = "query", required = false) String query,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        PageUtil pageUtil = ituserService.queryTUserByPage(pageNum, pageSize, query);
        return ResponseUtil.okList(pageUtil);
    }

    @PutMapping("/updateBlack/{id}")
    public Object updateBlack(@PathVariable int id){
        int i = ituserService.updateTuserBlackById(id);
        return i>0? ResponseUtil.ok() : ResponseUtil.afterError(BussinessUtil.UPDATE_FAILED);
    }

    @PutMapping("/updateOwner/{id}")
    public Object updateOwner(@PathVariable int id){
        int i = ituserService.updateTuserOwnerById(id);
        return i>0? ResponseUtil.ok() : ResponseUtil.afterError(BussinessUtil.UPDATE_FAILED);
    }

    @DeleteMapping("/deleteUser/{id}")
    public Object deleteUser(@PathVariable int id){
        int i = ituserService.deleteTuserById(id);
        return i>0? ResponseUtil.ok() : ResponseUtil.afterError(BussinessUtil.DELETE_FAILED);
    }


}

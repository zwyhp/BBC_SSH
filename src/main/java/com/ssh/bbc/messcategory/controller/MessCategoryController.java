package com.ssh.bbc.messcategory.controller;

import com.ssh.bbc.messcategory.domain.TmessCategory;
import com.ssh.bbc.messcategory.service.IMessCategoryService;
import com.ssh.bbc.util.BussinessUtil;
import com.ssh.bbc.util.PageUtil;
import com.ssh.bbc.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessCategoryController {
    @Autowired
    private IMessCategoryService messCategoryService;

    @GetMapping("/tourist/getcategory")
    public Object category(){
        List<TmessCategory> tmessCategories = messCategoryService.queryMessCategoryByPage("");
        return ResponseUtil.ok(tmessCategories);
    }

    @GetMapping("/admin/category")
    public Object category(@RequestParam(value = "query", required = false) String query,
            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        PageUtil pageUtil = messCategoryService.queryMessCategoryByPage(pageNum, pageSize,query);
        return ResponseUtil.okList(pageUtil);
    }

    @PostMapping("/admin/addcategory")
    public Object addCategory(@RequestBody @Validated TmessCategory tcategory){
        int i = messCategoryService.addMessCategory(tcategory);
        return i>0? ResponseUtil.ok() : ResponseUtil.badArgument(BussinessUtil.ADD_FAILED);
    }

    @PutMapping("/admin/category")
    public Object updateCategory(@RequestBody @Validated TmessCategory category){
        int i = messCategoryService.updateMessCategory(category);
        return i>0? ResponseUtil.ok() : ResponseUtil.badArgument(BussinessUtil.UPDATE_FAILED);
    }

    @DeleteMapping("/admin/category/{id}")
    public Object deleteCategory(@PathVariable int id){
        int i = messCategoryService.deleteMessCategoryById(id);
        return i>0? ResponseUtil.ok() : ResponseUtil.badArgument(BussinessUtil.DELETE_FAILED);
    }
}

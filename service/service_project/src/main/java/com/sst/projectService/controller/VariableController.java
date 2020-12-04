package com.sst.projectService.controller;


import com.sst.commonutils.NotBlank;
import com.sst.commonutils.R;
import com.sst.projectService.entity.Variable;
import com.sst.projectService.service.VariableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sst
 * @since 2020-12-04
 */
@RestController
@RequestMapping("/api/variable")
public class VariableController {
    @Autowired
    VariableService variableService;

    //1.创建变量
    @RequestMapping("addVariable")
    public R addVariable(@RequestBody Variable variable){
        variableService.save(variable);
        return R.ok().data(variable);
    }
    //2.获取所有变量
    @RequestMapping("getAllVariable")
    public R getAllVariable(){
        return R.ok().data(variableService.list(null));
    }
    //3.删除变量
    @RequestMapping("deleteVariable")
    public R deleteVariable(@NotBlank @RequestParam String id){
        variableService.removeById(id);
        return R.ok().message("删除成功");
    }
}


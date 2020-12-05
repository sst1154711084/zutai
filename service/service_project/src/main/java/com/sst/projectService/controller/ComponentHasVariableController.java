package com.sst.projectService.controller;


import com.sst.commonutils.NotBlank;
import com.sst.commonutils.R;
import com.sst.projectService.entity.ComponentHasVariable;
import com.sst.projectService.service.ComponentHasVariableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sst
 * @since 2020-12-05
 */
@RestController
@RequestMapping("/api/bind")
public class ComponentHasVariableController {
    @Autowired
    ComponentHasVariableService componentHasVariableService;
    //1.控件绑定变量
    @RequestMapping("/bindVariable")
    public R bindVariable(@NotBlank @RequestParam String componentId,@NotBlank @RequestParam String variableId){
        ComponentHasVariable chv = componentHasVariableService.bindVariable(componentId, variableId);
        return R.ok().message("绑定成功");
    }
}


package com.sst.projectService.controller;


import com.sst.commonutils.NotBlank;
import com.sst.commonutils.R;
import com.sst.projectService.entity.ComponentHasVariable;
import com.sst.projectService.entity.Variable;
import com.sst.projectService.service.ComponentHasVariableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    //1.图表控件绑定变量
    @RequestMapping("/bindVariable")
    public R bindVariable(@NotBlank @RequestParam String componentId,@RequestParam String variableId){
        componentHasVariableService.bindVariable(componentId, variableId);
        return R.ok().message("绑定成功");
    }

    //2.获取控件变量
    @RequestMapping("/getVariable")
    public R getVariable(@NotBlank @RequestParam String componentId){
        List<Variable> variables = componentHasVariableService.getVariable(componentId);
        return R.ok().message("绑定成功").data(variables);
    }

    //3.解除绑定变量
    @RequestMapping("/unbindVariable")
    public R unbindVariable(@NotBlank @RequestParam String componentId,@NotBlank @RequestParam String variableId){
        componentHasVariableService.unbindVariable(componentId, variableId);
        return R.ok();
    }
    //3.解除全部变量
    @RequestMapping("/unbindVariables")
    public R unbindVariables(@NotBlank @RequestParam String componentId){
        componentHasVariableService.unbindVariables(componentId);
        return R.ok();
    }
}


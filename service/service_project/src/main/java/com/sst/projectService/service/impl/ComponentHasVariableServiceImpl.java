package com.sst.projectService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sst.commonutils.MyException;
import com.sst.projectService.entity.Chart;
import com.sst.projectService.entity.ComponentHasVariable;
import com.sst.projectService.entity.Graph;
import com.sst.projectService.entity.Variable;
import com.sst.projectService.mapper.ComponentHasVariableMapper;
import com.sst.projectService.service.ChartService;
import com.sst.projectService.service.ComponentHasVariableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sst.projectService.service.GraphService;
import com.sst.projectService.service.VariableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sst
 * @since 2020-12-05
 */
@Service
public class ComponentHasVariableServiceImpl extends ServiceImpl<ComponentHasVariableMapper, ComponentHasVariable> implements ComponentHasVariableService {

    @Autowired
    GraphService graphService;
    @Autowired
    ChartService chartService;
    @Autowired
    VariableService variableService;
    @Override
    public void bindVariable(String componentId, String[] variableIds) {
        if(variableIds==null || variableIds.length==0)return;
        checkComponentExist(componentId);
        Set<String> variableIdSet = new HashSet<>(Arrays.asList(variableIds));
        List<ComponentHasVariable> res = new ArrayList<>();
        for(String variableId : variableIdSet){
            checkVariableExist(variableId);
            QueryWrapper<ComponentHasVariable> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("component_id",componentId);
            queryWrapper.eq("variable_id",variableId);
            ComponentHasVariable one = getOne(queryWrapper);
            if(one != null)
                throw new MyException("该控件已绑定此变量");
            ComponentHasVariable chv = new ComponentHasVariable();
            chv.setComponentId(componentId);
            chv.setVariableId(variableId);
            res.add(chv);
        }
        saveBatch(res);
    }

    @Override
    public List<Variable> getVariable(String componentId) {
        checkComponentExist(componentId);
        QueryWrapper<ComponentHasVariable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("component_id",componentId);
        queryWrapper.select("variable_id");
        List<ComponentHasVariable> componentHasVariables = list(queryWrapper);
        List<String> variableIds = toVariableIds(componentHasVariables);
        List<Variable> variables = new ArrayList<>();
        if(!variableIds.isEmpty())
            variables = (List<Variable>) variableService.listByIds(variableIds);
        return variables;
    }

    @Override
    public List<String> getVariableIds(String componentId) {
        List<Variable> variables = getVariable(componentId);
        List<String> ids = new ArrayList<>();
        for(Variable v : variables){
            ids.add(v.getId());
        }
        return ids;
    }
    @Override
    public void unbindVariable(String componentId, String variableId) {
        QueryWrapper<ComponentHasVariable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("component_id",componentId);
        queryWrapper.eq("variable_id",variableId);
        ComponentHasVariable chv = getOne(queryWrapper);
        if(chv == null)
            throw new MyException("该控件没有绑定此变量");
        remove(queryWrapper);
    }

    @Override
    public void unbindVariables(String componentId) {
        checkComponentExist(componentId);
        QueryWrapper<ComponentHasVariable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("component_id",componentId);
        remove(queryWrapper);
    }



    private void checkVariableExist(String variableId) {
        Variable variable = variableService.getById(variableId);
        if(variable==null)
            throw new MyException("变量不存在");
    }

    private List<String> toVariableIds(List<ComponentHasVariable> componentHasVariables) {
        List<String> ids = new ArrayList<>();
        for(ComponentHasVariable chv : componentHasVariables){
            ids.add(chv.getVariableId());
        }
        return ids;
    }

    private void checkComponentExist(String componentId) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("identifier",componentId);
        Graph graph = graphService.getOne(queryWrapper);
        Chart chart = chartService.getOne(queryWrapper);
        if(graph==null && chart==null)
            throw new MyException("控件不存在");
    }
}

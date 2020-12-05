package com.sst.projectService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sst.commonutils.MyException;
import com.sst.projectService.entity.ComponentHasVariable;
import com.sst.projectService.mapper.ComponentHasVariableMapper;
import com.sst.projectService.service.ComponentHasVariableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

    @Override
    public ComponentHasVariable bindVariable(String componentId, String variableId) {
        QueryWrapper<ComponentHasVariable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("component_id",componentId);
        queryWrapper.eq("variable_id",variableId);
        ComponentHasVariable one = getOne(queryWrapper);
        if(one != null)
            throw new MyException("该控件已绑定此变量");
        ComponentHasVariable chv = new ComponentHasVariable();
        chv.setComponentId(componentId);
        chv.setVariableId(variableId);
        save(chv);
        return chv;
    }
}

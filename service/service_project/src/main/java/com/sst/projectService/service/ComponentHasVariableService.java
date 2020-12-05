package com.sst.projectService.service;

import com.sst.projectService.entity.ComponentHasVariable;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sst.projectService.entity.Variable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sst
 * @since 2020-12-05
 */
public interface ComponentHasVariableService extends IService<ComponentHasVariable> {

    ComponentHasVariable bindVariable(String componentId, String variableId);

    List<Variable> getVariable(String componentId);

    void unbindVariable(String componentId, String variableId);
}

package com.sst.projectService.service;

import com.sst.projectService.entity.ComponentHasVariable;
import com.baomidou.mybatisplus.extension.service.IService;

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
}

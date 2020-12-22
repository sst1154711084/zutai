package com.sst.projectService.service;

import com.sst.projectService.entity.Chart;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sst
 * @since 2020-11-14
 */
public interface ChartService extends IService<Chart> {
    void delete(String projectId);

    void addBatch(List<Chart> charts);

    List<Chart> findAll(String id);

    void unbindVariables(Chart chart);

    void bindVariable(Chart chart);
}

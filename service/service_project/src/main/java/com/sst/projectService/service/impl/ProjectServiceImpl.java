package com.sst.projectService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sst.projectService.entity.Layer;
import com.sst.projectService.entity.Project;
import com.sst.projectService.entity.ro.ResultProject;
import com.sst.projectService.mapper.ProjectMapper;
import com.sst.projectService.service.ChartService;
import com.sst.projectService.service.GraphService;
import com.sst.projectService.service.LayerService;
import com.sst.projectService.service.ProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sst
 * @since 2020-11-13
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {

    @Autowired
    LayerService layerService;
    @Autowired
    GraphService graphService;
    @Autowired
    ChartService chartService;
    @Override
    public List<Project> getAllProject(Project project) {
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>(project);
        return list(queryWrapper);
    }

    @Override
    public ResultProject getProject(String id) {
        ResultProject result = new ResultProject();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("project_id",id);
        Layer layer = layerService.getOne(wrapper);
        List<Object> list = new ArrayList<>();
        list.addAll(graphService.listObjs(wrapper));
        list.addAll(chartService.listObjs(wrapper));
        result.setLayer(layer);
        if(null!=layer)
            result.setName(layer.getName());
        result.setComponents(list);
        return result;
    }
}

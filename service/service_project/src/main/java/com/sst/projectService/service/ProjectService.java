package com.sst.projectService.service;

import com.sst.projectService.entity.Component;
import com.sst.projectService.entity.Layer;
import com.sst.projectService.entity.Project;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sst.projectService.entity.ro.ResultProject;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sst
 * @since 2020-11-13
 */
public interface ProjectService extends IService<Project> {

    List<Project> getAllProject(Project project);

    ResultProject getProject(String id);

    boolean deleteProject(String id);

    Project addProject(Project project);

    void saveProject(String projectId, Layer layer, Component[] components);
}

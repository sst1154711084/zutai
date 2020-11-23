package com.sst.projectService.controller;


import com.sst.commonutils.NotBlank;
import com.sst.commonutils.R;
import com.sst.projectService.entity.Project;
import com.sst.projectService.entity.ro.ResultProject;
import com.sst.projectService.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author sst
 * @since 2020-11-13
 */
@RestController
@RequestMapping("/api/project")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    //1.返回所有工程
    @RequestMapping("/getAllProject")
    public R getAllProject(@RequestBody(required = false) Project project) {
        List<Project> list = projectService.getAllProject(project);
        return R.ok().data(list);
    }

    //2.删除工程
    @RequestMapping("/deleteProject")
    public R deleteProject(@RequestParam @NotBlank String id) {
        return R.ok().data(projectService.deleteProject(id));
    }

    //3.新建工程
    @RequestMapping("/addProject")
    public R addProject(@RequestBody Project project) {
        return R.ok().data(projectService.save(project));
    }

    //4.获取工程
    @RequestMapping("/getProject")
    public R getProject(@RequestParam @NotBlank String id) {
        ResultProject result = projectService.getProject(id);
        return R.ok().data(result);
    }

}


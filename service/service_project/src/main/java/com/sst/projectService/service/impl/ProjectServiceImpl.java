package com.sst.projectService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sst.commonutils.MyException;
import com.sst.projectService.entity.*;
import com.sst.projectService.entity.ro.ResultProject;
import com.sst.projectService.mapper.ProjectMapper;
import com.sst.projectService.service.ChartService;
import com.sst.projectService.service.GraphService;
import com.sst.projectService.service.LayerService;
import com.sst.projectService.service.ProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
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
        if(listByIds(Collections.singletonList(id)).size()==0)
            throw new MyException("项目不存在");
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("project_id",id);
        Layer layer = layerService.getOne(wrapper);
        List<Graph> graphs = graphService.listObjs(wrapper);
        List<Chart> charts = chartService.listObjs(wrapper);
        ResultProject result = toResultProject(layer,graphs,charts);
        return result;
    }

    private ResultProject toResultProject(Layer layer, List<Graph> graphs, List<Chart> charts) {
        ResultProject resultProject = new ResultProject();
        resultProject.setLayer(layer);
        List<Component> components = new ArrayList<>();
        for(Graph graph : graphs){
            Component component = new Component();
            component.setId(graph.getId());
            component.setType(graph.getType());
            component.setTitle("graph");
            component.setName(graph.getName());
            component.setIdentifier(graph.getIdentifier());
            Component.Style style = component.new Style();
            Component.Style.Position position = style.new Position();
            position.setX(graph.getX());
            position.setY(graph.getY());
            position.setH(graph.getH());
            position.setW(graph.getW());
            style.setPosition(position);
            style.setAngle(graph.getAngle());
            style.setBackColor(graph.getBackColor());
            style.setBorderColor(graph.getBorderColor());
            style.setBorderWidth(graph.getBorderWidth());
            style.setFontFamily(graph.getFontFamily());
            style.setFontSize(graph.getFontSize());
            style.setForeColor(graph.getForeColor());
            style.setIdentifier(graph.getIdentifier());
            style.setIsItalic(graph.getIsItalic());
            style.setIsUnderline(graph.getIsUnderline());
            style.setIsWeight(graph.getIsWeight());
            style.setLibraryitemId(graph.getLibraryitemId());
            style.setLocked(graph.getLocked());
            style.setRadius(graph.getRadius());
            style.setText(graph.getText());
            style.setTextAlign(graph.getTextAlign());
            style.setTransform(graph.getTransform());
            style.setVisible(graph.isVisible());
            style.setZIndex(graph.getZIndex());
            component.setStyle(style);
            component.setCreateTime(graph.getCreateTime());
            component.setUpdateTime(graph.getUpdateTime());
            components.add(component);
        }
        for(Chart chart : charts){
            Component component = new Component();
            component.setId(chart.getId());
            component.setType(chart.getType());
            component.setTitle("chart");
            component.setName(chart.getName());
            component.setIdentifier(chart.getIdentifier());
            Component.Style style = component.new Style();
            Component.Style.Position position = style.new Position();
            position.setX(chart.getX());
            position.setY(chart.getY());
            position.setH(chart.getH());
            position.setW(chart.getW());
            style.setPosition(position);
            style.setBackColor(chart.getBackColor());
            style.setBorderColor(chart.getBorderColor());
            style.setBorderWidth(chart.getBorderWidth());
            style.setFontFamily(chart.getFontFamily());
            style.setFontSize(chart.getFontSize());
            style.setForeColor(chart.getForeColor());
            style.setIdentifier(chart.getIdentifier());
            style.setLocked(chart.getLocked());
            style.setText(chart.getText());
            style.setTransform(chart.getTransform());
            style.setVisible(chart.getVisible());
            style.setZIndex(chart.getZIndex());
            style.setDataTotal(chart.getDataTotal());
            style.setInnerColor(chart.getInnerColor());
            style.setMax(chart.getMax());
            style.setMin(chart.getMin());
            style.setSplitNumber(chart.getSplitNumber());
            style.setVisible(chart.getVisible());
            style.setWaveColor(chart.getWaveColor());
            component.setStyle(style);
            component.setCreateTime(chart.getCreateTime());
            component.setUpdateTime(chart.getUpdateTime());
            components.add(component);
        }
        resultProject.setComponents(components);
        return resultProject;
    }

    @Override
    public boolean deleteProject(String id) {
        if(listByIds(Collections.singletonList(id)).size()==0)
            throw new MyException("项目不存在");
        removeById(id);
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("project_id",id);
        layerService.remove(wrapper);
        graphService.remove(wrapper);
        chartService.remove(wrapper);
        return true;
    }

    @Override
    public Project addProject(Project project) {
        if(StringUtils.isEmpty(project.getName()))
            throw new MyException("项目名不能为空");
        String name = project.getName();
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);
        List<Project> projects = list(queryWrapper);
        if(projects.size()>0)
            throw new MyException("项目名已存在");
        save(project);
        return project;
    }

    @Override
    public void saveProject(String projectId,Layer layer,Component[] components) {
        if(listByIds(Collections.singletonList(projectId)).size()==0)
            throw new MyException("项目不存在");
        deleteProject(projectId);
        List<Graph> graphs = new ArrayList<>();
        List<Chart> charts = new ArrayList<>();
        layer.setProjectId(projectId);
        layerService.save(layer);
        for(Component component : components){
            if("graph".equals(component.getTitle())){
                Graph graph = ctoGraph(projectId,component);
                graphs.add(graph);
            }
            if("chart".equals(component.getTitle())){
                Chart chart = ctoChart(projectId,component);
                charts.add(chart);
            }
            if("image".equals(component.getTitle())){

            }
        }
        graphService.saveBatch(graphs);
        chartService.saveBatch(charts);
    }

    private Chart ctoChart(String projectId,Component component) {
        Chart chart = new Chart();
        chart.setProjectId(projectId);
        chart.setId(component.getId());
        chart.setType(component.getType());
        Component.Style style = component.getStyle();
        Component.Style.Position position = style.getPosition();
        chart.setX(position.getX());
        chart.setY(position.getY());
        chart.setH(position.getH());
        chart.setW(position.getW());
        chart.setBackColor(style.getBackColor());
        chart.setBorderColor(style.getBorderColor());
        chart.setBorderWidth(style.getBorderWidth());
        chart.setFontFamily(style.getFontFamily());
        chart.setFontSize(style.getFontSize());
        chart.setForeColor(style.getForeColor());
        chart.setIdentifier(style.getIdentifier());
        chart.setLocked(style.getLocked());
        chart.setText(style.getText());
        chart.setTransform(style.getTransform());
        chart.setZIndex(style.getZIndex());
        chart.setDataTotal(style.getDataTotal());
        chart.setInnerColor(style.getInnerColor());
        chart.setMax(style.getMax());
        chart.setMin(style.getMin());
        chart.setSplitNumber(style.getSplitNumber());
        chart.setVisible(style.isVisible());
        chart.setWaveColor(style.getWaveColor());
        chart.setName(component.getName());
        chart.setIdentifier(component.getIdentifier());
        return chart;
    }

    private Graph ctoGraph(String projectId,Component component) {
        Graph graph = new Graph();
        graph.setProjectId(projectId);
        graph.setId(component.getId());
        Component.Style style = component.getStyle();
        Component.Style.Position position = style.getPosition();
        graph.setType(component.getType());
        graph.setX(position.getX());
        graph.setY(position.getY());
        graph.setH(position.getH());
        graph.setW(position.getW());
        graph.setAngle(style.getAngle());
        graph.setBackColor(style.getBackColor());
        graph.setBorderColor(style.getBorderColor());
        graph.setBorderWidth(style.getBorderWidth());
        graph.setFontFamily(style.getFontFamily());
        graph.setFontSize(style.getFontSize());
        graph.setForeColor(style.getForeColor());
        graph.setIdentifier(style.getIdentifier());
        graph.setIsItalic(style.getIsItalic());
        graph.setIsUnderline(style.getIsUnderline());
        graph.setIsWeight(style.getIsWeight());
        graph.setLibraryitemId(style.getLibraryitemId());
        graph.setLocked(style.getLocked());
        graph.setRadius(style.getRadius());
        graph.setText(style.getText());
        graph.setTextAlign(style.getTextAlign());
        graph.setTransform(style.getTransform());
        graph.setVisible(style.isVisible());
        graph.setZIndex(style.getZIndex());
        graph.setName(component.getName());
        graph.setIdentifier(component.getIdentifier());
        return graph;
    }
}

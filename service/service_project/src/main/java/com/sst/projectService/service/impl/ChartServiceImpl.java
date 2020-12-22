package com.sst.projectService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sst.commonutils.MyException;
import com.sst.projectService.entity.Chart;
import com.sst.projectService.entity.ChartComponent;
import com.sst.projectService.mapper.ChartMapper;
import com.sst.projectService.service.ChartComponentService;
import com.sst.projectService.service.ChartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sst.projectService.service.ComponentHasVariableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sst
 * @since 2020-11-14
 */
@Service
public class ChartServiceImpl extends ServiceImpl<ChartMapper, Chart> implements ChartService {
    @Autowired
    ChartComponentService chartComponentService;
    @Autowired
    ComponentHasVariableService chvService;

    @Override
    public void delete(String projectId) {
        QueryWrapper<Chart> wrapper = new QueryWrapper<>();
        wrapper.eq("project_id", projectId);
        List<Chart> charts = list(wrapper);
        if (charts == null) return;
        for (Chart chart : charts) {
            QueryWrapper<ChartComponent> wrapper2 = new QueryWrapper<>();
            wrapper2.eq("chart_id", chart.getId());
            //解除控件绑定
            List<ChartComponent> chartComponents = chartComponentService.list(wrapper2);
            if (chartComponents == null) continue;
            for (ChartComponent cc : chartComponents) {
                chvService.unbindVariables(cc.getIdentifier());
            }
            chartComponentService.remove(wrapper2);
        }
        remove(wrapper);
    }

    @Override
    public void addBatch(List<Chart> charts) {
        saveBatch(charts);
        for (Chart chart : charts) {
            List<ChartComponent> chartComponents = chart.getChartComponents();
            if (chartComponents != null) {
                for (ChartComponent chartComponent : chartComponents) chartComponent.setChartId(chart.getId());
                chartComponentService.saveBatch(chartComponents);
            }
        }
    }

    @Override
    public List<Chart> findAll(String id) {
        QueryWrapper<Chart> wrapper = new QueryWrapper<>();
        wrapper.eq("project_id", id);
        List<Chart> charts = list(wrapper);
        for (Chart chart : charts) {
            QueryWrapper<ChartComponent> wrapper2 = new QueryWrapper<>();
            wrapper2.eq("chart_id", chart.getId());
            chart.setChartComponents(chartComponentService.list(wrapper2));
        }
        return charts;
    }

    @Override
    public void unbindVariables(Chart chart) {
        List<ChartComponent> chartComponents = chart.getChartComponents();
        if (chartComponents == null) return;
        for (ChartComponent cc : chartComponents) {
            chvService.unbindVariables(cc.getIdentifier());
        }
    }

    @Override
    public void bindVariable(Chart chart) {
        List<ChartComponent> chartComponents = chart.getChartComponents();
        if (chartComponents == null) return;
        for (ChartComponent cc : chartComponents) {
            if(cc.getIdentifier()==null && cc.getVid()!=null)
                throw new MyException("控件的identifier为空，绑定失败");
            chvService.bindVariable(cc.getIdentifier(), cc.getVid());
        }
    }
}

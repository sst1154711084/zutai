package com.sst.projectService.service.impl;

import com.sst.commonutils.MyException;
import com.sst.projectService.entity.Layer;
import com.sst.projectService.entity.Project;
import com.sst.projectService.mapper.LayerMapper;
import com.sst.projectService.service.LayerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sst.projectService.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sst
 * @since 2020-11-14
 */
@Service
public class LayerServiceImpl extends ServiceImpl<LayerMapper, Layer> implements LayerService {

    @Autowired
    ProjectService projectService;

    @Override
    public Layer addLayer(Layer layer) {
        Project project = projectService.getById(layer.getProjectId());
        if (project == null)
            throw new MyException("项目不存在");
        save(layer);
        return layer;
    }

    @Override
    public void deleteLayer(String id) {
        if(checkLayerIsExistById(id))
            throw new MyException("项目背景不存在");
        removeById(id);
    }

    private boolean checkLayerIsExistById(String id) {
        Layer layer = getById(id);
        return layer == null;
    }

    @Override
    public Layer updateLayer(Layer layer) {
        if(checkLayerIsExistById(layer.getId()))
            throw new MyException("项目背景不存在");
        updateById(layer);
        return layer;
    }
}

package com.sst.projectService.service;

import com.sst.projectService.entity.Layer;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sst
 * @since 2020-11-14
 */
public interface LayerService extends IService<Layer> {

    Layer addLayer(Layer layer);

    void deleteLayer(String id);

    Layer updateLayer(Layer layer);
}

package com.sst.projectService.controller;


import com.sst.commonutils.NotBlank;
import com.sst.commonutils.R;
import com.sst.projectService.entity.Layer;
import com.sst.projectService.service.LayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sst
 * @since 2020-11-14
 */
@RestController
@RequestMapping("/api/layer")
public class LayerController {
    @Autowired
    LayerService layerService;
    //1.获取项目背景
    @RequestMapping("/getLayer")
    public R getLayer(@NotBlank String id){
        return R.ok().data(layerService.getById(id));
    }

    //2.创建项目背景
    @RequestMapping("/addLayer")
    public R addLayer(@RequestBody Layer layer){
        return R.ok().data(layerService.addLayer(layer));
    }

    //3.删除项目背景
    @RequestMapping("/deleteLayer")
    public R deleteLayer(@RequestParam @NotBlank String id){
        layerService.deleteLayer(id);
        return R.ok().message("删除成功");
    }
    //4.编辑项目背景
    @RequestMapping("/updateLayer")
    public R updateLayer(@RequestBody Layer layer){
        return R.ok().data(layerService.updateLayer(layer));
    }

}


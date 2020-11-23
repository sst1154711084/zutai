package com.sst.projectService.controller;


import com.sst.commonutils.R;
import com.sst.projectService.entity.Sharedlibrary;
import com.sst.projectService.service.SharedlibraryService;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @since 2020-11-14
 */
@RestController
@RequestMapping("/api/sharedlibrary")
public class SharedlibraryController {
    @Autowired
    SharedlibraryService sharedlibraryService;

    //1.获得所有公共库
    @RequestMapping("/getAllLibrary")
    public R getAllLibrary() {
        List<Sharedlibrary> sharedlibraries = sharedlibraryService.list(null);
        return R.ok().data(sharedlibraries);
    }

    //2.增加库
    @RequestMapping("/addLibrary")
    public R addLibrary(@RequestParam String name) {
        Sharedlibrary library = sharedlibraryService.addLibrary(name);
        return R.ok().data(library);
    }

    //3.删除库
    @RequestMapping("/deleteLibrary")
    public R deleteLibrary(@RequestParam String id){
        sharedlibraryService.deleteLibraryById(id);
        return R.ok().data("删除成功");
    }

    //4.编辑库
    @RequestMapping("/updateLibrary")
    public R updateLibrary(@RequestParam String id,@RequestParam String name){
        Sharedlibrary library = sharedlibraryService.updateLibrary(id,name);
        return R.ok().data(library);
    }
}


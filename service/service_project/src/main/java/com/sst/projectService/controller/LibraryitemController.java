package com.sst.projectService.controller;


import com.sst.commonutils.NotBlank;
import com.sst.commonutils.R;
import com.sst.projectService.service.LibraryitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author sst
 * @since 2020-11-14
 */
@RestController
@RequestMapping("/api/libraryitem")
public class LibraryitemController {
    @Autowired
    LibraryitemService libraryitemService;
    //1.根据库id获取库内的组件
    @RequestMapping("getLibraryItemsById")
    public R getLibraryItemsById(@RequestParam @NotBlank String libraryId){
        return R.ok().data(libraryitemService.getLibraryItemsById(libraryId));
    }

}


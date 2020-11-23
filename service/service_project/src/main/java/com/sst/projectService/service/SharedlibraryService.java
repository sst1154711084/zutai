package com.sst.projectService.service;

import com.sst.projectService.entity.Sharedlibrary;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sst
 * @since 2020-11-14
 */
public interface SharedlibraryService extends IService<Sharedlibrary> {

    Sharedlibrary addLibrary(String name);

    void deleteLibraryById(String id);

    Sharedlibrary updateLibrary(String id, String name);
}

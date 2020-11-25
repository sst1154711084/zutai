package com.sst.projectService.service;

import com.sst.projectService.entity.Libraryitem;
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
public interface LibraryitemService extends IService<Libraryitem> {

    List<Libraryitem> getLibraryItemsById(String libraryId);
}

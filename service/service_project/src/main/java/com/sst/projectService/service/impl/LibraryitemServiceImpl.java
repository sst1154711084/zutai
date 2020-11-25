package com.sst.projectService.service.impl;

import com.sst.projectService.entity.Libraryitem;
import com.sst.projectService.entity.Sharedlibrary;
import com.sst.projectService.mapper.LibraryitemMapper;
import com.sst.projectService.service.LibraryitemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sst.projectService.service.SharedlibraryService;
import org.apache.tomcat.jni.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sst
 * @since 2020-11-14
 */
@Service
public class LibraryitemServiceImpl extends ServiceImpl<LibraryitemMapper, Libraryitem> implements LibraryitemService {

    @Autowired
    SharedlibraryService sharedlibraryService;
    @Override
    public List<Libraryitem> getLibraryItemsById(String libraryId) {
        Sharedlibrary sharedlibrary = sharedlibraryService.getById(libraryId);

        return null;
    }
}

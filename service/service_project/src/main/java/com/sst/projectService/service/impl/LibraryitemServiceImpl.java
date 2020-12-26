package com.sst.projectService.service.impl;

import ch.qos.logback.core.util.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sst.commonutils.MyException;
import com.sst.projectService.entity.Libraryitem;
import com.sst.projectService.entity.Sharedlibrary;
import com.sst.projectService.mapper.LibraryitemMapper;
import com.sst.projectService.service.LibraryitemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sst.projectService.service.SharedlibraryService;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.jni.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
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

    @Override
    public List<Libraryitem> getLibrary(String libraryId) {
        QueryWrapper<Libraryitem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("library_id",libraryId);
        List<Libraryitem> list = list(queryWrapper);
        return list;
    }

    @Override
    public void deleteFile(String id) {
        Libraryitem libraryitem = getById(id);
        if(libraryitem==null)
            throw new MyException("无此文件");
        String filePath = libraryitem.getLujing();
        removeById(id);
        try {
            File file = new File(filePath);
            if (file.exists()){
                file.delete();
            }
        }catch (Exception e){
            throw new MyException("文件删除错误");
        }

    }
}

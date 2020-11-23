package com.sst.projectService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sst.commonutils.MyException;
import com.sst.projectService.entity.Sharedlibrary;
import com.sst.projectService.mapper.SharedlibraryMapper;
import com.sst.projectService.service.SharedlibraryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Wrapper;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
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
public class SharedlibraryServiceImpl extends ServiceImpl<SharedlibraryMapper, Sharedlibrary> implements SharedlibraryService {

    @Override
    public Sharedlibrary addLibrary(String name) {
        QueryWrapper<Sharedlibrary> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        List<Sharedlibrary> sharedlibraries = list(queryWrapper);
        if (sharedlibraries.size() > 0)
            throw new MyException("库名已存在");
        Sharedlibrary sharedlibrary = new Sharedlibrary();
        sharedlibrary.setName(name);
        save(sharedlibrary);
        return sharedlibrary;
    }

    @Override
    public void deleteLibraryById(String id) {
        Collection<Sharedlibrary> sharedlibraries = listByIds(Collections.singletonList(id));
        if (sharedlibraries.size() == 0)
            throw new MyException("库不存在");
        boolean b = removeById(id);
        if (!b)
            throw new MyException("删除失败");
    }

    @Override
    public Sharedlibrary updateLibrary(String id, String name) {
        Collection<Sharedlibrary> sharedlibraries = listByIds(Collections.singletonList(id));
        if (sharedlibraries.size() == 0)
            throw new MyException("库不存在");
        Sharedlibrary library = new Sharedlibrary(id,name);
        updateById(library);
        return library;
    }
}

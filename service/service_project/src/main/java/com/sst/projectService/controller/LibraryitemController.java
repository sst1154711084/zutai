package com.sst.projectService.controller;

import com.sst.commonutils.MyException;
import com.sst.commonutils.NotBlank;
import com.sst.commonutils.R;
import com.sst.projectService.entity.Libraryitem;
import com.sst.projectService.service.LibraryitemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
@Slf4j
public class LibraryitemController {
    @Autowired
    LibraryitemService libraryitemService;
    //1.根据库id获取库内的组件
    @RequestMapping("getLibrary")
    public R getLibrary(@RequestParam @NotBlank String id){
        return R.ok().data(libraryitemService.getLibrary(id));
    }

    //2.上传图片、svg
    @RequestMapping(value="/uploadFile",produces="application/json;charset=UTF-8")
    public R uploadFile(@RequestParam @NotBlank String libraryId,@RequestParam("fileName") MultipartFile file) {
        System.out.print("上传文件==="+"\n");
        //判断文件是否为空
        if (file.isEmpty()) {
            throw new MyException("上传文件不可为空");
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        System.out.print("上传的文件名为: "+fileName+"\n");

        //fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
        //System.out.print("（加个时间戳，尽量避免文件名称重复）保存的文件名为: "+fileName+"\n");


        //加个时间戳，尽量避免文件名称重复
        //String path = "E:/fileUpload/" +fileName;
        String path = "";
        try {
            path = ResourceUtils.getURL("classpath:").getPath()+fileName;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //String path = "E:/fileUpload/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
        //文件绝对路径
        System.out.print("保存文件绝对路径"+path+"\n");

        //创建文件路径
        File dest = new File(path);

        //判断文件是否已经存在
        if (dest.exists()) {
            return R.error().message("文件已经存在");
        }

        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        String url;
        try {
            //上传文件
            file.transferTo(dest); //保存文件
            System.out.print("保存文件路径"+path+"\n");
            //url="http://你自己的域名/项目名/images/"+fileName;//正式项目
            url ="http://localhost:8001/images/"+fileName;//本地运行项目
            Libraryitem libraryitem = new Libraryitem();
            libraryitem.setName(fileName);
            libraryitem.setUrl(url);
            libraryitem.setLujing(path);
            libraryitem.setLibraryId(libraryId);
            boolean jieguo= libraryitemService.save(libraryitem);
            System.out.print("插入结果"+jieguo+"\n");
            System.out.print("保存的完整url===="+url+"\n");

        } catch (IOException e) {
            throw new MyException("上传失败");
        }
        return R.ok().data(url);
    }

    //3.删除图片
    @RequestMapping("/deleteFile")
    public R deleteFile(@RequestParam @NotBlank String id){
        libraryitemService.deleteFile(id);
        return R.ok();
    }
}


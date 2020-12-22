package com.sst.projectService;

import com.alibaba.fastjson.JSON;
import com.sst.projectService.entity.Project;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class produceJSON {

    @Test
    public void projectJSON(){
        Project project = new Project();
        project.setName("姓名");
        project.setAddress("地址");
        project.setContact("联系人");
        project.setContactNo("联系人电话");
        String ss = JSON.toJSONString(project);
        System.out.println(ss);
    }




}

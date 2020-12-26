package com.sst.projectService;

import com.alibaba.fastjson.JSON;
import com.sst.projectService.entity.Project;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;

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
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {return o2-o1;});
        Arrays.stream(new int[]{1,2,3}).boxed().collect(Collectors.toList());
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"3242","2131","123","321"};
        Map<String, List<String>>map = new HashMap<>();
        for(int i = 0;i < strs.length;i++){
            char[] temp =  strs[i].toCharArray();
            Arrays.sort(temp);
            String news = new String(temp);
            if(map.containsKey(news))map.get(news).add(strs[i]);
            else map.put(news,Arrays.asList(strs[i]));

        }
        List<List<String>> res = new ArrayList();
        for(Map.Entry<String,List<String>> entry : map.entrySet()){
            res.add(entry.getValue());
        }
        System.out.println();
    }



}

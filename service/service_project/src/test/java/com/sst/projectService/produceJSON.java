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
    public void projectJSON() {
        Project project = new Project();
        project.setName("姓名");
        project.setAddress("地址");
        project.setContact("联系人");
        project.setContactNo("联系人电话");
        String ss = JSON.toJSONString(project);
        System.out.println(ss);
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
            return o2 - o1;
        });
        Arrays.stream(new int[]{1, 2, 3}).boxed().collect(Collectors.toList());
    }

    public static TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        LinkedList<TreeNode> stack = new LinkedList();
        stack.push(root);
        TreeNode cur = root;
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if (node.left != null && cur != node.left && cur != node.right) {
                stack.push(node.left);
            } else if (node.right != null && cur != root.right) {
                stack.push(node.right);
            } else {
                cur = stack.pop();
                invertNode(cur);
            }

        }
        return root;
    }

    static void invertNode(TreeNode root) {
        TreeNode temp = root.right;
        root.right = root.left;
        root.left = temp;
    }



    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


}

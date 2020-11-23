package com.sst.projectService.entity.ro;

import com.sst.projectService.entity.Layer;
import lombok.Data;

import java.util.List;

@Data
public class ResultProject {
    private Layer layer;
    private List<Object> components;
}

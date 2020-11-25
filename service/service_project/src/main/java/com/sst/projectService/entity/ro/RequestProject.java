package com.sst.projectService.entity.ro;

import com.sst.projectService.entity.Component;
import com.sst.projectService.entity.Layer;
import lombok.Data;

@Data
public class RequestProject {
    Layer layer;
    Component[] components;
}

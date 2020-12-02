package com.sst.projectService.entity.ro;

import lombok.Data;

@Data
public class ResultItem {
    /*{
        "name": "3D按钮20",
            "type": "image",
            "info": {
                "style": {
                    "backColor": "transparent",
                            "position": {
                        "h": 42,
                                "w": 42,
                                "x": 0,
                                "y": 0
                    },
                    "url": "statics/shapes/1/20.png",
                            "zIndex": 1
                },
            },
    }*/
    String name;
    String type;
    Info info;
    @Data
    public class Info{
        @Data
        public class Style {
            @Data
            public class Position {
                double x, y;
                int w, h;
            }
            String backColor;
            Position position;
            String url;
            int zIndex;
        }
        Style style;
    }
}

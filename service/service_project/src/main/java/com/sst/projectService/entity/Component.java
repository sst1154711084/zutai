package com.sst.projectService.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class Component {
    @Data
    public class Style {
        @Data
        public class Position {
            double x, y;
            int w, h;
        }

        Position position;

        @ApiModelProperty(value = "旋转角度")
        private Double angle;

        @ApiModelProperty(value = "是否可以移动")
        private Boolean locked;

        @ApiModelProperty(value = "线条颜色")
        private String borderColor;

        @ApiModelProperty(value = "背景颜色")
        private String backColor;

        private String foreColor;

        @ApiModelProperty(value = "边框宽度")
        private Integer borderWidth;

        private Integer transform;

        private String text;

        private String textAlign;

        private Integer fontSize;

        private String fontColor;

        private String fontFamily;

        private Integer zIndex;

        @ApiModelProperty(value = "圆角角度")
        private Double radius;

        @ApiModelProperty(value = "是否可见")
        private boolean visible;

        @ApiModelProperty(value = "前端生成id")
        private String identifier;

        @ApiModelProperty(value = "是否粗体")
        private Boolean isWeight;

        @ApiModelProperty(value = "是否斜体")
        private Boolean isItalic;

        @ApiModelProperty(value = "是否下划线")
        @TableField("is_underLine")
        private Boolean isUnderline;

        private String libraryitemId;

        private Integer min;

        private Integer max;

        private String waveColor;

        private String innerColor;

        private Integer dataTotal;

        private Integer splitNumber;

        private String url;

    }

    private String id;

    private String title;

    private String type;

    Style style;

    private Date createTime;

    private Date updateTime;

    String identifier;

    private String name;

    @ApiModelProperty(value = "对应的项目id")
    private String projectId;

}

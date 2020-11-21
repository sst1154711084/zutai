package com.sst.projectService.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author sst
 * @since 2020-11-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Graph对象", description="")
public class Graph implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "图形id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "图形类型")
    private String type;

    @ApiModelProperty(value = "组件名称")
    private String name;

    @ApiModelProperty(value = "外框左上x坐标")
    private Double x;

    @ApiModelProperty(value = "外框左上y坐标")
    private Double y;

    @ApiModelProperty(value = "宽度")
    private Integer w;

    @ApiModelProperty(value = "高度")
    private Integer h;

    @ApiModelProperty(value = "旋转角度")
    private Double angle;

    @ApiModelProperty(value = "是否可以移动")
    private Boolean locked;

    @ApiModelProperty(value = "对应的项目id")
    private String projectId;

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

    private String fontFamily;

    @ApiModelProperty(value = "层级")
    @TableField("zIndex")
    private Integer zIndex;

    @ApiModelProperty(value = "生成时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "圆角角度")
    private Double radius;

    @ApiModelProperty(value = "是否可见")
    private Integer visible;

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


}

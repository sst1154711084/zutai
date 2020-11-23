package com.sst.projectService.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
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
@ApiModel(value="Chart对象", description="")
public class Chart implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "图形id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

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

    @ApiModelProperty(value = "是否可以移动")
    private Integer locked;

    @ApiModelProperty(value = "对应的项目id")
    private String projectId;

    @ApiModelProperty(value = "层级")
    @TableField("zIndex")
    private Integer zIndex;

    private Integer transform;

    @ApiModelProperty(value = "背景颜色")
    private String backColor;

    private String foreColor;

    private String barColor;

    private String fontFamily;

    private String fontColor;

    private Integer fontSize;

    @ApiModelProperty(value = "前端生成id")
    private String identifier;

    private String text;

    private Integer min;

    private Integer max;

    private String waveColor;

    private String innerColor;

    private String borderColor;

    private Integer dataTotal;

    private Integer angle;

    private Boolean visible;

    private Integer borderWidth;

    private Integer splitNumber;


}

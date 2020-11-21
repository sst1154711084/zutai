package com.sst.projectService.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value="Layer对象", description="")
public class Layer implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目背景画面id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    private Date createTime;

    private Date updateTime;

    @ApiModelProperty(value = "背景画面名")
    private String name;

    @ApiModelProperty(value = "背景画面宽度")
    private Integer width;

    @ApiModelProperty(value = "背景画面高度")
    private Integer height;

    @ApiModelProperty(value = "背景画面颜色")
    private String backColor;

    @ApiModelProperty(value = "背景画面描述")
    private String description;

    @ApiModelProperty(value = "背景画面图片")
    private String backgroundImage;

    @ApiModelProperty(value = "背景画面宽高比")
    private String widthHeightRatio;

    private String projectId;


}

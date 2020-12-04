package com.sst.projectService.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2020-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Variable对象", description="")
public class Variable implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "变量id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "变量名")
    private String name;

    @ApiModelProperty(value = "变量连接的数据源id")
    private String dataSourceId;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "变量值")
    private Double value;

    @ApiModelProperty(value = "模拟刷新频率")
    private Integer frequency;

    @ApiModelProperty(value = "模拟最小值")
    private Double min;

    @ApiModelProperty(value = "模拟最大值")
    private Double max;


}

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
@ApiModel(value="Sharedlibrary对象", description="")
public class Sharedlibrary implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "公共库id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    private Date createTime;

    private Date updateTime;

    @ApiModelProperty(value = "公共库名")
    private String name;


}
